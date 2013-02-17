package com.khubla.pragmatach.plugin.jackson;

import java.io.OutputStream;
import java.util.Map;

import org.codehaus.jackson.map.ObjectMapper;

import com.khubla.pragmatach.framework.api.PragmatachException;
import com.khubla.pragmatach.framework.api.Response;
import com.khubla.pragmatach.framework.controller.PragmatachController;

/**
 * @author tome
 */
public class JacksonResponse implements Response {
   /**
    * the controller
    */
   private final PragmatachController pragmatachController;

   /**
    * ctor
    */
   public JacksonResponse(PragmatachController pragmatachController) {
      this.pragmatachController = pragmatachController;
   }

   @Override
   public Map<String, String> getHeaders() throws PragmatachException {
      return null;
   }

   @Override
   public int getHTTPCode() {
      return 200;
   }

   @Override
   public void render(OutputStream outputStream) throws PragmatachException {
      try {
         ObjectMapper mapper = new ObjectMapper();
         mapper.writeValue(outputStream, pragmatachController);
      } catch (final Exception e) {
         throw new PragmatachException("Exception in render", e);
      }
   }
}
