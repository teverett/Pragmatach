package com.khubla.pragmatach.plugin.gson;

import com.khubla.pragmatach.framework.api.PragmatachException;
import com.khubla.pragmatach.framework.api.Response;
import com.khubla.pragmatach.framework.controller.AbstractController;

/**
 * @author tome
 */
public class GSONController extends AbstractController {
   /**
    * ctor
    */
   public GSONController() {
   }

   /**
    * render
    */
   public Response render() throws PragmatachException {
      try {
         return new GSONResponse(this);
      } catch (final Exception e) {
         throw new PragmatachException("Exception in render", e);
      }
   }
}
