package com.khubla.pragmatach.plugin.jsp;

import com.khubla.pragmatach.framework.api.PragmatachException;
import com.khubla.pragmatach.framework.api.Response;
import com.khubla.pragmatach.framework.controller.impl.FormPostBeanBoundController;

/**
 * @author tome
 */
public class JSPController extends FormPostBeanBoundController {
   /**
    * ctor
    */
   public JSPController() {
   }

   /**
    * render
    */
   public Response render() throws PragmatachException {
      try {
         return new JSPResponse(getCacheHeaders(), getTemplateName(), getTemplateContext());
      } catch (final Exception e) {
         throw new PragmatachException("Exception in render", e);
      }
   }
}
