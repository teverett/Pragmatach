package com.khubla.pragmatach.framework.router;

import java.lang.reflect.Method;
import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.khubla.pragmatach.framework.annotation.Controller;
import com.khubla.pragmatach.framework.annotation.Route;
import com.khubla.pragmatach.framework.annotation.RouteParameter;
import com.khubla.pragmatach.framework.api.PragmatachException;
import com.khubla.pragmatach.framework.api.Request;
import com.khubla.pragmatach.framework.api.Response;
import com.khubla.pragmatach.framework.application.Application;
import com.khubla.pragmatach.framework.cache.LRUCache;
import com.khubla.pragmatach.framework.controller.BeanBoundController;
import com.khubla.pragmatach.framework.controller.PragmatachController;
import com.khubla.pragmatach.framework.controller.SessionScopedControllers;
import com.khubla.pragmatach.framework.controller.impl.NotFoundController;
import com.khubla.pragmatach.framework.lifecycle.LifecycleListener;

/**
 * @author tome
 */
public class Router {
   /**
    * logger
    */
   private final Logger logger = LoggerFactory.getLogger(this.getClass());
   /**
    * route cache. caches the top 100 routes.
    */
   private static final LRUCache<String, RouteFinder> routeCache = new LRUCache<String, RouteFinder>(getRouteCacheSize());
   /**
    * default size of the route cache
    */
   private static final int DEFAULT_ROUTE_CACHE_SIZE = 100;

   public static LRUCache<String, RouteFinder> getRoutecache() {
      return routeCache;
   }

   private static int getRouteCacheSize() {
      try {
         int ret = DEFAULT_ROUTE_CACHE_SIZE;
         final String configuredSize = Application.getConfiguration().getParameter("pragmatach.routecache.size");
         if (null != configuredSize) {
            ret = Integer.parseInt(configuredSize);
         }
         return ret;
      } catch (final Exception e) {
         return DEFAULT_ROUTE_CACHE_SIZE;
      }
   }

   /**
    * ctor
    */
   public Router() {
   }

   /**
    * find a route, either by matching, or by the cache
    */
   private RouteFinder findRoute(Request request) throws PragmatachException {
      try {
         /*
          * route is cached?
          */
         RouteFinder routeFinder = routeCache.get(request.getURI());
         if (null != routeFinder) {
            return routeFinder;
         } else {
            /*
             * try to find a route
             */
            routeFinder = new RouteFinder();
            if (true == routeFinder.match(request)) {
               routeCache.put(request.getURI(), routeFinder);
               return routeFinder;
            } else {
               return null;
            }
         }
      } catch (final Exception e) {
         throw new PragmatachException("Exception in findRoute", e);
      }
   }

   /**
    * get instance of Pragmatatch controller. Either a new request controller or an existing session controller
    */
   private PragmatachController getPragmatachControllerInstance(PragmatachRoute pragmatachRoute, Request request) throws PragmatachException {
      try {
         final Class<?> clazz = pragmatachRoute.getMethod().getDeclaringClass();
         final Controller controller = clazz.getAnnotation(Controller.class);
         if (null != controller) {
            if (controller.scope() == Controller.Scope.request) {
               /*
                * request scope
                */
               return pragmatachRoute.getControllerClazzInstance(request);
            } else {
               /*
                * session scope
                */
               PragmatachController pragmatachController = SessionScopedControllers.getController(request.getSession(), clazz);
               if (null == pragmatachController) {
                  pragmatachController = pragmatachRoute.getControllerClazzInstance(request);
                  SessionScopedControllers.setController(request.getSession(), pragmatachController);
               }
               return pragmatachController;
            }
         } else {
            throw new PragmatachException("Class '" + clazz + "' does not have an @Controller annotation");
         }
      } catch (final Exception e) {
         throw new PragmatachException("Exception in getPragmatachControllerInstance", e);
      }
   }

   /**
    * let any lifecycle listeners know we started a request
    */
   private void informLifecycleListenersOfBeginRequest(PragmatachRoute pragmatachRoute, Request request) {
      for (final LifecycleListener lifecycleListener : Application.getLifecyclelisteners().getLifecycleListeners()) {
         lifecycleListener.beginRequest(pragmatachRoute, request);
      }
   }

   /**
    * let any lifecycle listeners know we ended a request
    */
   private void informLifecycleListenersOfEndRequest(PragmatachRoute pragmatachRoute, Request request) {
      for (final LifecycleListener lifecycleListener : Application.getLifecyclelisteners().getLifecycleListeners()) {
         lifecycleListener.endRequest(pragmatachRoute, request);
      }
   }

