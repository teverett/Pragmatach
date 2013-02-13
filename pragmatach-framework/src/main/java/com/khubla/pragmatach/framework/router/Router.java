package com.khubla.pragmatach.framework.router;

import java.util.List;

import com.khubla.pragmatach.framework.api.PragmatachException;
import com.khubla.pragmatach.framework.api.Request;
import com.khubla.pragmatach.framework.api.Response;
import com.khubla.pragmatach.framework.api.TrivialResponse;
import com.khubla.pragmatach.framework.controller.PragmatachController;

/**
 * @author tome
 */
public class Router {
   /**
    * get Method that matches request
    */
   private PragmatachRoute getRoute(List<PragmatachRoute> PragmatachRoutes, String path) throws PragmatachException {
      try {
         if (null != PragmatachRoutes) {
            for (final PragmatachRoute pragmatachRoute : PragmatachRoutes) {
               if (pragmatachRoute.matches(path)) {
                  return pragmatachRoute;
               }
            }
         }
         return null;
      } catch (final Exception e) {
         throw new PragmatachException("Exception in getRoute", e);
      }
   }

   public Response routeGET(Request request) throws PragmatachException {
      try {
         final String path = request.getHttpServletRequest().getContextPath();
         final PragmatachRoute pragmatachRoute = getRoute(Routes.getInstance().getGETRoutes(), path);
         if (null != pragmatachRoute) {
            final PragmatachController controller = pragmatachRoute.getControllerClazzInstance();
            return controller.render(request);
         } else {
            return new TrivialResponse("Unable find controller for route '" + path + "'", 200);
         }
      } catch (final Exception e) {
         throw new PragmatachException("Exception in routeGET", e);
      }
   }

   public Response routePOST(Request request) throws PragmatachException {
      try {
         return null;
      } catch (final Exception e) {
         throw new PragmatachException("Exception in routePOST", e);
      }
   }
}
