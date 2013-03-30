package com.khubla.pragmatach.plugin.velocity;

import com.khubla.pragmatach.framework.api.PragmatachException;
import com.khubla.pragmatach.framework.api.Response;
import com.khubla.pragmatach.framework.controller.BasicBeanBoundController;

/**
 * @author tome
 */
public class VelocityController extends BasicBeanBoundController {
   /**
    * ctor
    */
   public VelocityController() {
   }

   /**
    * render
    */
   public Response render() throws PragmatachException {
      try {
         final String template = getTemplate();
         return new VelocityResponse(getCacheHeaders(), getTemplateName(), template, getTemplateContext());
      } catch (final Exception e) {
         throw new PragmatachException("Exception in render", e);
      }
   }
}
