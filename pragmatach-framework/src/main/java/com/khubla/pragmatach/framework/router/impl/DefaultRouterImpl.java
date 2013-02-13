package com.khubla.pragmatach.framework.router.impl;

import com.khubla.pragmatach.framework.api.PragmatachException;
import com.khubla.pragmatach.framework.api.Request;
import com.khubla.pragmatach.framework.api.Response;
import com.khubla.pragmatach.framework.router.Router;

/**
 * @author tome
 */
public class DefaultRouterImpl implements Router {
   @Override
   public Response routeGET(Request request) throws PragmatachException {
      try {
         return null;
      } catch (final Exception e) {
         throw new PragmatachException("Exception in routeGET");
      }
   }

   @Override
   public Response routePOST(Request request) throws PragmatachException {
      try {
         return null;
      } catch (final Exception e) {
         throw new PragmatachException("Exception in routePOST");
      }
   }
}
