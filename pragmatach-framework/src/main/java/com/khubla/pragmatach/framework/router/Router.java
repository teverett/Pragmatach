package com.khubla.pragmatach.framework.router;

import java.lang.reflect.Method;
import java.util.List;

import org.testng.log4testng.Logger;

import com.khubla.pragmatach.framework.api.PragmatachException;
import com.khubla.pragmatach.framework.api.Request;
import com.khubla.pragmatach.framework.api.Response;
import com.khubla.pragmatach.framework.controller.AbstractController;
import com.khubla.pragmatach.framework.controller.PragmatachController;
import com.khubla.pragmatach.framework.controller.impl.StaticResourceController;
import com.khubla.pragmatach.framework.controller.impl.TrivialController;

/**
 * @author tome
 */
public class Router {
   /**
    * static asset path
    */
   private final String publicContextPath;
   /**
    * logger
    */
   private final Logger logger = Logger.getLogger(this.getClass());

   /**
    * ctor
    */
   public Router() {
      publicContextPath = null;
   }

   /**
    * ctor
    */
   public Router(String publicContextPath) {
      this.publicContextPath = publicContextPath;
   }

   /**
    * invoke a request on a route
    */
   private Response invoke(PragmatachRoute pragmatachRoute, Request request) throws PragmatachException {
      try {
         final PragmatachController pragmatachController = pragmatachRoute.getControllerClazzInstance(request);
         final Method method = pragmatachRoute.getMethod();
         return (Response) method.invoke(pragmatachController);
      } catch (final Exception e) {
         throw new PragmatachException("Exception in invoke", e);
      }
   }

   /**
    * check if request is on the static asset path
    */
   private boolean isRequestOnStaticAssetPath(Request request) {
      return (true == request.getHttpServletRequest().getRequestURI().startsWith(publicContextPath));
   }

   /**
    * in order to match it has to be the right beginning of the path, and what follows the path has to be the right parameters. returns the parameters if it matches, null otherwise
    */
   public String[] matchRoute(PragmatachRoute pragmatachRoute, String uri) throws PragmatachException {
      try {
         final String routeURI = pragmatachRoute.getRoute().uri();
         if (uri.startsWith(routeURI)) {
            final String parametersPartOfURI = uri.substring(routeURI.length());
            final String[] parsedParameters = parseParameters(parametersPartOfURI);
            final int methodParameterCount = pragmatachRoute.getMethod().getParameterTypes().length;
            if (null != parsedParameters) {
               if (methodParameterCount == parsedParameters.length) {
                  return parsedParameters;
               } else {
                  return null;
               }
            } else {
               if (methodParameterCount == 0) {
                  return new String[] { "" };
               } else {
                  return null;
               }
            }
         } else {
            return null;
         }
      } catch (final Exception e) {
         throw new PragmatachException("Exception in matchRoute", e);
      }
   }

   /**
    * parse the URL parameters
    */
   private String[] parseParameters(String params) throws PragmatachException {
      try {
         if ((params.length() > 0) && (params.contains("/"))) {
            return params.split("/");
         } else {
            return null;
         }
      } catch (final Exception e) {
         throw new PragmatachException("Exception in parseParameters", e);
      }
   }

   /**
    * get Method that matches request
    */
   private Response route(List<PragmatachRoute> PragmatachRoutes, Request request) throws PragmatachException {
      try {
         /*
          * check if it's a static asset
          */
         if (null != publicContextPath) {
            if (isRequestOnStaticAssetPath(request)) {
               final StaticResourceController staticResourceController = new StaticResourceController(publicContextPath, request);
               return staticResourceController.render();
            }
         }
         /*
          * try to find a route
          */
         if (null != PragmatachRoutes) {
            for (final PragmatachRoute pragmatachRoute : PragmatachRoutes) {
               final String uri = request.getHttpServletRequest().getRequestURI();
               /*
                * get the parameters
                */
               final String[] parameters = matchRoute(pragmatachRoute, uri);
               if (null != parameters) {
                  /*
                   * log
                   */
                  logger.info("Bound method '" + pragmatachRoute.getMethod().getDeclaringClass().getName() + ":" + pragmatachRoute.getMethod().getName() + "' to route '" + uri + "'");
                  /*
                   * invoke
                   */
                  return invoke(pragmatachRoute, request);
               }
            }
         }
         /*
          * no match it is 404
          */
         return new TrivialController(request, "Unable to find resource ''", AbstractController.HTTP_NOTFOUND).render();
      } catch (final Exception e) {
         throw new PragmatachException("Exception in getRoute", e);
      }
   }

   public Response routeGET(Request request) throws PragmatachException {
      try {
         return route(PragmatachRoutes.getInstance().getGETRoutes(), request);
      } catch (final Exception e) {
         throw new PragmatachException("Exception in routeGET", e);
      }
   }

   public Response routePOST(Request request) throws PragmatachException {
      try {
         return route(PragmatachRoutes.getInstance().getPOSTRoutes(), request);
      } catch (final Exception e) {
         throw new PragmatachException("Exception in routePOST", e);
      }
   }
}