   /**
    * invoke a request on a route
    */
   private Response invoke(PragmatachRoute pragmatachRoute, Request request, LinkedHashMap<String, String> parameterMap) throws PragmatachException {
      try {
         /*
          * get the controller
          */
         final PragmatachController pragmatachController = getPragmatachControllerInstance(pragmatachRoute, request);
         /*
          * set the route
          */
         pragmatachController.setPragmatachRoute(pragmatachRoute);
         /*
          * set the request
          */
         pragmatachController.setRequest(request);
         /*
          * set fields based on URL parameters
          */
         processParameterData(request, pragmatachController);
         /*
          * process form data?
          */
         if (request.getMethod() == Route.HttpMethod.post) {
            processFormData(pragmatachController);
         }
         /*
          * method and types
          */
         final Method method = pragmatachRoute.getMethod();
         final Class<?>[] methodParameterTypes = pragmatachRoute.getMethod().getParameterTypes();
         /*
          * wildcard?
          */
         if (false == pragmatachRoute.isWildcardRoute()) {
            if ((null == methodParameterTypes) || (methodParameterTypes.length == 0)) {
               /*
                * method takes no parameters
                */
               return (Response) method.invoke(pragmatachController);
            } else {
               /*
                * parameters to pass
                */
               final Object[] params = new Object[methodParameterTypes.length];
               /*
                * walk the annotations
                */
               int i = 0;
               for (final RouteParameter routeParameter : pragmatachRoute.getBoundRouteParameters()) {
                  /*
                   * get the name to bind
                   */
                  final String boundName = routeParameter.name();
                  /*
                   * that name is there?
                   */
                  if (parameterMap.containsKey(boundName)) {
                     /*
                      * set the value in the array
                      */
                     final String parameterValue = parameterMap.get(boundName);
                     params[i] = ConvertUtils.convert(parameterValue, methodParameterTypes[i]);
                  }
                  i++;
               }
               /*
                * invoke the method
                */
               return (Response) method.invoke(pragmatachController, params);
            }
         } else {
            /*
             * parameters to pass
             */
            final Object[] params = new Object[1];
            final String[] lst = new String[parameterMap.size()];
            int i = 0;
            for (final String p : parameterMap.keySet()) {
               lst[i++] = p;
            }
            params[0] = lst;
            /*
             * invoke the method
             */
            return (Response) method.invoke(pragmatachController, params);
         }
      } catch (final Exception e) {
         throw new PragmatachException("Exception in invoke", e);
      }
   }

   /**
    * set the controller fields based on the post
    */
   private void processFormData(PragmatachController pragmatachController) throws PragmatachException {
      try {
         /*
          * walk the fields
          */
         if (pragmatachController instanceof BeanBoundController) {
            final BeanBoundController beanBoundController = (BeanBoundController) pragmatachController;
            final Map<String, String> fieldValues = beanBoundController.getPostFieldValues();
            for (final String fieldName : fieldValues.keySet()) {
               /*
                * set the fields
                */
               BeanUtils.setProperty(pragmatachController, fieldName, fieldValues.get(fieldName));
            }
         }
      } catch (final Exception e) {
         throw new PragmatachException("Exception in processFormData", e);
      }
   }

   /**
    * set the controller fields based on the URL parameters
    */
   private void processParameterData(Request request, PragmatachController pragmatachController) throws PragmatachException {
      try {
         /*
          * walk the fields
          */
         final Map<String, String[]> parameterValues = request.getParameters();
         for (final String parameterName : parameterValues.keySet()) {
            /*
             * set the fields
             */
            BeanUtils.setProperty(pragmatachController, parameterName, parameterValues.get(parameterName));
         }
      } catch (final Exception e) {
         throw new PragmatachException("Exception in processFormData", e);
      }
   }

   /**
    * route request
    */
   public Response route(Request request) throws PragmatachException {
      try {
         /*
          * get route
          */
         final RouteFinder routeFinder = findRoute(request);
         if (null != routeFinder) {
            /*
             * note a message
             */
            logger.debug(request.getMethod() + " request for: " + request.getURI() + " routed to " + routeFinder.getPragmatachRoute().getDescription());
            /*
             * inform lifecycle listeners of start of request
             */
            informLifecycleListenersOfBeginRequest(routeFinder.getPragmatachRoute(), request);
            /*
             * invoke
             */
            try {
               return invoke(routeFinder.getPragmatachRoute(), request, routeFinder.getParameterMap());
            } catch (final Exception e) {
               throw e;
            } finally {
               /*
                * inform lifecycle listeners of end of request
                */
               informLifecycleListenersOfEndRequest(routeFinder.getPragmatachRoute(), request);
            }
         } else {
            /*
             * log a message
             */
            logger.info(request.getMethod() + " request for: " + request.getURI() + " could not be routed");
            /*
             * no match, return 404
             */
            final NotFoundController notFoundController = new NotFoundController();
            notFoundController.setRequest(request);
            return notFoundController.render();
         }
      } catch (final Exception e) {
         throw new PragmatachException("Exception in getRoute", e);
      }
   }
}
