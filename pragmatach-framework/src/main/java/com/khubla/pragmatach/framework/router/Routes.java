package com.khubla.pragmatach.framework.router;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.khubla.pragmatach.framework.annotation.AnnotationsScanner;
import com.khubla.pragmatach.framework.annotation.Route;
import com.khubla.pragmatach.framework.api.PragmatachException;

/**
 * all routes
 * 
 * @author tome
 */
public class Routes {
   /**
    * singleton
    */
   private static Routes instance = null;

   /**
    * getter
    */
   public static Routes getInstance() throws PragmatachException {
      try {
         if (null == instance) {
            instance = new Routes();
         }
         return instance;
      } catch (final Exception e) {
         throw new PragmatachException(e);
      }
   }

   /**
    * GET routes
    */
   private final List<Method> GETMethods = new ArrayList<Method>();
   /**
    * POST routes
    */
   private final List<Method> POSTMethods = new ArrayList<Method>();

   /**
    * ctor
    */
   private Routes() throws PragmatachException {
      readRoutes();
   }

   public List<Method> getGETMethods() {
      return GETMethods;
   }

   public List<Method> getPOSTMethods() {
      return POSTMethods;
   }

   /**
    * read the routes file
    */
   private void readRoutes() throws PragmatachException {
      try {
         final Set<Method> routerMethods = AnnotationsScanner.getRouterMethods();
         for (final Method method : routerMethods) {
            if (method.getAnnotation(Route.class).method() == com.khubla.pragmatach.framework.annotation.Route.HttpMethod.get) {
               GETMethods.add(method);
            } else {
               POSTMethods.add(method);
            }
         }
      } catch (final Exception e) {
         throw new PragmatachException(e);
      }
   }
}
