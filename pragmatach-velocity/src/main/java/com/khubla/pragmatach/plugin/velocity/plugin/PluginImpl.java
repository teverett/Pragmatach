package com.khubla.pragmatach.plugin.velocity.plugin;

import com.khubla.pragmatach.framework.api.I8NProvider;
import com.khubla.pragmatach.framework.api.Plugin;

/**
 * @author tome
 */
public class PluginImpl implements Plugin {
   @Override
   public String getName() {
      return "Velocity";
   }

   @Override
   public I8NProvider getI8NProvider() {
      return null;
   }
}
