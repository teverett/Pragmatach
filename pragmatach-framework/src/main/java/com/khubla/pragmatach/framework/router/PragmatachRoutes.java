package com.khubla.pragmatach.framework.router;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import com.khubla.pragmatach.framework.annotation.AnnotationsScanner;
import com.khubla.pragmatach.framework.annotation.Route;
import com.khubla.pragmatach.framework.api.PragmatachException;

/**
 * all routes, both GET and POST, sorted.
 * 
 * @author tome
 */
public class PragmatachRoutes {
   /**
    * singleton
    */
   private static PragmatachRoutes instance = null;

   /**
    * getter
    */
   public static PragmatachRoutes getInstance() throws PragmatachException {
      try {
         if (null == instance) {
            instance = new PragmatachRoutes();
         }
         return instance;
      } catch (final Exception e) {
         throw new PragmatachException(e);
      }
   }

   /**
    * GET routes
    */
   private final List<PragmatachRoute> GETRoutes = new ArrayList<PragmatachRoute>();
   /**
    * POST routes
    */
   private final List<PragmatachRoute> POSTRoutes = new ArrayList<PragmatachRoute>();

   /**
    * ctor
    */
   private PragmatachRoutes() throws PragmatachException {
      readRoutes();
   }

   public List<PragmatachRoute> getGETRoutes() {
      return GETRoutes;
   }

   public List<PragmatachRoute> getPOSTRoutes() {
      return POSTRoutes;
   }

   /**
    * read the routes file
    */
   private void readRoutes() throws PragmatachException {
      try {
         /*
          * get the routes
          */
         final Set<Method> routerMethods = AnnotationsScanner.getRouterMethods();
         for (final Method method : routerMethods) {
            final Route route = method.getAnnotation(Route.class);
            final Route.HttpMethod httpMethod = route.method();
            if (httpMethod == com.khubla.pragmatach.framework.annotation.Route.HttpMethod.get) {
               GETRoutes.add(new PragmatachRoute(method));
            } else {
               POSTRoutes.add(new PragmatachRoute(method));
            }
         }
         /*
          * sort the routes
          */
         Collections.sort(GETRoutes);
         Collections.sort(POSTRoutes);
      } catch (final Exception e) {
         throw new PragmatachException(e);
      }
   }
}
