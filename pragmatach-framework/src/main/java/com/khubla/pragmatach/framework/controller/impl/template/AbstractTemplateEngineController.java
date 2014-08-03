package com.khubla.pragmatach.framework.controller.impl.template;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.URI;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.IOUtils;

import com.khubla.pragmatach.framework.annotation.Controller;
import com.khubla.pragmatach.framework.annotation.View;
import com.khubla.pragmatach.framework.api.PragmatachException;
import com.khubla.pragmatach.framework.controller.PragmatachController;
import com.khubla.pragmatach.framework.controller.SessionScopedControllers;
import com.khubla.pragmatach.framework.controller.impl.AbstractController;
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
         if ((null != templateName) && (templateName.length() > 0)) {
            final InputStream templateInputStream = getResource(templateName);
            if (null != templateInputStream) {
               final String template = getTemplateAsString(templateInputStream);
               templateInputStream.close();
               return template;
            } else {
               throw new Exception("Unable to load template '" + templateName + "' for controller '" + AbstractController.getControllerName(this) + "'");
            }
         } else {
            throw new PragmatachException("Unable to get template name for controller '" + AbstractController.getControllerName(this) + "'. Does it have an @View annotation?");
         }
      } catch (final Exception e) {
         throw new PragmatachException("Exception in getTemplate", e);
      }
   }

   private String getTemplateAsString(InputStream templateInputStream) throws PragmatachException {
      try {
         final ByteArrayOutputStream baos = new ByteArrayOutputStream();
         IOUtils.copy(templateInputStream, baos);
         return baos.toString("UTF-8");
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
      context.put(AbstractController.getControllerName(this), this);
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
    * Get the name of the template to render from the @View annotation.
    */
   protected String getTemplateName() throws PragmatachException {
      try {
         final View view = getPragmatachRoute().getView();
         if (null != view) {
            return view.view();
         }
         return null;
      } catch (final Exception e) {
         throw new PragmatachException("Exception in getTemplateName", e);
      }
   }

   /**
    * get properly qualified url given the http, the context, etc
    */
   public String url_for(String page) throws PragmatachException {
      try {
         final HttpServletRequest httpServletRequest = getRequest().getHttpServletRequest();
         final URI uri = new URI(httpServletRequest.getRequestURL().toString()).resolve(page);
         return uri.toString();
      } catch (final Exception e) {
         throw new PragmatachException("Exception in getTemplateName", e);
      }
   }
}
