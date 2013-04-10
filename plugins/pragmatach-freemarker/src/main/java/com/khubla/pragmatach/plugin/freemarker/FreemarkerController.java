package com.khubla.pragmatach.plugin.freemarker;

import java.io.InputStream;
import java.io.InputStreamReader;

import com.khubla.pragmatach.framework.api.PragmatachException;
import com.khubla.pragmatach.framework.api.Response;
import com.khubla.pragmatach.framework.controller.impl.AbstractController;
import com.khubla.pragmatach.framework.controller.impl.FormPostBeanBoundController;

import freemarker.template.Configuration;
import freemarker.template.Template;

/**
 * @author tome
 */
public class FreemarkerController extends FormPostBeanBoundController {
   /**
    * ctor
    */
   public FreemarkerController() {
   }

   /**
    * get the Freemarker Template
    */
   private Template getFreemarkerTemplate() throws PragmatachException {
      try {
         final Configuration configuration = new Configuration();
         configuration.setLocalizedLookup(false);
         final PragmatachTemplateLoader pragmatachTemplateLoader = new PragmatachTemplateLoader(getRequest().getServletContext());
         configuration.setTemplateLoader(pragmatachTemplateLoader);
         final String templateName = getTemplateName();
         if (null != templateName) {
            final InputStream templateInputStream = getResource(templateName);
            if (null != templateInputStream) {
               return new Template(templateName, new InputStreamReader(templateInputStream), configuration);
            } else {
               throw new Exception("Unable to load template '" + templateName + "'");
            }
         } else {
            throw new PragmatachException("Unable to get template name for controller '" + AbstractController.getControllerName(this) + "'. Does it have an @View annotation?");
         }
      } catch (final Exception e) {
         throw new PragmatachException("Exception in getTemplate", e);
      }
   }

   /**
    * render
    */
   public Response render() throws PragmatachException {
      try {
         final Template template = getFreemarkerTemplate();
         return new FreemarkerResponse(getCacheHeaders(), template, getTemplateContext());
      } catch (final Exception e) {
         throw new PragmatachException("Exception in render", e);
      }
   }
}
