package com.khubla.pragmatach.plugin.gson.plugin;

import java.util.Map;

import com.khubla.pragmatach.framework.api.I8NProvider;
import com.khubla.pragmatach.framework.api.Plugin;
import com.khubla.pragmatach.framework.api.PluginContext;
import com.khubla.pragmatach.framework.api.PragmatachException;

/**
 * @author tome
 */
public class PluginImpl implements Plugin {
   @Override
   public String getName() {
      return "GSON";
   }

   @Override
   public I8NProvider getI8NProvider() {
      return null;
   }

   private PluginContext pluginContext;

   @Override
   public void setPluginContext(PluginContext pluginContext) {
      this.pluginContext = pluginContext;
   }

   public PluginContext getPluginContext() {
      return pluginContext;
   }

   @Override
   public void startup() throws PragmatachException {
   }

   @Override
   public Map<String, Object> getTemplateVariables() {
      return null;
   }
}
