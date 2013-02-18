package com.khubla.pragmatach.framework.plugin;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Properties;

/**
 * @author tome
 */
public class Plugin {
   /**
    * name
    */
   public static final String NAME = "name";
   /**
    * the jar
    */
   private final URL url;
   /**
    * the properties
    */
   private final Properties properties;

   public Plugin(URL url, InputStream inputStream) throws IOException {
      this.url = url;
      properties = new Properties();
      properties.load(inputStream);
   }

   public String getName() {
      return properties.getProperty(NAME);
   }

   public Properties getProperties() {
      return properties;
   }

   public URL getUrl() {
      return url;
   }
}
