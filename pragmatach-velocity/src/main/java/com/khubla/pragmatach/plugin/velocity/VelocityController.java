package com.khubla.pragmatach.plugin.velocity;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.io.IOUtils;

import com.khubla.pragmatach.framework.annotation.View;
import com.khubla.pragmatach.framework.api.PragmatachException;
import com.khubla.pragmatach.framework.api.Response;
import com.khubla.pragmatach.framework.controller.AbstractController;

/**
 * @author tome
 */
public class VelocityController extends AbstractController {
   /**
    * state variables
    */
   private static final String SESSION = "session";
   private static final String CONTROLLER = "controller";

   /**
    * ctor
    */
   public VelocityController() {
   }

   /**
    * get the Template
    */
   private String getTemplate() throws PragmatachException {
      try {
         final String templateName = getTemplateName();
         final InputStream templateInputStream = getResource(templateName);
         if (null != templateInputStream) {
            return getTemplateAsString(templateInputStream);
         } else {
            throw new Exception("Unable to load template '" + templateName + "'");
         }
      } catch (final Exception e) {
         throw new PragmatachException("Exception in getTemplate", e);
      }
   }

   private String getTemplateAsString(InputStream templateInputStream) throws PragmatachException {
      try {
         final ByteArrayOutputStream baos = new ByteArrayOutputStream();
         IOUtils.copy(templateInputStream, baos);
         return baos.toString();
      } catch (final Exception e) {
         throw new PragmatachException("Exception in getTemplateAsString", e);
      }
   }

   /**
    * get the name of the template from the annotation
    */
   private String getTemplateName() {
      final View view = this.getClass().getAnnotation(View.class);
      if (null != view) {
         final String template = view.view();
         if ((null != template) && (template.length() > 0)) {
            return template;
         }
      }
      return null;
   }

   /**
    * render
    */
   public Response render() throws PragmatachException {
      try {
         final String template = getTemplate();
         final Map<String, Object> context = new HashMap<String, Object>();
         context.put(SESSION, getRequest().getSession());
         context.put(CONTROLLER, this);
         return new VelocityResponse(getTemplateName(), template, context);
      } catch (final Exception e) {
         throw new PragmatachException("Exception in render", e);
      }
   }
}
