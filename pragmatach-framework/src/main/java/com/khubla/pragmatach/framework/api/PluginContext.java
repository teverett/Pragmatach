package com.khubla.pragmatach.framework.api;

import com.khubla.pragmatach.framework.resourceloader.ResourceLoader;

/**
 * The plugin context is passed to every plugin. It contains methods which enable the plugin to access framework serivices
 * 
 * @author tome
 */
public interface PluginContext {
   /**
    * gets the framework-provided resource loader
    */
   ResourceLoader getResourceLoader() throws PragmatachException;
}
