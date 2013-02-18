package com.khubla.pragmatach.plugin.freemarker;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

import com.khubla.pragmatach.framework.annotation.Controller;
import com.khubla.pragmatach.framework.annotation.View;
import com.khubla.pragmatach.framework.api.PragmatachException;
import com.khubla.pragmatach.framework.api.Response;
import com.khubla.pragmatach.framework.controller.AbstractController;
import com.khubla.pragmatach.framework.controller.BeanBoundController;
import com.khubla.pragmatach.framework.controller.PragmatachController;
import com.khubla.pragmatach.framework.controller.SessionScopedControllers;
import com.khubla.pragmatach.framework.form.Form;
import com.khubla.pragmatach.framework.form.FormItem;

import freemarker.template.Configuration;
import freemarker.template.Template;

/**
 * @author tome
 */
public class FreemarkerController extends AbstractController implements BeanBoundController {
   /**
    * state variables
    */
   private static final String SESSION = "session";
   private static final String CONTROLLER = "controller";

   /**
    * ctor
    */
   public FreemarkerController() {
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
    * get the Freemarker Template
    */
   private Template getTemplate() throws PragmatachException {
      try {
         final Configuration cfg = new Configuration();
         final PragmatachTemplateLoader pragmatachTemplateLoader = new PragmatachTemplateLoader(getRequest().getServletContext());
         cfg.setTemplateLoader(pragmatachTemplateLoader);
         final String templateName = getTemplateName();
         final InputStream templateInputStream = getResource(templateName);
         if (null != templateInputStream) {
            return new Template(templateName, new InputStreamReader(templateInputStream), cfg);
         } else {
            throw new Exception("Unable to load template '" + templateName + "'");
         }
      } catch (final Exception e) {
         throw new PragmatachException("Exception in getTemplate", e);
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
         final Template template = getTemplate();
         final Map<String, Object> context = new HashMap<String, Object>();
         context.put(SESSION, getRequest().getSession());
         context.put(CONTROLLER, this);
         /*
          * add the current controller by name
          */
         context.put(this.getClass().getAnnotation(Controller.class).name(), this);
         /*
          * add all session scoped controllers
          */
         Hashtable<Class<?>, PragmatachController> sessionControllerInstances = SessionScopedControllers.getMap(this.getRequest().getSession());
         if (null != sessionControllerInstances) {
            Enumeration<Class<?>> enumer = sessionControllerInstances.keys();
            while (enumer.hasMoreElements()) {
               Class<?> key = enumer.nextElement();
               Controller controller = key.getAnnotation(Controller.class);
               if (controller.scope() == Controller.Scope.session) {
                  PragmatachController pragmatachController = sessionControllerInstances.get(key);
                  context.put(controller.name(), pragmatachController);
               }
            }
         }
         return new FreemarkerResponse(template, context);
      } catch (final Exception e) {
         throw new PragmatachException("Exception in render", e);
      }
   }
}
