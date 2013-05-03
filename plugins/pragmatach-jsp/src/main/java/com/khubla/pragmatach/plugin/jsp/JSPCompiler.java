package com.khubla.pragmatach.plugin.jsp;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;

import javax.servlet.Servlet;

import org.apache.jasper.JspC;
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
   public JSPCompiler() throws MalformedURLException {
      final File f = new File(tempdir);
      f.mkdirs();
      urlClassLoader = new URLClassLoader(new URL[] { new URL("file://" + tempdir) });
   }

   /**
    * compile
    */
   private Class<?> compile(String jspFile) throws PragmatachException {
      try {
         final String className = makeClassName(jspFile);
         final String fullyQualifiedClassName = getFullyQualifiedClassName(jspFile);
         logger.info("Compiling '" + jspFile + "' to '" + fullyQualifiedClassName + "'");
         final JspC jspc = new JspC();
         jspc.setCompile(true);
         jspc.setUriroot(System.getProperty("user.dir"));
         jspc.setJspFiles(jspFile);
         jspc.setFailOnError(false);
         jspc.setOutputDir(tempdir);
         jspc.setClassName(className);
         jspc.execute();
         return urlClassLoader.loadClass(fullyQualifiedClassName);
      } catch (final Exception e) {
         throw new PragmatachException("Exception in compile", e);
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
         Class<?> clazz = urlClassLoader.loadClass(fullyQualifiedClassName);
         if (null != clazz) {
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
