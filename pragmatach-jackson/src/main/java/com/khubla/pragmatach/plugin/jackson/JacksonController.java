package com.khubla.pragmatach.plugin.jackson;

import com.khubla.pragmatach.framework.api.PragmatachException;
import com.khubla.pragmatach.framework.api.Response;
import com.khubla.pragmatach.framework.controller.AbstractController;

/**
 * @author tome
 */
public class JacksonController extends AbstractController {
   /**
    * ctor
    */
   public JacksonController() {
   }

   /**
    * render
    */
   public Response render() throws PragmatachException {
      try {
         return new JacksonResponse(this);
      } catch (final Exception e) {
         throw new PragmatachException("Exception in render", e);
      }
   }
}
