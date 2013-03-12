package com.khubla.pragmatach.framework.application;

import com.khubla.pragmatach.framework.api.Configuration;

/**
 * @author tome
 */
public class Application {
   /**
    * configuration
    */
   private static Configuration configuration;

   public static Configuration getConfiguration() {
      return configuration;
   }

   public static void setConfiguration(Configuration configuration) {
      Application.configuration = configuration;
   }
}
