package com.khubla.pragmatach.framework.router;

import java.lang.reflect.Method;

import com.khubla.pragmatach.framework.annotation.Route;
import com.khubla.pragmatach.framework.api.PragmatachException;
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
   public int compareTo(PragmatachRoute arg0) {
      // TODO Auto-generated method stub
      return 0;
   }

   /**
    * get a class instance of the controller
    */
   public PragmatachController getControllerClazzInstance() throws PragmatachException {
      try {
         return (PragmatachController) method.getDeclaringClass().newInstance();
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

   public boolean matches(String path) {
      return false;
   }
}
