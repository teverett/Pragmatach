package com.khubla.pragmatach.plugin.json;

import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import com.khubla.pragmatach.framework.api.PragmatachException;
import com.khubla.pragmatach.framework.controller.PragmatachController;
import com.khubla.pragmatach.framework.controller.impl.AbstractResponse;

/**
 * @author tome
 */
public class JSONResponse extends AbstractResponse {
   /**
    * the controller
    */
   private final PragmatachController pragmatachController;

   /**
    * ctor
    */
   public JSONResponse(Map<String, String> cacheHeaders, PragmatachController pragmatachController) {
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
   public void render(HttpServletResponse httpServletResponse) throws PragmatachException {
      try {
         PragmaticControllerSerializer.serialize(pragmatachController, httpServletResponse.getOutputStream());
      } catch (final Exception e) {
         throw new PragmatachException("Exception in render", e);
      }
   }
}
