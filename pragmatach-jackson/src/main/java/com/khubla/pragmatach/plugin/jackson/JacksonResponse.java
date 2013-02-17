package com.khubla.pragmatach.plugin.jackson;

import java.io.OutputStream;

import org.codehaus.jackson.map.ObjectMapper;

import com.khubla.pragmatach.framework.api.PragmatachException;
import com.khubla.pragmatach.framework.controller.AbstractResponse;
import com.khubla.pragmatach.framework.controller.PragmatachController;

/**
 * @author tome
 */
public class JacksonResponse extends AbstractResponse {
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
   public void render(OutputStream outputStream) throws PragmatachException {
      try {
         final ObjectMapper mapper = new ObjectMapper();
         mapper.writeValue(outputStream, pragmatachController);
      } catch (final Exception e) {
         throw new PragmatachException("Exception in render", e);
      }
   }
}
