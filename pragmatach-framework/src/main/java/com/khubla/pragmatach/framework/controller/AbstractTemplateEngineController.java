package com.khubla.pragmatach.framework.controller;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

import org.apache.commons.io.IOUtils;

import com.khubla.pragmatach.framework.annotation.Controller;
import com.khubla.pragmatach.framework.annotation.View;
import com.khubla.pragmatach.framework.api.PragmatachException;
import com.khubla.pragmatach.framework.i8n.I8NProviders;
import com.khubla.pragmatach.framework.i8n.I8NResolver;
import com.khubla.pragmatach.framework.plugin.PluginDescriptor;
import com.khubla.pragmatach.framework.plugin.PluginDescriptors;

/**
 * @author tome
 */
public class AbstractTemplateEngineController extends AbstractController {
   /**
    * state variables
    */
   private static final String SESSION = "session";
   private static final String CONTROLLER = "controller";
   private static final String I8N = "i8n";

   /**
    * get the Template
    */
   protected String getTemplate() throws PragmatachException {
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
    * get the template's context
    */
   protected Map<String, Object> getTemplateContext() {
      final Map<String, Object> context = new HashMap<String, Object>();
      context.put(SESSION, getRequest().getSession());
      context.put(CONTROLLER, this);
      /*
       * i8n
       */
      context.put(I8N, new I8NResolver(I8NProviders.getInstance().providers));
      /*
       * add the current controller by name
       */
      context.put(this.getClass().getAnnotation(Controller.class).name(), this);
      /*
       * add all session scoped controllers
       */
      final Hashtable<Class<?>, PragmatachController> sessionControllerInstances = SessionScopedControllers.getMap(getRequest().getSession());
      if (null != sessionControllerInstances) {
         final Enumeration<Class<?>> enumer = sessionControllerInstances.keys();
         while (enumer.hasMoreElements()) {
            final Class<?> key = enumer.nextElement();
            final Controller controller = key.getAnnotation(Controller.class);
            if (controller.scope() == Controller.Scope.session) {
               final PragmatachController pragmatachController = sessionControllerInstances.get(key);
               context.put(controller.name(), pragmatachController);
            }
         }
      }
      /*
       * add all template variables contributed by plugins
       */
      final Map<String, PluginDescriptor> plugins = PluginDescriptors.getPlugins();
      if (null != plugins) {
         for (final PluginDescriptor pluginDescriptor : plugins.values()) {
            final Map<String, Object> vars = pluginDescriptor.getPlugin().getTemplateVariables();
            if (null != vars) {
               context.putAll(vars);
            }
         }
      }
      return context;
   }

   /**
    * get the name of the template from the annotation
    */
   protected String getTemplateName() {
      final View view = this.getClass().getAnnotation(View.class);
      if (null != view) {
         final String template = view.view();
         if ((null != template) && (template.length() > 0)) {
            return template;
         }
      }
      return null;
   }
}
