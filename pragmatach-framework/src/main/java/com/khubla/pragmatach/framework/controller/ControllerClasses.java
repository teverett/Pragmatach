package com.khubla.pragmatach.framework.controller;

import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.khubla.pragmatach.framework.annotation.Controller;
import com.khubla.pragmatach.framework.annotation.Route;
import com.khubla.pragmatach.framework.api.PragmatachException;
import com.khubla.pragmatach.framework.scanner.AnnotationScanner;

/**
 * @author tome
 */
public class ControllerClasses {
   /**
    * logger
    */
   private static Logger logger = LoggerFactory.getLogger(ControllerClasses.class);
   /**
    * routes
    */
   private static Set<Method> routerMethods = new HashSet<Method>();
   /**
    * controllers
    */
   private static Set<Class<?>> controllers = new HashSet<Class<?>>();

   /**
    * do the annotations scan
    */
   public static void buildDB() throws PragmatachException {
      try {
         /*
          * controllers
          */
         final Set<Class<?>> annotatedControllers = AnnotationScanner.getAll(Controller.class);
         for (final Class<?> clazz : annotatedControllers) {
            controllers.add(clazz);
            logger.info("Found controller '" + clazz.getName() + "'");
         }
         /*
          * routes
          */
         final Set<Class<?>> controllersWithRoutes = AnnotationScanner.getAll(Route.class);
         for (final Class<?> clazz : controllersWithRoutes) {
            final Method[] methods = clazz.getMethods();
            if (null != methods) {
               for (final Method method : methods) {
                  if (method.isAnnotationPresent(Route.class)) {
                     if (method.isAnnotationPresent(Route.class)) {
                        routerMethods.add(method);
                        logger.info("Found router method '" + method.getDeclaringClass().getName() + ":" + method.getName() + "' with route specification '" + method.getAnnotation(Route.class).uri()
                              + "'");
                     }
                  }
               }
            }
         }
      } catch (final Exception e) {
         throw new PragmatachException("Exception in processAnnotations", e);
      }
   }

   public static Set<Class<?>> getControllers() {
      return controllers;
   }

   public static Set<Method> getRouterMethods() {
      return routerMethods;
   }

   public static void setControllers(Set<Class<?>> controllers) {
      ControllerClasses.controllers = controllers;
   }

   public static void setRouterMethods(Set<Method> routerMethods) {
      ControllerClasses.routerMethods = routerMethods;
   }
}
