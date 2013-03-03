package com.khubla.pragmatach.plugin.websocket.plugin;

import com.khubla.pragmatach.framework.api.I8NProvider;
import com.khubla.pragmatach.framework.api.Plugin;

/**
 * @author tome
 */
public class PluginImpl implements Plugin {
   @Override
   public String getName() {
      return "WebSocket";
   }

   @Override
   public I8NProvider getI8NProvider() {
      return null;
   }
}
