package com.khubla.pragmatach.framework.router.impl;

import java.lang.reflect.Method;
import java.util.List;

import com.khubla.pragmatach.framework.api.PragmatachException;
import com.khubla.pragmatach.framework.api.Request;
import com.khubla.pragmatach.framework.api.Response;
import com.khubla.pragmatach.framework.api.TrivialResponse;
import com.khubla.pragmatach.framework.router.Router;
import com.khubla.pragmatach.framework.router.Routes;

/**
 * @author tome
 */
public class DefaultRouterImpl implements Router {
   /**
    * get Method that matches request
    */
   private Method getMethod(List<Method> routeMethods, String path) throws PragmatachException {
      try {
         if (null != routeMethods) {
            for (final Method method : routeMethods) {
               return null;
               // if (route.path().compareTo(path) == 0) {
               // return route;
               // }
            }
         }
         return null;
      } catch (final Exception e) {
         throw new PragmatachException("Exception in getRoute", e);
      }
   }

   @Override
   public Response routeGET(Request request) throws PragmatachException {
      try {
         final String path = request.getHttpServletRequest().getContextPath();
         final Method method = getMethod(Routes.getInstance().getGETMethods(), path);
         if (null != method) {
            // final PragmatachController controller = route.getControllerClazzInstance();
            // return controller.render(request);
            return null;
         } else {
            return new TrivialResponse("Unable find controller for route '" + path + "'", 200);
         }
      } catch (final Exception e) {
         throw new PragmatachException("Exception in routeGET", e);
      }
   }

   @Override
   public Response routePOST(Request request) throws PragmatachException {
      try {
         return null;
      } catch (final Exception e) {
         throw new PragmatachException("Exception in routePOST", e);
      }
   }
}
