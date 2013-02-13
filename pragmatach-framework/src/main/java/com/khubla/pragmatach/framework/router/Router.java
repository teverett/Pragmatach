package com.khubla.pragmatach.framework.router;

import com.khubla.pragmatach.framework.api.PragmatachException;
import com.khubla.pragmatach.framework.api.Request;
import com.khubla.pragmatach.framework.api.Response;

/**
 * @author tome
 */
public interface Router {
   /**
    * route GET to controller and return response
    */
   Response routeGET(Request request) throws PragmatachException;

   /**
    * route POST to controller and return response
    */
   Response routePOST(Request request) throws PragmatachException;
}
