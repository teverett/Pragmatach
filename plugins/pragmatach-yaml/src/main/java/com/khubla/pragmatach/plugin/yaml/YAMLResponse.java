package com.khubla.pragmatach.plugin.yaml;

import java.io.OutputStream;
import java.util.Map;

import com.khubla.pragmatach.framework.api.PragmatachException;
import com.khubla.pragmatach.framework.controller.PragmatachController;
import com.khubla.pragmatach.framework.controller.impl.AbstractResponse;

/**
 * @author tome
 */
public class YAMLResponse extends AbstractResponse {
   /**
    * the controller
    */
   private final PragmatachController pragmatachController;

   /**
    * ctor
    */
   public YAMLResponse(Map<String, String> cacheHeaders, PragmatachController pragmatachController) {
      super(cacheHeaders);
      this.pragmatachController = pragmatachController;
   }

   @Override
   public String getContentType() throws PragmatachException {
      return "application/json";
   }

   @Override
   public Map<String, String> getHeaders() throws PragmatachException {
      return super.getCacheHeaders();
   }

   @Override
   public void render(OutputStream outputStream) throws PragmatachException {
      try {
         PragmatachYAML.toYAML(pragmatachController, outputStream);
      } catch (final Exception e) {
         throw new PragmatachException("Exception in render", e);
      }
   }
}
