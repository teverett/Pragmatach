package com.khubla.pragmatach.framework.router;

import java.util.List;

import org.testng.log4testng.Logger;

import com.khubla.pragmatach.framework.api.PragmatachException;
import com.khubla.pragmatach.framework.api.Request;
import com.khubla.pragmatach.framework.api.Response;
import com.khubla.pragmatach.framework.controller.PragmatachController;
import com.khubla.pragmatach.framework.controller.impl.TrivialResponse;

/**
 * @author tome
 */
public class Router {
   /**
    * logger
    */
   private final Logger logger = Logger.getLogger(this.getClass());

   /**
    * get Method that matches request
    */
   private PragmatachRoute getRoute(List<PragmatachRoute> PragmatachRoutes, String uri) throws PragmatachException {
      try {
         if (null != PragmatachRoutes) {
            for (final PragmatachRoute pragmatachRoute : PragmatachRoutes) {
               if (pragmatachRoute.matches(uri)) {
                  /*
                   * log
                   */
                  logger.info("Bound method '" + pragmatachRoute.getMethod().getDeclaringClass().getName() + ":" + pragmatachRoute.getMethod().getName() + "' to route '" + uri + "'");
                  /*
                   * done
                   */
                  return pragmatachRoute;
               }
            }
         }
         return null;
      } catch (final Exception e) {
         throw new PragmatachException("Exception in getRoute", e);
      }
   }

   /**
    * invoke a request on a route
    */
   private Response invoke(PragmatachRoute pragmatachRoute, Request request) throws PragmatachException {
      try {
         final PragmatachController pragmatachController = pragmatachRoute.getControllerClazzInstance();
         return (Response) pragmatachRoute.getMethod().invoke(pragmatachController, request);
      } catch (final Exception e) {
         throw new PragmatachException("Exception in invoke", e);
      }
   }

   public Response routeGET(Request request) throws PragmatachException {
      try {
         final String uri = request.getHttpServletRequest().getRequestURI();
         final PragmatachRoute pragmatachRoute = getRoute(PragmatachRoutes.getInstance().getGETRoutes(), uri);
         if (null != pragmatachRoute) {
            return invoke(pragmatachRoute, request);
         } else {
            return new TrivialResponse("Unable find controller for uri '" + uri + "'", 200);
         }
      } catch (final Exception e) {
         throw new PragmatachException("Exception in routeGET", e);
      }
   }

   public Response routePOST(Request request) throws PragmatachException {
      try {
         final String uri = request.getHttpServletRequest().getRequestURI();
         final PragmatachRoute pragmatachRoute = getRoute(PragmatachRoutes.getInstance().getPOSTRoutes(), uri);
         if (null != pragmatachRoute) {
            return invoke(pragmatachRoute, request);
         } else {
            return new TrivialResponse("Unable find controller for uri '" + uri + "'", 200);
         }
      } catch (final Exception e) {
         throw new PragmatachException("Exception in routePOST", e);
      }
   }
}
