package com.khubla.pragmatach.plugin.statsd.plugin;

import java.util.Map;

import com.khubla.pragmatach.framework.api.I8NProvider;
import com.khubla.pragmatach.framework.api.Plugin;
import com.khubla.pragmatach.framework.api.PluginContext;
import com.khubla.pragmatach.framework.api.PragmatachException;
import com.khubla.pragmatach.framework.application.Application;
import com.khubla.pragmatach.plugin.statsd.StatsDClientImplementation;

/**
 * @author tome
 */
public class PluginImpl implements Plugin {
   /**
    * the context
    */
   private PluginContext pluginContext;
   /**
    * statsd
    */
   StatsDClientImplementation statsDClientImplementation = new StatsDClientImplementation();

   @Override
   public I8NProvider getI8NProvider() {
      return null;
   }

   @Override
   public String getName() {
      return "Statsd";
   }

   public PluginContext getPluginContext() {
      return pluginContext;
   }

   @Override
   public Map<String, Object> getTemplateVariables() {
      return null;
   }

   @Override
   public void setPluginContext(PluginContext pluginContext) {
      this.pluginContext = pluginContext;
   }

   @Override
   public void startup() throws PragmatachException {
      Application.getLifecyclelisteners().add(statsDClientImplementation);
   }
}
