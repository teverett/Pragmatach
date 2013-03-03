package com.khubla.pragmatach.framework.api;

/**
 * @author tome
 */
public interface Plugin {
   /**
    * if this plugin exposes an I8NProvider, return it here, otherwise return null
    */
   I8NProvider getI8NProvider();

   /**
    * name of the plugin
    */
   String getName();

   /**
    * this sets the PluginContext, so that the plugin can access framework-provided services, such as the ResourceLoader
    */
   void setPluginContext(PluginContext pluginContext);
}
