package com.khubla.pragmatach.framework.controller.impl.template;

import java.util.Map;

import com.khubla.pragmatach.framework.api.PragmatachException;
import com.khubla.pragmatach.framework.api.Response;
import com.khubla.pragmatach.framework.controller.impl.AbstractController;

/**
 * @author tome
 */
public abstract class SimpleTemplateController extends AbstractController {
   public Response template(String templateName, Map<String, String> parameters) throws PragmatachException {
      try {
         return new SimpleTemplateResponse(getCacheHeaders(), templateName, parameters);
      } catch (final Exception e) {
         throw new PragmatachException("Exception in template", e);
      }
   }
}
