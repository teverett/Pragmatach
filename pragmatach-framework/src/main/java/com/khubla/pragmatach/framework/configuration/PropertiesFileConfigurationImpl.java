package com.khubla.pragmatach.framework.configuration;

import java.io.InputStream;
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
   public Map<String, String> getAll() {
      readProperties();
      final Map<String, String> ret = new HashMap<String, String>();
      final Enumeration<Object> enumer = properties.keys();
      while (enumer.hasMoreElements()) {
         final String k = (String) enumer.nextElement();
         ret.put(k, properties.getProperty(k));
      }
      return ret;
   }

   @Override
   public String getParameter(String name) {
      readProperties();
      return properties.getProperty(name);
   }

   @Override
   public String getPublicResourcePath() {
      readProperties();
      return properties.getProperty("pragmatach.publicResourcePath");
   }

   /**
    * read properties
    */
   private void readProperties() {
      try {
         if (null == properties) {
            properties = new Properties();
            final InputStream is = PropertiesFileConfigurationImpl.class.getResourceAsStream(CONFIGURATION_FILE);
            if (null != is) {
               properties.load(is);
            }
         }
      } catch (final Exception e) {
         e.printStackTrace();
      }
   }
}
