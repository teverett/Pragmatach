package com.khubla.pragmatach.framework.router.impl;

import java.util.List;

import com.khubla.pragmatach.framework.api.PragmatachException;
import com.khubla.pragmatach.framework.api.Request;
import com.khubla.pragmatach.framework.api.Response;
import com.khubla.pragmatach.framework.api.TrivialResponse;
import com.khubla.pragmatach.framework.controller.Controller;
import com.khubla.pragmatach.framework.router.Route;
import com.khubla.pragmatach.framework.router.Router;
import com.khubla.pragmatach.framework.router.Routes;

/**
 * @author tome
 */
public class DefaultRouterImpl implements Router {
   /**
    * get instance of controller for route
    */
   private Controller getControllerInstance(Route route) throws PragmatachException {
      try {
         if (null != route) {
            return (Controller) Class.forName(route.getController()).newInstance();
         } else {
            return null;
         }
      } catch (final Exception e) {
         throw new PragmatachException("Exception in getControllerInstance", e);
      }
   }

   /**
    * get route that matches request
    */
   private Route getRoute(List<Route> routes, String path) throws PragmatachException {
      try {
         if (null != routes) {
            for (final Route route : routes) {
               if (route.matches(path)) {
                  return route;
               }
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
         final Route route = getRoute(Routes.getInstance().getGETRoutes(), path);
         if (null != route) {
            final Controller controller = getControllerInstance(route);
            return controller.render(request);
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
