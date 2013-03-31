package com.khubla.pragmatach.framework.i8n;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.khubla.pragmatach.framework.api.I8NProvider;
import com.khubla.pragmatach.framework.plugin.PluginDescriptor;
import com.khubla.pragmatach.framework.plugin.PluginDescriptors;

/**
 * @author tome
 */
public class I8NProviders {
   /**
    * instance
    */
   private static I8NProviders instance = null;

   /**
    * singleton
    */
   public static I8NProviders getInstance() {
      if (null == instance) {
         instance = new I8NProviders();
      }
      return instance;
   }

   /**
    * logger
    */
   private final Logger logger = LoggerFactory.getLogger(this.getClass());
   /**
    * providers
    */
   public Set<I8NProvider> providers;

   /**
    * ctor
    */
   private I8NProviders() {
      final Map<String, PluginDescriptor> plugins = PluginDescriptors.getPlugins();
      if (null != plugins) {
         providers = new HashSet<I8NProvider>();
         for (final PluginDescriptor pluginDescriptor : plugins.values()) {
            final I8NProvider i8NProvider = pluginDescriptor.getPlugin().getI8NProvider();
            if (null != i8NProvider) {
               logger.info("Loaded i8N provider: " + i8NProvider.getName());
               providers.add(i8NProvider);
            }
         }
      }
   }
}
