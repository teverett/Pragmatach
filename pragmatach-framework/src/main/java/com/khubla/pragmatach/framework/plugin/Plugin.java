package com.khubla.pragmatach.framework.plugin;

import java.io.IOException;
import java.io.InputStream;
import java.net.JarURLConnection;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Properties;
import java.util.jar.Manifest;

import com.khubla.pragmatach.framework.api.PragmatachException;

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

   /**
    * get the plugin jar manifest
    */
   public Manifest getManifest() throws PragmatachException {
      try {
         final JarURLConnection jarURLConnection = (JarURLConnection) url.openConnection();
         if (null != jarURLConnection) {
            return jarURLConnection.getManifest();
         }
         return null;
      } catch (final Exception e) {
         throw new PragmatachException("Exception in getManifest", e);
      }
   }

   public String getName() {
      return properties.getProperty(NAME);
   }

   public Properties getProperties() {
      return properties;
   }

   /**
    * get a resource from this plugin's jar
    */
   public InputStream getResource(String name) throws PragmatachException {
      try {
         final ClassLoader classLoader = new URLClassLoader(new URL[] { url });
         return classLoader.getResourceAsStream(name);
      } catch (final Exception e) {
         throw new PragmatachException("Exception in getResource", e);
      }
   }

   public URL getUrl() {
      return url;
   }
}
