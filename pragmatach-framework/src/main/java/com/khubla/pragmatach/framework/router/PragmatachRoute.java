package com.khubla.pragmatach.framework.router;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.khubla.pragmatach.framework.annotation.Route;
import com.khubla.pragmatach.framework.annotation.RouteParameter;
import com.khubla.pragmatach.framework.annotation.View;
import com.khubla.pragmatach.framework.api.PragmatachException;
import com.khubla.pragmatach.framework.api.Request;
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
    * logger
    */
   private final Logger logger = LoggerFactory.getLogger(this.getClass());
   /**
    * the @view annotation, if it exists
    */
   private final View view;

   /**
    * ctor
    */
   public PragmatachRoute(Method method) throws PragmatachException {
      this.method = method;
      view = findView();
      route = method.getAnnotation(Route.class);
      routeSpecification = new RouteSpecification(route.uri());
      /*
       * check the route
       */
      checkRouteSpecificationSanity();
   }

   /**
    * check that the route specification makes sense
    */
   private void checkRouteSpecificationSanity() throws PragmatachException {
      try {
         /*
          * wildcards are special
          */
         if (false == isWildcardRoute()) {
            /*
             * there are parameters?
             */
            if (false == ((method.getParameterTypes().length == 0) && (0 == routeSpecification.getIds().size()))) {
               /*
                * number of route specification ids must match number of method parameters
                */
               if (method.getParameterTypes().length != routeSpecification.getIds().size()) {
                  throw new PragmatachException("Parameter number mismatch.  Method '" + method.getDeclaringClass().getName() + ":" + method.getName() + "' has '" + method.getParameterTypes().length
                        + "' parameters, but route has '" + routeSpecification.getIds().size() + "'");
               }
               /*
                * check that the number of supplied variable bindings annotations matches the number of parameters
                */
               if (method.getParameterAnnotations().length != method.getParameterTypes().length) {
                  throw new PragmatachException("Annotation number mismatch.  Method '" + method.getDeclaringClass().getName() + ":" + method.getName() + "' has '"
                        + method.getParameterAnnotations().length + "' annotated parameters, but method has '" + method.getParameterTypes().length + "' parameters");
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
         } else {
            /*
             * method should have a single parameter
             */
            if (1 != method.getParameterTypes().length) {
               throw new PragmatachException("Parameter number mismatch.  Method '" + method.getDeclaringClass().getName() + ":" + method.getName()
                     + "' is bound to a wildcard route and must be a a single parameter");
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
    * find the view declaration for the route
    * <p>
    * Firstly, look for an annotation on the method, then the class.
    * </p>
    */
   protected View findView() throws PragmatachException {
      try {
         /*
          * first check the method
          */
         View ret = method.getAnnotation(View.class);
         if (null == ret) {
            /*
             * check the class
             */
            ret = method.getDeclaringClass().getAnnotation(View.class);
         }
         return ret;
      } catch (final Exception e) {
         throw new PragmatachException("Exception in findView", e);
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
    * get a class instance of the controller, and return a proxy that allows us to intercept method calls
    */
   public PragmatachController getControllerClazzInstance(Request request) throws PragmatachException {
      try {
         /*
          * get the actual class type
          */
         final Class<?> controllerClazz = method.getDeclaringClass();
         /*
          * enhance it
          */
         // final Enhancer enhancer = new Enhancer();
         // enhancer.setSuperclass(controllerClazz);
         // enhancer.setCallback(new ControllerMethodInterceptor());
         // return (PragmatachController) enhancer.create();
         return (PragmatachController) controllerClazz.newInstance();
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

   public View getView() {
      return view;
   }

   public boolean isWildcardRoute() {
      final String uri = route.uri();
      return (true == uri.endsWith("*"));
   }

   /**
    * returns true this route is more general than the passed route, false otherwise
    */
   public boolean scopes(PragmatachRoute pragmatachRoute) {
      if (null != pragmatachRoute) {
         /*
          * the root url is the most general
          */
         if (route.uri().compareTo("/*") == 0) {
            logger.debug(route.uri() + " scopes " + pragmatachRoute.getRoute().uri());
            return true;
         }
         if (isWildcardRoute()) {
            if (false == pragmatachRoute.isWildcardRoute()) {
               /*
                * wildcard routes always scope non-wildcard routes
                */
               logger.debug(route.uri() + " scopes " + pragmatachRoute.getRoute().uri());
               return true;
            } else {
               final String thisnonStarRoute = route.uri().substring(0, route.uri().length() - 1);
               final String othernonStarRoute = route.uri().substring(0, route.uri().length() - 1);
               if (othernonStarRoute.startsWith(thisnonStarRoute)) {
                  logger.debug(route.uri() + " scopes " + pragmatachRoute.getRoute().uri());
                  return true;
               } else {
                  logger.debug(route.uri() + " DOESN'T scope " + pragmatachRoute.getRoute().uri());
                  return false;
               }
            }
         } else {
            if (false == pragmatachRoute.isWildcardRoute()) {
               /*
                * check that the routes are on the same path
                */
               if (pragmatachRoute.route.uri().startsWith(route.uri())) {
                  /*
                   * the parameter count for *this* is less than the parameter count for the passed route
                   */
                  if (getSegmentCount() < pragmatachRoute.getSegmentCount()) {
                     logger.debug(route.uri() + " scopes " + pragmatachRoute.getRoute().uri());
                     return true;
                  }
               }
            } else {
               logger.debug(route.uri() + " DOESN'T scope " + pragmatachRoute.getRoute().uri());
               return false;
            }
         }
      }
      logger.debug(route.uri() + " DOESN'T scope " + pragmatachRoute.getRoute().uri());
      return false;
   }
}
