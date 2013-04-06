package com.khubla.pragmatach.plugin.json;

import java.io.ByteArrayInputStream;

import com.khubla.pragmatach.framework.api.PragmatachException;
import com.khubla.pragmatach.framework.api.Response;
import com.khubla.pragmatach.framework.controller.AbstractController;
import com.khubla.pragmatach.framework.controller.BeanBoundController;

/**
 * @author tome
 */
public class JSONController extends AbstractController implements BeanBoundController {
   /**
    * ctor
    */
   public JSONController() {
   }

   @Override
   public void populateController() throws PragmatachException {
      PragmaticControllerSerializer.deserialize(this, new ByteArrayInputStream(getRequest().getPostBody().getBytes()));
   }

   /**
    * for testing purposes
    */
   public void populateController(String JSON) throws PragmatachException {
      PragmaticControllerSerializer.deserialize(this, new ByteArrayInputStream(JSON.getBytes()));
   }

   /**
    * render
    */
   public Response render() throws PragmatachException {
      try {
         return new JSONResponse(getCacheHeaders(), this);
      } catch (final Exception e) {
         throw new PragmatachException("Exception in render", e);
      }
   }
}
