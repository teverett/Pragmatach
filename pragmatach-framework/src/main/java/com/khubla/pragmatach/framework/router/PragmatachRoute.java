package com.khubla.pragmatach.framework.router;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

import com.khubla.pragmatach.framework.annotation.Route;
import com.khubla.pragmatach.framework.api.PragmatachException;
import com.khubla.pragmatach.framework.api.Request;
import com.khubla.pragmatach.framework.controller.PragmatachController;

/**
 * a specific route
 * 
 * @author tome
 */
public class PragmatachRoute implements Comparable<PragmatachRoute> {
   /**
    * route annotation
    */
   private final Route route;
   /**
    * method
    */
   private final Method method;

   /**
    * ctor
    */
   public PragmatachRoute(Route route, Method method) throws PragmatachException {
      this.method = method;
      this.route = route;
   }

   @Override
   public int compareTo(PragmatachRoute pragmatachRoute) {
      if (pragmatachRoute.scopes(this)) {
         return -1;
      } else if (scopes(pragmatachRoute)) {
         return 1;
      } else {
         /*
          * neither route scopes the other, for the purposes of sorting they are equal
          */
         return 0;
      }
   }

   /**
    * get a class instance of the controller
    */
   public PragmatachController getControllerClazzInstance(Request request) throws PragmatachException {
      try {
         final Class<?> clazz = method.getDeclaringClass();
         final Constructor<?> ctor = clazz.getDeclaredConstructor(Request.class);
         return (PragmatachController) ctor.newInstance(request);
      } catch (final Exception e) {
         throw new PragmatachException("Exception in getControllerClazzInstance", e);
      }
   }

   public Method getMethod() {
      return method;
   }

   public Route getRoute() {
      return route;
   }

   /**
    * returns true this route is more general than the passed route, false otherwise
    */
   public boolean scopes(PragmatachRoute pragmatachRoute) {
      if (null != pragmatachRoute) {
         if ((pragmatachRoute.route.uri().startsWith(route.uri())) && (pragmatachRoute.route.uri().length() > route.uri().length())) {
            return true;
         }
      }
      return false;
   }
}
