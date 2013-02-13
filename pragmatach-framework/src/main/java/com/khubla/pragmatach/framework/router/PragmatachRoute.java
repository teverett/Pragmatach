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

   /**
    * in order to match it has to be the right beginning of the path, and what follows the path has to be the right parameters.
    */
   public boolean matches(String uri) throws PragmatachException {
      try {
         final String routeURI = route.uri();
         if (uri.startsWith(routeURI)) {
            final String parametersPartOfURI = uri.substring(routeURI.length());
            final String[] parsedParameters = parseParameters(parametersPartOfURI);
            final int methodParameterCount = method.getParameterTypes().length;
            if (null != parsedParameters) {
               if (methodParameterCount == parsedParameters.length) {
                  return true;
               } else {
                  return false;
               }
            } else {
               if (methodParameterCount == 0) {
                  return true;
               } else {
                  return false;
               }
            }
         } else {
            return false;
         }
      } catch (final Exception e) {
         throw new PragmatachException("Exception in matches", e);
      }
   }

   /**
    * parse the URL parameters
    */
   private String[] parseParameters(String params) throws PragmatachException {
      try {
         return params.split("/");
      } catch (final Exception e) {
         throw new PragmatachException("Exception in parseParameters", e);
      }
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
