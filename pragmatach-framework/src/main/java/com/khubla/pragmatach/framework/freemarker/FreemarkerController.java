package com.khubla.pragmatach.framework.freemarker;

import java.util.HashMap;
import java.util.Map;

import com.khubla.pragmatach.framework.api.PragmatachException;
import com.khubla.pragmatach.framework.api.Request;
import com.khubla.pragmatach.framework.api.Response;
import com.khubla.pragmatach.framework.controller.AbstractController;

import freemarker.template.Configuration;
import freemarker.template.Template;

/**
 * @author tome
 */
public class FreemarkerController extends AbstractController {
   /**
    * state variables
    */
   private static final String SESSION = "session";
   private static final String CONTROLLER = "controller";

   /**
    * get the name of the template from the annotation
    */
   private String getTemplate() {
      final FreemarkerTemplate controller = this.getClass().getAnnotation(FreemarkerTemplate.class);
      if (null != controller) {
         final String template = controller.template();
         if ((null != template) && (template.length() > 0)) {
            return template;
         }
      }
      return null;
   }

   /**
    * render
    */
   public Response render(Request request) throws PragmatachException {
      try {
         final Configuration cfg = new Configuration();
         final String templateFile = getTemplate();
         final Template template = cfg.getTemplate(templateFile);
         final Map<String, Object> context = new HashMap<String, Object>();
         context.put(SESSION, request.getHttpServletRequest().getSession());
         context.put(CONTROLLER, this);
         return new FreemarkerResponse(template, null);
      } catch (final Exception e) {
         throw new PragmatachException("Exception in render", e);
      }
   }
}
