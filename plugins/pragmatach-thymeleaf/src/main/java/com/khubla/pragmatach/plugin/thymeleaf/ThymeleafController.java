package com.khubla.pragmatach.plugin.thymeleaf;

import org.thymeleaf.templateresolver.ITemplateResolver;

import com.khubla.pragmatach.framework.api.PragmatachException;
import com.khubla.pragmatach.framework.api.Response;
import com.khubla.pragmatach.framework.controller.BasicBeanBoundController;

/**
 * @author tome
 */
public class ThymeleafController extends BasicBeanBoundController {
   /**
    * ctor
    */
   public ThymeleafController() {
   }

   /**
    * render
    */
   public Response render() throws PragmatachException {
      try {
         final ITemplateResolver templateResolver = new PragmatachTemplateResolver(getRequest().getServletContext());
         return new ThymeleafResponse(getCacheHeaders(), getTemplateName(), getTemplateContext(), templateResolver);
      } catch (final Exception e) {
         throw new PragmatachException("Exception in render", e);
      }
   }
}
