package com.khubla.pragmatach.framework.annotation;

import java.lang.reflect.Method;
import java.net.URL;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.ServletContext;

import org.scannotation.AnnotationDB;
import org.scannotation.ClasspathUrlFinder;
import org.scannotation.WarUrlFinder;

import com.khubla.pragmatach.framework.api.PragmatachException;

/**
 * @author tome
 */
public class AnnotationsScanner {
   /**
    * routes
    */
   private static Set<Method> routerMethods = new HashSet<Method>();
   /**
    * controllers
    */
   private static Set<Class<?>> controllers = new HashSet<Class<?>>();

   private static void addAnnotations(AnnotationDB annotationDB) throws PragmatachException {
      try {
         /*
          * controllers
          */
         final Set<String> controllerClasses = annotationDB.getAnnotationIndex().get(Controller.class.getName());
         if (null != controllerClasses) {
            for (final String name : controllerClasses) {
               /*
                * add the controller
                */
               final Class<?> clazz = Class.forName(name);
               controllers.add(clazz);
            }
         }
         /*
          * methods
          */
         final Set<String> routeMethods = annotationDB.getAnnotationIndex().get(Route.class.getName());
         if (null != routeMethods) {
            for (final String name : routeMethods) {
               final Class<?> clazz = Class.forName(name);
               final Method[] methods = clazz.getMethods();
               if (null != methods) {
                  for (final Method method : methods) {
                     if (method.isAnnotationPresent(Route.class)) {
                        routerMethods.add(method);
                     }
                  }
               }
            }
         }
      } catch (final Exception e) {
         throw new PragmatachException("Exception in addControllers", e);
      }
   }

   public static Set<Class<?>> getControllers() {
      return controllers;
   }

   public static Set<Method> getRouterMethods() {
      return routerMethods;
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
         final AnnotationDB annotationDB = new AnnotationDB();
         annotationDB.scanArchives(urls);
         addAnnotations(annotationDB);
      } catch (final Exception e) {
         throw new PragmatachException("Exception in scanClassPath", e);
      }
   }

   private static void scanWar(ServletContext servletContext) throws PragmatachException {
      try {
         if (null != servletContext) {
            final URL[] urls = WarUrlFinder.findWebInfLibClasspaths(servletContext);
            final AnnotationDB annotationDB = new AnnotationDB();
            annotationDB.scanArchives(urls);
            addAnnotations(annotationDB);
         }
      } catch (final Exception e) {
         throw new PragmatachException("Exception in scanClassPath", e);
      }
   }

   public static void setControllers(Set<Class<?>> controllers) {
      AnnotationsScanner.controllers = controllers;
   }

   public static void setRouterMethods(Set<Method> routerMethods) {
      AnnotationsScanner.routerMethods = routerMethods;
   }
}
