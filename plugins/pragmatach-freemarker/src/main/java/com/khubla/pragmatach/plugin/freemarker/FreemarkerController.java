package com.khubla.pragmatach.plugin.freemarker;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

import com.khubla.pragmatach.framework.api.PragmatachException;
import com.khubla.pragmatach.framework.api.Response;
import com.khubla.pragmatach.framework.controller.AbstractTemplateEngineController;
import com.khubla.pragmatach.framework.controller.BeanBoundController;
import com.khubla.pragmatach.framework.form.Form;
import com.khubla.pragmatach.framework.form.FormItem;

import freemarker.template.Configuration;
import freemarker.template.Template;

/**
 * @author tome
 */
public class FreemarkerController extends AbstractTemplateEngineController implements BeanBoundController {
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
            throw new PragmatachException("Unable to get template name for controller '" + getControllerName(this) + "'. Does it have an @View annotation?");
         }
      } catch (final Exception e) {
         throw new PragmatachException("Exception in getTemplate", e);
      }
   }

   @Override
   public Map<String, String> getPostFieldValues() throws PragmatachException {
      try {
         final Form form = Form.parse(getRequest().getHttpServletRequest());
         if (null != form) {
            final Map<String, String> ret = new HashMap<String, String>();
            for (final FormItem formItem : form.getItems().values()) {
               ret.put(formItem.getName(), formItem.getValue());
            }
            return ret;
         } else {
            return null;
         }
      } catch (final Exception e) {
         throw new PragmatachException("Exception in getPostFieldValues", e);
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
