package com.khubla.pragmatach.plugin.i8n;

import java.io.InputStream;
import java.util.Date;
import java.util.Properties;

import org.apache.log4j.Logger;

import com.khubla.pragmatach.framework.api.I8NProvider;

/**
 * @author tome
 */
public class I8NImpl implements I8NProvider {
   /**
    * props file
    */
   private final static String I8N_FILE = "i8n.properties";
   /**
    * properties
    */
   private final Properties properties;
   /**
    * logger
    */
   private final Logger logger = Logger.getLogger(this.getClass());

   /**
    * ctor
    */
   public I8NImpl() {
      properties = new Properties();
      final InputStream inputStream = I8NImpl.class.getResourceAsStream(I8N_FILE);
      if (null != inputStream) {
         try {
            properties.load(inputStream);
         } catch (final Exception e) {
            logger.error("Exception reading '" + I8N_FILE + "'", e);
         }
      }
   }

   @Override
   public String getDate(String locale, Date date) {
      return date.toString();
   }

   @Override
   public String getName() {
      return "Pragmatach file-based i8n provider";
   }

   @Override
   public String getString(String locale, String name) {
      return properties.getProperty(locale + ":" + name);
   }
}
