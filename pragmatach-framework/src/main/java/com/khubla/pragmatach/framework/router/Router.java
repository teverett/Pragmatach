package com.khubla.pragmatach.framework.router;

import java.lang.reflect.Method;
import java.util.List;

import org.apache.commons.beanutils.ConvertUtils;
import org.testng.log4testng.Logger;

import com.khubla.pragmatach.framework.api.PragmatachException;
import com.khubla.pragmatach.framework.api.Request;
import com.khubla.pragmatach.framework.api.Response;
import com.khubla.pragmatach.framework.controller.PragmatachController;
import com.khubla.pragmatach.framework.controller.impl.RedirectController;
import com.khubla.pragmatach.framework.controller.impl.StaticResourceController;

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
   private Response invoke(PragmatachRoute pragmatachRoute, Request request, String[] parsedParameters) throws PragmatachException {
      try {
         final PragmatachController pragmatachController = pragmatachRoute.getControllerClazzInstance(request);
         final Method method = pragmatachRoute.getMethod();
         final Class<?>[] methodParameterTypes = pragmatachRoute.getMethod().getParameterTypes();
         if ((null == methodParameterTypes) || (methodParameterTypes.length == 0)) {
            return (Response) method.invoke(pragmatachController);
         } else {
            final Object[] params = new Object[methodParameterTypes.length];
            int i = 0;
            for (final Class<?> type : methodParameterTypes) {
               params[i] = ConvertUtils.convert(parsedParameters[i], type);
               i++;
            }
            return (Response) method.invoke(pragmatachController, params);
         }
      } catch (final Exception e) {
         throw new PragmatachException("Exception in invoke", e);
      }
   }

   /**
    * check if request is on the static asset path
    */
   private boolean isRequestOnStaticAssetPath(Request request) {
      return (true == request.getURI().startsWith(publicContextPath));
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
               /*
                * number of parameters matches?
                */
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
         String p = params;
         if (p.startsWith("/")) {
            p = p.substring(1);
         }
         if ((p.length() > 0) && (p.contains("/"))) {
            return p.split("/");
         } else {
            if (p.length() > 0) {
               return new String[] { p };
            } else {
               return null;
            }
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
          * get the uri
          */
         final String uri = request.getURI();
         /*
          * check if it's a static asset
          */
         if (null != publicContextPath) {
            if (isRequestOnStaticAssetPath(request)) {
               final StaticResourceController staticResourceController = new StaticResourceController(publicContextPath);
               staticResourceController.setRequest(request);
               return staticResourceController.render();
            }
         }
         /*
          * try to find a route
          */
         if (null != PragmatachRoutes) {
            for (final PragmatachRoute pragmatachRoute : PragmatachRoutes) {
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
                  return invoke(pragmatachRoute, request, parameters);
               }
            }
         }
         /*
          * no match, redirect to 404
          */
         final RedirectController redirectController = new RedirectController("/pragmatach/404", new String[] { uri });
         redirectController.setRequest(request);
         return redirectController.render();
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
