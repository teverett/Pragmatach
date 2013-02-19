package com.khubla.pragmatach.framework.plugin;

import org.scannotation.archiveiterator.Filter;

/**
 * @author tome
 */
public class PluginFilter implements Filter {
   /**
    * the plugin properties file
    */
   private static final String PLUGIN_PROPERTIES = "META-INF/pragmatach-plugin.properties";

   @Override
   public boolean accepts(String name) {
      if (name.compareTo(PLUGIN_PROPERTIES) == 0) {
         return true;
      }
      return false;
   }
}
