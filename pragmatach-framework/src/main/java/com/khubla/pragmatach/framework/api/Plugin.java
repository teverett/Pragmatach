package com.khubla.pragmatach.framework.api;

import java.util.Map;

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
    * get template variables
    */
   Map<String, Object> getTemplateVariables();

   /**
    * this sets the PluginContext, so that the plugin can access framework-provided services, such as the ResourceLoader
    */
   void setPluginContext(PluginContext pluginContext);

   /**
    * startup
    */
   void startup() throws PragmatachException;
}
