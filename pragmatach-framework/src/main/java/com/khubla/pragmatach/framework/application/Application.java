package com.khubla.pragmatach.framework.application;

import com.khubla.pragmatach.framework.api.Configuration;
import com.khubla.pragmatach.framework.lifecycle.LifecycleListeners;

/**
 * @author tome
 */
public class Application {
   /**
    * configuration
    */
   private static Configuration configuration;
   /**
    * lifecycle listeners
    */
   private static final LifecycleListeners lifecycleListeners = new LifecycleListeners();

   public static Configuration getConfiguration() {
      return configuration;
   }

   public static LifecycleListeners getLifecyclelisteners() {
      return lifecycleListeners;
   }

   public static void setConfiguration(Configuration configuration) {
      Application.configuration = configuration;
   }
}
