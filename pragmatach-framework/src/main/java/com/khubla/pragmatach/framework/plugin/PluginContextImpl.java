package com.khubla.pragmatach.framework.plugin;

import com.khubla.pragmatach.framework.api.PluginContext;
import com.khubla.pragmatach.framework.api.PragmatachException;
import com.khubla.pragmatach.framework.resourceloader.ResourceLoader;

/**
 * @author tome
 */
public class PluginContextImpl implements PluginContext {
   /**
    * resource loader
    */
   private final ResourceLoader resourceLoader;

   /**
    * ctor
    */
   public PluginContextImpl(ResourceLoader resourceLoader) {
      this.resourceLoader = resourceLoader;
   }

   @Override
   public ResourceLoader getResourceLoader() throws PragmatachException {
      return resourceLoader;
   }
}
