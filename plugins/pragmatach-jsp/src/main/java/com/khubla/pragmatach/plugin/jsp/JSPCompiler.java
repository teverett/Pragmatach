package com.khubla.pragmatach.plugin.jsp;

import java.io.File;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServlet;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
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
 * http://javasourcecode.org/html/open-source/tomcat/tomcat-6.0.32/org/apache/jasper/JspC.java.html
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
    * classloader
    */
   private URLClassLoader jspClassLoader;
   /**
    * options
    */
   private final Options options;
   /**
    * file
    */
   private final String jspFile;

   /**
    * ctor
    */
   public JSPCompiler(String jspFile, ServletConfig servletConfig, ServletContext servletContext) {
      this.servletContext = servletContext;
      this.servletConfig = servletConfig;
      options = new EmbeddedServletOptions(servletConfig, servletContext);
      this.jspFile = jspFile;
      jspClassLoader = createClassLoader(jspFile);
   }

   /**
    * compile a jspFile and return the class type
    */
   private Class<?> compile() throws PragmatachException {
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
         logger.info("Compiling '" + jspFile + "' to '" + fullyQualifiedClassName + "' in directory '" + getPackageDir(packageName) + "'");
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
         final JspCompilationContext jspCompilationContext = new JspCompilationContext(jspUri, false, options, servletContext, null, jspRuntimeContext);
         jspCompilationContext.setServletClassName(className);
         jspCompilationContext.setServletPackageName(packageName);
         /*
          * save the class loader and set new class loader
          */
         originalClassLoader = Thread.currentThread().getContextClassLoader();
         Thread.currentThread().setContextClassLoader(jspClassLoader);
         jspCompilationContext.setClassLoader(jspClassLoader);
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
          * refresh the classloader
          */
         jspClassLoader = createClassLoader(jspUri);
         /*
          * done
          */
         return jspClassLoader.loadClass(fullyQualifiedClassName);
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
   private URLClassLoader createClassLoader(String jspFile) {
      try {
         /*
          * the urls
          */
         final List<URL> urls = new ArrayList<URL>();
         /*
          * add the temp dir
          */
         urls.add(new URL("file://" + options.getScratchDir().getAbsolutePath() + "/"));
         /*
          * make sure the package dir is on the classpath
          */
         final String packageDir = getPackageDir(getPackage(jspFile));
         urls.add(new URL("file://" + packageDir + "/"));
         /*
          * add the system classpath
          */
         final String systemClasspath = System.getProperty("java.class.path");
         final String[] ss = systemClasspath.split(File.pathSeparator);
         for (final String s : ss) {
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
               urls.add(new URL("file://" + jar.getAbsolutePath()));
            }
         }
         /*
          * show all urls
          */
         for (final URL url : urls) {
            logger.debug(url.getPath());
         }
         /*
          * done
          */
         final URL[] u = new URL[urls.size()];
         urls.toArray(u);
         return new URLClassLoader(u);
      } catch (final Exception e) {
         logger.error("Exception in createClassLoader", e);
         return null;
      }
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
      final String classPart = jspFile.replaceAll(File.separator, ".");
      final int i = classPart.lastIndexOf(File.separator);
      if (-1 != i) {
         return "org.apache.jsp." + classPart.substring(0, i);
      } else {
         return "org.apache.jsp";
      }
   }

   /**
    * get the absolute dir that files in a specific package will end up in
    */
   private String getPackageDir(String packageName) {
      return options.getScratchDir().getAbsolutePath() + File.separator + StringUtils.replace(packageName, ".", File.separator);
   }

   /**
    * given a JSP file relative path, return a Servlet
    */
   public HttpServlet getServlet() throws PragmatachException {
      try {
         final String fullyQualifiedClassName = getFullyQualifiedClassName(jspFile);
         Class<?> clazz = null;
         try {
            clazz = jspClassLoader.loadClass(fullyQualifiedClassName);
         } catch (final ClassNotFoundException ex) {
         }
         if (null == clazz) {
            clazz = compile();
         }
         final Object o = clazz.newInstance();
         return (org.apache.jasper.runtime.HttpJspBase) o;
      } catch (final Exception e) {
         throw new PragmatachException("Exception in getServlet", e);
      }
   }

   /**
    * create classname from jspFile
    */
   private String makeClassName(String jspFile) {
      /*
       * get the part after the last dot
       */
      String ret = jspFile.substring(0, jspFile.indexOf("."));
      final int i = ret.lastIndexOf(File.separator);
      if (-1 != i) {
         ret = ret.substring(i + 1);
      }
      /*
       * capitalize first letter
       */
      ret = ret.substring(0, 1).toUpperCase() + ret.substring(1);
      /*
       * done
       */
      return ret;
   }
}