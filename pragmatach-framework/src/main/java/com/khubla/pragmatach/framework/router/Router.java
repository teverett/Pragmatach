package com.khubla.pragmatach.framework.router;

import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.testng.log4testng.Logger;

import com.khubla.pragmatach.framework.annotation.Controller;
import com.khubla.pragmatach.framework.annotation.Route;
import com.khubla.pragmatach.framework.api.PragmatachException;
import com.khubla.pragmatach.framework.api.Request;
import com.khubla.pragmatach.framework.api.Response;
import com.khubla.pragmatach.framework.controller.BeanBoundController;
import com.khubla.pragmatach.framework.controller.PragmatachController;
import com.khubla.pragmatach.framework.controller.SessionScopedControllers;
import com.khubla.pragmatach.framework.controller.impl.NotFoundController;
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
    * get the resource path, taking off the servlet context path
    */
   private String getResourcePath(Request request) throws PragmatachException {
      try {
         final String uri = request.getURI();
         String ret = uri.substring(request.getHttpServletRequest().getContextPath().length());
         if (ret.endsWith("/")) {
            ret = ret.substring(0, ret.length() - 1);
         }
         if ((null == ret) || (ret.length() == 0)) {
            ret = "/";
         }
         return ret;
      } catch (final Exception e) {
         throw new PragmatachException("Exception in resourcePath", e);
      }
   }

   /**
    * invoke a request on a route
    */
   private Response invoke(PragmatachRoute pragmatachRoute, Request request, String[] parsedParameters) throws PragmatachException {
      try {
         /*
          * get the controller
          */
         final PragmatachController pragmatachController = getPragmatachControllerInstance(pragmatachRoute, request);
         /*
          * process form data?
          */
         if (request.getMethod() == Route.HttpMethod.post) {
            processFormData(pragmatachController);
         }
         /*
          * call the method
          */
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
   private boolean isRequestOnStaticAssetPath(String uri) {
      return (true == uri.startsWith(publicContextPath));
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
    * get Method that matches request
    */
   private Response route(List<PragmatachRoute> PragmatachRoutes, Request request) throws PragmatachException {
      try {
         /*
          * get the uri
          */
         final String uri = getResourcePath(request);
         /*
          * check if it's a static asset
          */
         if (null != publicContextPath) {
            if (isRequestOnStaticAssetPath(uri)) {
               final StaticResourceController staticResourceController = new StaticResourceController(uri, publicContextPath);
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
          * no match, return 404
          */
         final NotFoundController notFoundController = new NotFoundController();
         notFoundController.setRequest(request);
         return notFoundController.render();
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
