package com.khubla.pragmatach.plugin.cluster.plugin;

import java.util.HashMap;
import java.util.Map;

import com.khubla.pragmatach.framework.annotation.Controller;
import com.khubla.pragmatach.framework.api.I8NProvider;
import com.khubla.pragmatach.framework.api.Plugin;
import com.khubla.pragmatach.framework.api.PluginContext;
import com.khubla.pragmatach.framework.api.PragmatachException;
import com.khubla.pragmatach.plugin.cluster.ClusteredControllers;

/**
 * @author tome
 */
public class PluginImpl implements Plugin {
   private PluginContext pluginContext;

   private String getClusteredControllerName(Class<?> clazz) throws PragmatachException {
      try {
         final Controller controller = clazz.getAnnotation(Controller.class);
         if (null != controller) {
            return controller.name();
         } else {
            throw new Exception("ClusteredController '" + clazz.getName() + "' does not have a @Controller annotation");
         }
      } catch (final Exception e) {
         throw new PragmatachException("Exception in getClusteredControllerName", e);
      }
   }

   @Override
   public I8NProvider getI8NProvider() {
      return null;
   }

   @Override
   public String getName() {
      return "Cluster";
   }

   public PluginContext getPluginContext() {
      return pluginContext;
   }

   @Override
   public Map<String, Object> getTemplateVariables() {
      try {
         final Map<String, Object> ret = new HashMap<String, Object>();
         /*
          * add all the cluster controllers by name
          */
         final ClusteredControllers clusteredControllers = ClusteredControllers.getInstance();
         if (null != clusteredControllers) {
            /*
             * walk clustered controllers
             */
            for (final Class<?> controllerClazz : clusteredControllers.getClusteredControllerClasses()) {
               getClusteredControllerName(controllerClazz);
            }
         }
         return ret;
      } catch (final Exception e) {
         e.printStackTrace();
         return null;
      }
   }

   @Override
   public void setPluginContext(PluginContext pluginContext) {
      this.pluginContext = pluginContext;
   }

   @Override
   public void startup() throws PragmatachException {
      try {
         /*
          * get all clustered controllers
          */
         final ClusteredControllers clusteredControllers = ClusteredControllers.getInstance();
         if (null != clusteredControllers) {
            /*
             * walk clustered controllers
             */
            for (final Class<?> controllerClazz : clusteredControllers.getClusteredControllerClasses()) {
               // final PragmatachController pragmatachController = (PragmatachController) controllerClazz.newInstance();
               // String name = getClusteredControllerName(controllerClazz);
            }
         }
      } catch (final Exception e) {
         throw new PragmatachException("Exception in startup", e);
      }
   }
}
