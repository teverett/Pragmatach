package com.khubla.pragmatach.framework.configuration;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
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

   @Override
   public Map<String, String> getAll() {
      readProperties();
      Map<String, String> ret = new HashMap<String, String>();
      Enumeration<Object> enumer = properties.keys();
      while (enumer.hasMoreElements()) {
         String k = (String) enumer.nextElement();
         ret.put(k, properties.getProperty(k));
      }
      return ret;
   }
}
