package com.khubla.pragmatach.plugin.xstream.plugin;

import com.khubla.pragmatach.framework.api.I8NProvider;
import com.khubla.pragmatach.framework.api.Plugin;

/**
 * @author tome
 */
public class PluginImpl implements Plugin {
   @Override
   public String getName() {
      return "XStream";
   }

   @Override
   public I8NProvider getI8NProvider() {
      return null;
   }
}
