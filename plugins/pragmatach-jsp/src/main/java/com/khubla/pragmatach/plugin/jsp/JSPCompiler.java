package com.khubla.pragmatach.plugin.jsp;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;

import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;

import org.apache.jasper.EmbeddedServletOptions;
import org.apache.jasper.JspCompilationContext;
import org.apache.jasper.Options;
import org.apache.jasper.compiler.JspRuntimeContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.khubla.pragmatach.framework.api.PragmatachException;

/**
 * a simple wrapper for JspC
 * 
 * @author tome
 */
public class JSPCompiler {
   /**
    * servlet context
    */
   private final ServletContext servletContext;
   /**
    * servlet config
    */
   private final ServletConfig servletConfig;
   /**
    * logger
    */
   private final Logger logger = LoggerFactory.getLogger(this.getClass());
   /**
    * tempdir
    */
   private final String tempdir = System.getProperty("java.io.tmpdir") + "pragmatach/";
   /**
    * classloader
    */
   private final URLClassLoader urlClassLoader;

   /**
    * ctor
    */
   public JSPCompiler(ServletConfig servletConfig, ServletContext servletContext) throws MalformedURLException {
      this.servletContext = servletContext;
      this.servletConfig = servletConfig;
      final File f = new File(tempdir);
      f.mkdirs();
      urlClassLoader = new URLClassLoader(new URL[] { new URL("file://" + tempdir) });
   }

   /**
    * compile
    */
   private Class<?> compile(String jspFile) throws PragmatachException {
      ClassLoader originalClassLoader = null;
      try {
         /*
          * get description of the class we want to create
          */
         final String className = makeClassName(jspFile);
         final String packageName = this.getPackage(jspFile);
         final String fullyQualifiedClassName = getFullyQualifiedClassName(jspFile);
         /*
          * log
          */
         logger.info("Compiling '" + jspFile + "' to '" + fullyQualifiedClassName + "'");
         /*
          * options
          */
         Options options = new EmbeddedServletOptions(this.servletConfig, this.servletContext);
         /**
          * runtime context
          */
         JspRuntimeContext jspRuntimeContext = new JspRuntimeContext(this.servletContext, options);
         /*
          * set up class compilation context
          */
         String jspUri = jspFile.replace('\\', '/');
         JspCompilationContext jspCompilationContext = new JspCompilationContext(jspUri, options, servletContext, null, jspRuntimeContext);
         jspCompilationContext.setServletClassName(className);
         jspCompilationContext.setServletPackageName(packageName);
         /*
          * save the class loader and set new class loader
          */
         originalClassLoader = Thread.currentThread().getContextClassLoader();
         Thread.currentThread().setContextClassLoader(urlClassLoader);
         jspCompilationContext.setClassLoader(urlClassLoader);
         /*
          * compile
          */
         jspCompilationContext.compile();
         /*
          * done
          */
         return urlClassLoader.loadClass(fullyQualifiedClassName);
      } catch (final Exception e) {
         throw new PragmatachException("Exception in compile", e);
      } finally {
         if (originalClassLoader != null) {
            Thread.currentThread().setContextClassLoader(originalClassLoader);
         }
      }
   }

   private String getFullyQualifiedClassName(String jspFile) {
      return getPackage(jspFile) + "." + makeClassName(jspFile);
   }

   private String getPackage(String jspFile) {
      final int i = jspFile.lastIndexOf("/");
      if (-1 != i) {
         return "org.apache.jsp." + jspFile.substring(0, i).replaceAll("/", ".");
      } else {
         return "org.apache.jsp";
      }
   }

   public Servlet getServlet(String jspFile) throws PragmatachException {
      try {
         final String fullyQualifiedClassName = getFullyQualifiedClassName(jspFile);
         Class<?> clazz = null;
         try {
            clazz = urlClassLoader.loadClass(fullyQualifiedClassName);
         } catch (ClassNotFoundException ex) {
         }
         if (null == clazz) {
            clazz = compile(jspFile);
         }
         return (Servlet) clazz.newInstance();
      } catch (final Exception e) {
         throw new PragmatachException("Exception in getServlet", e);
      }
   }

   private String makeClassName(String jspFile) {
      String ret = jspFile.substring(0, jspFile.indexOf("."));
      final int i = ret.lastIndexOf("/");
      if (-1 != i) {
         ret = ret.substring(i + 1);
      }
      return ret;
   }
}
