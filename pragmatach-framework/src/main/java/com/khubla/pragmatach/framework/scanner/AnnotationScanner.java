package com.khubla.pragmatach.framework.scanner;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.ServletContext;

import org.scannotation.AnnotationDB;
import org.scannotation.ClasspathUrlFinder;
import org.scannotation.WarUrlFinder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.khubla.pragmatach.framework.api.PragmatachException;

/**
 * @author tome
 */
public class AnnotationScanner {
   /**
    * get classes with annotation
    */
   public static Set<Class<?>> getAllClasses(Class<?> annotationClass) throws PragmatachException {
      try {
         final Set<Class<?>> ret = new HashSet<Class<?>>();
         final Set<String> classNames = annotationDB.getAnnotationIndex().get(annotationClass.getCanonicalName());
         if (null != classNames) {
            for (final String name : classNames) {
               final Class<?> clazz = Class.forName(name);
               ret.add(clazz);
            }
         }
         return ret;
      } catch (final Exception e) {
         throw new PragmatachException("Exception in getAllClasses", e);
      }
   }

   /**
    * get classes with annotation
    */
   public static Set<Method> getAllMethods(Class<?> annotationClass) throws PragmatachException {
      try {
         final Set<Method> ret = new HashSet<Method>();
         final Set<String> classNames = annotationDB.getAnnotationIndex().get(annotationClass.getCanonicalName());
         if (null != classNames) {
            for (final String name : classNames) {
               final Class<?> clazz = Class.forName(name);
               for (final Method method : clazz.getMethods()) {
                  for (final Annotation annotation : method.getDeclaredAnnotations()) {
                     if (0 == annotation.getClass().getCanonicalName().compareTo(annotationClass.getCanonicalName())) {
                        ret.add(method);
                     }
                  }
               }
            }
         }
         return ret;
      } catch (final Exception e) {
         throw new PragmatachException("Exception in getAllMethods", e);
      }
   }

   public static AnnotationDB getAnnotationDB() {
      return annotationDB;
   }

   /**
    * do the scan
    */
   public static void scan(ServletContext servletContext) throws PragmatachException {
      scanClassPath();
      scanWar(servletContext);
   }

   private static void scanClassPath() throws PragmatachException {
      try {
         final URL[] urls = ClasspathUrlFinder.findClassPaths();
         annotationDB.scanArchives(urls);
      } catch (final Exception e) {
         throw new PragmatachException("Exception in scanClassPath", e);
      }
   }

   private static void scanWar(ServletContext servletContext) throws PragmatachException {
      try {
         if (null != servletContext) {
            final URL[] libURLs = WarUrlFinder.findWebInfLibClasspaths(servletContext);
            final URL classesURL = WarUrlFinder.findWebInfClassesPath(servletContext);
            if ((null != libURLs) && (libURLs.length > 0)) {
               annotationDB.scanArchives(libURLs);
            } else {
               logger.error("Unable to get WebInfLibClasspaths");
            }
            if (null != classesURL) {
               annotationDB.scanArchives(classesURL);
            } else {
               logger.error("Unable to get WebInfClassesPath");
            }
         }
      } catch (final Exception e) {
         throw new PragmatachException("Exception in scanWar", e);
      }
   }

   /**
    * logger
    */
   private static final Logger logger = LoggerFactory.getLogger(AnnotationScanner.class);
   /**
    * the DB
    */
   private static AnnotationDB annotationDB = new AnnotationDB();
}
