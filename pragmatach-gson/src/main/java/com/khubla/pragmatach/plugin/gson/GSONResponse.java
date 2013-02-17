package com.khubla.pragmatach.plugin.gson;

import java.io.ByteArrayInputStream;
import java.io.OutputStream;

import org.apache.commons.io.IOUtils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.khubla.pragmatach.framework.api.PragmatachException;
import com.khubla.pragmatach.framework.api.Request;
import com.khubla.pragmatach.framework.controller.AbstractResponse;
import com.khubla.pragmatach.framework.controller.PragmatachController;

/**
 * @author tome
 */
public class GSONResponse extends AbstractResponse {
   /**
    * the controller
    */
   private final PragmatachController pragmatachController;

   /**
    * ctor
    */
   public GSONResponse(PragmatachController pragmatachController) {
      this.pragmatachController = pragmatachController;
   }

   @Override
   public void render(OutputStream outputStream) throws PragmatachException {
      try {
         final Gson gson = new GsonBuilder().setExclusionStrategies(new PragmatachExclusionStrategy(Request.class)).create();
         final String JSON = gson.toJson(pragmatachController);
         final ByteArrayInputStream bais = new ByteArrayInputStream(JSON.getBytes());
         IOUtils.copy(bais, outputStream);
      } catch (final Exception e) {
         throw new PragmatachException("Exception in render", e);
      }
   }
}
