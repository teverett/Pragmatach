package com.khubla.pragmatach.framework.configuration;

import java.util.Properties;

import com.khubla.pragmatach.framework.api.Configuration;

/**
 * @author tome
 */
public class PropertiesFileConfigurationImpl implements Configuration {
   /**
    * file
    */
   private static final String CONFIGURATION_FILE = "/pragmatach.properties";
   /**
    * properties
    */
   private static Properties properties;

   @Override
   public String getPublicResourcePath() {
      readProperties();
      return properties.getProperty("publicResourcePath");
   }

   /**
    * read properties
    */
   private void readProperties() {
      try {
         if (null == properties) {
            properties = new Properties();
            properties.load(PropertiesFileConfigurationImpl.class.getResourceAsStream(CONFIGURATION_FILE));
         }
      } catch (final Exception e) {
         e.printStackTrace();
      }
   }
}
