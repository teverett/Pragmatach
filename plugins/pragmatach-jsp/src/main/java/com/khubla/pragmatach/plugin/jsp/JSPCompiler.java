package com.khubla.pragmatach.plugin.jsp;

import java.io.File;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.jasper.EmbeddedServletOptions;
import org.apache.jasper.JspCompilationContext;
import org.apache.jasper.Options;
import org.apache.jasper.compiler.Compiler;
import org.apache.jasper.compiler.JspRuntimeContext;
import org.apache.jasper.runtime.HttpJspBase;
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
    * class cache
    */
   private static JSPClassCache jspClassCache = new JSPClassCache();
   /**
    * namespace
    */
   private static final String JSP_NAMESPACE = "com.khubla.pragmatach.jsp";
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
   private URLClassLoader compilerClassLoader;
   /**
    * options
    */
   private final Options options;
   /**
    * file
    */
   private final String jspFile;
   /**
    * the classname
    */
   final String className;
   /**
    * the class package
    */
   final String packageName;
   /**
    * the fully qualified classname
    */
   final String fullyQualifiedClassName;
   /**
    * the path to the classfile
    */
   final String classFilePath;

   /**
    * ctor
    */
   public JSPCompiler(String jspFile, ServletConfig servletConfig, ServletContext servletContext) {
      this.servletContext = servletContext;
      this.servletConfig = servletConfig;
      options = new EmbeddedServletOptions(servletConfig, servletContext);
      this.jspFile = jspFile;
      compilerClassLoader = createCompilerClassLoader(jspFile);
      className = makeClassName();
      packageName = getPackage();
      fullyQualifiedClassName = getFullyQualifiedClassName();
      classFilePath = getClassFilePath();
   }

   /**
    * compile a jspFile and return the class type
    */
   private void compile() throws PragmatachException {
      ClassLoader originalClassLoader = null;
      try {
         /*
          * get description of the class we want to create
          */
         final String className = makeClassName();
         final String packageName = getPackage();
         final String fullyQualifiedClassName = getFullyQualifiedClassName();
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
         Thread.currentThread().setContextClassLoader(compilerClassLoader);
         jspCompilationContext.setClassLoader(compilerClassLoader);
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
         compilerClassLoader = createCompilerClassLoader(jspUri);
      } catch (final Exception e) {
         throw new PragmatachException("Exception in compile", e);
      } finally {
         if (originalClassLoader != null) {
            Thread.currentThread().setContextClassLoader(originalClassLoader);
         }
      }
   }

   /**
    * set up the class loader for the JSP compiler
    */
   private URLClassLoader createCompilerClassLoader(String jspFile) {
      try {
         /*
          * the urls
          */
         final List<URL> urls = new ArrayList<URL>();
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
         logger.error("Exception in createCompilerClassLoader", e);
         return null;
      }
   }

   private URLClassLoader createJSPClassLoader() {
      try {
         return new URLClassLoader(new URL[] { new URL("file://" + options.getScratchDir().getAbsolutePath() + "/") }, Thread.currentThread().getContextClassLoader());
      } catch (final Exception e) {
         logger.error("Exception in createJSPClassLoader", e);
         return null;
      }
   }

   private final String getClassFilePath() {
      return getPackageDir(getPackage()) + "/" + makeClassName() + ".class";
   }

   /**
    * get a Class<?> for the jspFile
    */
   private Class<?> getClazz() throws PragmatachException {
      try {
         /*
          * get from cache
          */
         Class<?> ret = jspClassCache.find(fullyQualifiedClassName);
         if (null == ret) {
            /*
             * compile
             */
            compile();
            /*
             * get it
             */
            final URLClassLoader jspClassLoader = createJSPClassLoader();
            if (null != jspClassLoader) {
               ret = jspClassLoader.loadClass(fullyQualifiedClassName);
               /*
                * cache it
                */
               jspClassCache.add(ret, fullyQualifiedClassName);
            }
         }
         /*
          * done
          */
         return ret;
      } catch (final Exception e) {
         throw new PragmatachException("Exception in getClazz", e);
      }
   }

   /**
    * get jspFile fully qualified name
    */
   private String getFullyQualifiedClassName() {
      return getPackage() + "." + makeClassName();
   }

   /**
    * get jspFile package
    */
   private String getPackage() {
      final String classPart = jspFile.replaceAll(File.separator, ".");
      final int i = classPart.lastIndexOf(File.separator);
      if (-1 != i) {
         return JSP_NAMESPACE + "." + classPart.substring(0, i);
      } else {
         return JSP_NAMESPACE;
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
   public HttpJspBase getServlet() throws PragmatachException {
      try {
         final Class<?> clazz = getClazz();
         if (null != clazz) {
            final Object o = clazz.newInstance();
            return (HttpJspBase) o;
         } else {
            return null;
         }
      } catch (final Exception e) {
         throw new PragmatachException("Exception in getServlet", e);
      }
   }

   /**
    * create classname from jspFile
    */
   private String makeClassName() {
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