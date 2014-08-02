package com.khubla.pragmatach.plugin.i8n;

import java.io.InputStream;
import java.util.Date;
import java.util.Hashtable;
import java.util.Properties;

import com.khubla.pragmatach.framework.api.I8NProvider;
import com.khubla.pragmatach.framework.api.PragmatachException;
import com.khubla.pragmatach.plugin.i8n.plugin.PluginImpl;

/**
 * @author tome
 */
public class I8NImpl implements I8NProvider {
   /**
    * props file
    */
   private final static String I8N_FILE = "i8n.properties_";
   /**
    * properties
    */
   private final Hashtable<String, Properties> localeData = new Hashtable<String, Properties>();
   /**
    * the owning plugin
    */
   private final PluginImpl plugin;

   /**
    * ctor
    */
   public I8NImpl(PluginImpl plugin) {
      this.plugin = plugin;
   }

   @Override
   public String getDate(String locale, Date date) throws PragmatachException {
      return date.toString();
   }

   /**
    * get the props for a certain locale
    */
   private Properties getLocaleData(String locale) throws PragmatachException {
      try {
         /*
          * check locally
          */
         Properties properties = localeData.get(locale);
         if (null != properties) {
            return properties;
         } else {
            /*
             * get resource
             */
            final InputStream inputStream = plugin.getPluginContext().getResourceLoader().getResource(getLocalfileResourceName(locale));
            if (null != inputStream) {
               properties = new Properties();
               properties.load(inputStream);
               localeData.put(locale, properties);
               inputStream.close();
               return properties;
            } else {
               return null;
            }
         }
      } catch (final Exception e) {
         throw new PragmatachException("Exception in getLocaleData", e);
      }
   }

   private String getLocalfileResourceName(String locale) {
      return I8N_FILE + locale;
   }

   @Override
   public String getName() {
      return "Pragmatach file-based i8n provider";
   }

   @Override
   public String getString(String locale, String name) throws PragmatachException {
      final Properties properties = getLocaleData(locale);
      if (null != properties) {
         return properties.getProperty(name);
      }
      return null;
   }
}
