package com.khubla.pragmatach.plugin.xstream.plugin;

import com.khubla.pragmatach.framework.api.I8NProvider;
import com.khubla.pragmatach.framework.api.Plugin;
import com.khubla.pragmatach.framework.api.PluginContext;

/**
 * @author tome
 */
public class PluginImpl implements Plugin {
   private PluginContext pluginContext;

   @Override
   public I8NProvider getI8NProvider() {
      return null;
   }

   @Override
   public String getName() {
      return "XStream";
   }

   public PluginContext getPluginContext() {
      return pluginContext;
   }

   @Override
   public void setPluginContext(PluginContext pluginContext) {
      this.pluginContext = pluginContext;
   }
}
