package com.khubla.pragmatach.plugin.jsp;

import java.io.File;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;

import org.apache.commons.io.FileUtils;
import org.apache.jasper.EmbeddedServletOptions;
import org.apache.jasper.JspCompilationContext;
import org.apache.jasper.Options;
import org.apache.jasper.compiler.Compiler;
import org.apache.jasper.compiler.JspRuntimeContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.khubla.pragmatach.framework.api.PragmatachException;

/**
 * Simple JSP compiler
 * <p>
 * http://javasourcecode.org/html/open-source/tomcat/tomcat-7.0.29/org/apache/jasper/JspCompilationContext.java.html
 * </p>
 * <p>
 * http://javasourcecode.org/html/open-source/tomcat/tomcat-7.0.29/org/apache/jasper/JspC.java.html
 * </p>
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
   private final ClassLoader classLoader;

   /**
    * ctor
    */
   public JSPCompiler(ServletConfig servletConfig, ServletContext servletContext) {
      this.servletContext = servletContext;
      this.servletConfig = servletConfig;
      final File f = new File(tempdir);
      f.mkdirs();
      classLoader = createClassLoader();
   }

   /**
    * compile a jspFile and return the class type
    */
   private Class<?> compile(String jspFile) throws PragmatachException {
      ClassLoader originalClassLoader = null;
      try {
         /*
          * get description of the class we want to create
          */
         final String className = makeClassName(jspFile);
         final String packageName = getPackage(jspFile);
         final String fullyQualifiedClassName = getFullyQualifiedClassName(jspFile);
         /*
          * log
          */
         logger.info("Compiling '" + jspFile + "' to '" + fullyQualifiedClassName + "'");
         /*
          * options
          */
         final Options options = new EmbeddedServletOptions(servletConfig, servletContext);
         /*
          * runtime context
          */
         final JspRuntimeContext jspRuntimeContext = new JspRuntimeContext(servletContext, options);
         /*
          * set up class compilation context
          */
         final String jspUri = jspFile.replace('\\', '/');
         final JspCompilationContext jspCompilationContext = new JspCompilationContext(jspUri, options, servletContext, null, jspRuntimeContext);
         jspCompilationContext.setServletClassName(className);
         jspCompilationContext.setServletPackageName(packageName);
         /*
          * save the class loader and set new class loader
          */
         originalClassLoader = Thread.currentThread().getContextClassLoader();
         Thread.currentThread().setContextClassLoader(classLoader);
         jspCompilationContext.setClassLoader(classLoader);
         /*
          * compile
          */
         final Compiler compiler = jspCompilationContext.createCompiler();
         if (null != compiler) {
            compiler.compile();
         } else {
            throw new Exception("Unable to create compiler");
         }
         /*
          * done
          */
         return classLoader.loadClass(fullyQualifiedClassName);
      } catch (final Exception e) {
         throw new PragmatachException("Exception in compile", e);
      } finally {
         if (originalClassLoader != null) {
            Thread.currentThread().setContextClassLoader(originalClassLoader);
         }
      }
   }

   /**
    * set up the class loader
    */
   private ClassLoader createClassLoader() {
      URLClassLoader classLoader = null;
      try {
         /*
          * the urls
          */
         final List<URL> urls = new ArrayList<URL>();
         /*
          * add the temp dir
          */
         urls.add(new URL("file://" + tempdir));
         /*
          * add the system classpath
          */
         final String systemClasspath = System.getProperty("java.class.path");
         final String[] ss = systemClasspath.split(File.pathSeparator);
         for (final String s : ss) {
            logger.info("Added '" + s + "' to JSP compiler classpath");
            urls.add(new URL("file://" + s));
         }
         /*
          * find the files in /WEB-INF/classes/
          */
         final String rootURI = servletContext.getRealPath(File.separator);
         final File classesDir = new File(rootURI + "/WEB-INF/classes/");
         if (classesDir.exists()) {
            final Collection<File> jars = FileUtils.listFiles(classesDir, new String[] { "jar" }, true);
            for (final File jar : jars) {
               logger.info("Added '" + jar.getAbsolutePath() + "' to JSP compiler classpath");
               urls.add(new URL("file://" + jar.getAbsolutePath()));
            }
         }
         /*
          * find the files in /WEB-INF/lib/
          */
         final File libsDir = new File(rootURI + "/WEB-INF/lib/");
         if (libsDir.exists()) {
            final Collection<File> jars = FileUtils.listFiles(libsDir, new String[] { "jar" }, true);
            for (final File jar : jars) {
               logger.info("Added '" + jar.getAbsolutePath() + "' to JSP compiler classpath");
               urls.add(new URL("file://" + jar.getAbsolutePath()));
            }
         }
         /*
          * done
          */
         final URL[] u = new URL[urls.size()];
         urls.toArray(u);
         classLoader = new URLClassLoader(u);
      } catch (final Exception e) {
         logger.error("Exception in createClassLoader", e);
      }
      return classLoader;
   }

   /**
    * get jspFile fully qualified name
    */
   private String getFullyQualifiedClassName(String jspFile) {
      return getPackage(jspFile) + "." + makeClassName(jspFile);
   }

   /**
    * get jspFile package
    */
   private String getPackage(String jspFile) {
      final int i = jspFile.lastIndexOf("/");
      if (-1 != i) {
         return "org.apache.jsp." + jspFile.substring(0, i).replaceAll("/", ".");
      } else {
         return "org.apache.jsp";
      }
   }

   /**
    * given a JSP file relative path, return a Servlet
    */
   public Servlet getServlet(String jspFile) throws PragmatachException {
      try {
         final String fullyQualifiedClassName = getFullyQualifiedClassName(jspFile);
         Class<?> clazz = null;
         try {
            clazz = classLoader.loadClass(fullyQualifiedClassName);
         } catch (final ClassNotFoundException ex) {
         }
         if (null == clazz) {
            clazz = compile(jspFile);
         }
         return (Servlet) clazz.newInstance();
      } catch (final Exception e) {
         throw new PragmatachException("Exception in getServlet", e);
      }
   }

   /**
    * create classname from jspFile
    */
   private String makeClassName(String jspFile) {
      String ret = jspFile.substring(0, jspFile.indexOf("."));
      final int i = ret.lastIndexOf("/");
      if (-1 != i) {
         ret = ret.substring(i + 1);
      }
      return ret;
   }
}