package com.khubla.pragmatach.plugin.i8n.plugin;

import com.khubla.pragmatach.framework.api.I8NProvider;
import com.khubla.pragmatach.framework.api.Plugin;
import com.khubla.pragmatach.plugin.i8n.I8NImpl;

/**
 * @author tome
 */
public class PluginImpl implements Plugin {
   @Override
   public I8NProvider getI8NProvider() {
      return new I8NImpl();
   }

   @Override
   public String getName() {
      return "i8n";
   }
}
