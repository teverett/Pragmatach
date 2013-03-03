package com.khubla.pragmatach.framework.plugin;

import com.khubla.pragmatach.framework.api.I8NProvider;
import com.khubla.pragmatach.framework.api.Plugin;

/**
 * @author tome
 */
public class PluginImpl implements Plugin {
   @Override
   public I8NProvider getI8NProvider() {
      return null;
   }

   @Override
   public String getName() {
      return "Framework";
   }
}
