package com.khubla.pragmatach.framework.router;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import com.khubla.pragmatach.framework.annotation.Route;
import com.khubla.pragmatach.framework.annotation.RouteParameter;
import com.khubla.pragmatach.framework.api.PragmatachException;
import com.khubla.pragmatach.framework.api.Request;
import com.khubla.pragmatach.framework.controller.Controllers;
import com.khubla.pragmatach.framework.controller.PragmatachController;
import com.khubla.pragmatach.framework.url.RouteSpecification;

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
    * route specification
    */
   private final RouteSpecification routeSpecification;

   /**
    * ctor
    */
   public PragmatachRoute(Method method) throws PragmatachException {
      this.method = method;
      route = method.getAnnotation(Route.class);
      routeSpecification = new RouteSpecification(route.uri());
      /*
       * check that the route sp
       */
      checkRouteSpecificationSanity();
   }

   /**
    * check that the route specification makes sense
    */
   private void checkRouteSpecificationSanity() throws PragmatachException {
      try {
         /*
          * there are parameters?
          */
         if (false == ((method.getParameterTypes().length == 0) && (0 == routeSpecification.getIds().size()))) {
            /*
             * number of route specification ids must match number of method parameters
             */
            if (method.getParameterTypes().length != routeSpecification.getIds().size()) {
               throw new PragmatachException("Parameter number mismatch");
            }
            /*
             * check that the number of supplied variable binding annotations matches the number of parameters
             */
            if (method.getParameterAnnotations().length != method.getParameterTypes().length) {
               throw new PragmatachException("Annotation number mismatch");
            }
            /*
             * each bound name in the method must match up with a id in the route specification
             */
            final List<RouteParameter> routeParameters = getBoundRouteParameters();
            if ((null != routeParameters) && (routeParameters.size() > 0)) {
               for (final RouteParameter routeParameter : routeParameters) {
                  final String boundName = routeParameter.name();
                  if (false == routeSpecification.getIds().contains(boundName)) {
                     throw new PragmatachException("Route specfication does not specify an id for bound variable '" + boundName + "'");
                  }
               }
            } else {
               throw new PragmatachException("Bound parameter number mismatch. '" + routeSpecification.getIds().size() + "' annotations were expected but none were found");
            }
         }
      } catch (final Exception e) {
         throw new PragmatachException("Exception in checkRouteSpecificationSanity", e);
      }
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
    * get bound parameters annotations, in order of the parameters in the method
    */
   public List<RouteParameter> getBoundRouteParameters() {
      final Annotation[][] parameterAnnotations = method.getParameterAnnotations();
      if (parameterAnnotations.length > 0) {
         final List<RouteParameter> ret = new ArrayList<RouteParameter>();
         for (final Annotation[] p : parameterAnnotations) {
            for (final Annotation annotation : p) {
               if (annotation.annotationType() == RouteParameter.class) {
                  ret.add((RouteParameter) annotation);
                  break;
               }
            }
         }
         if (ret.size() > 0) {
            return ret;
         } else {
            return null;
         }
      } else {
         return null;
      }
   }

   /**
    * get a class instance of the controller
    */
   public PragmatachController getControllerClazzInstance(Request request) throws PragmatachException {
      try {
         final Class<?> clazz = method.getDeclaringClass();
         return Controllers.getInstance(clazz);
      } catch (final Exception e) {
         throw new PragmatachException("Exception in getControllerClazzInstance", e);
      }
   }

   public String getDescription() {
      return getRoute().uri() + " " + getMethod().getDeclaringClass().getName() + ":" + getMethod().getName();
   }

   public Method getMethod() {
      return method;
   }

   /**
    * number of method parameters
    */
   public int getParameterCount() {
      return method.getParameterTypes().length;
   }

   public Route getRoute() {
      return route;
   }

   public RouteSpecification getRouteSpecification() {
      return routeSpecification;
   }

   /**
    * number of route segments
    */
   public int getSegmentCount() {
      if (null != routeSpecification.getSegments()) {
         return routeSpecification.getSegments().size();
      }
      return 0;
   }

   /**
    * returns true this route is more general than the passed route, false otherwise
    */
   public boolean scopes(PragmatachRoute pragmatachRoute) {
      /*
       * the root url is the most general
       */
      if (route.uri().compareTo("/") == 0) {
         return true;
      }
      if (null != pragmatachRoute) {
         /*
          * check that the routes are on the same path
          */
         if (route.uri().startsWith(pragmatachRoute.route.uri())) {
            /*
             * the parameter count for *this* is less than the parameter count for the passed route
             */
            if (getSegmentCount() < pragmatachRoute.getSegmentCount()) {
               return true;
            }
         }
      }
      return false;
   }
}
