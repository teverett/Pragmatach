package com.khubla.pragmatach.plugin.json;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.Map;

import com.khubla.pragmatach.framework.api.PragmatachException;

/**
 * @author tome
 */
public class PragmatachJSON {
   /**
    * convert object from JSON
    */
   public static void fromJSON(Object object, InputStream inputStream) throws PragmatachException {
      try {
      } catch (final Exception e) {
         throw new PragmatachException("Exception in fromJSON", e);
      }
   }

   /**
    * parse JSON to map
    */
   public static Map<String, String> parseJSON(InputStream inputStream) throws PragmatachException {
      try {
         return null;
      } catch (final Exception e) {
         throw new PragmatachException("Exception in parseJSON", e);
      }
   }

   /**
    * convert object to JSON
    */
   public static void toJSON(Object object, OutputStream outputStream) throws PragmatachException {
      try {
      } catch (final Exception e) {
         throw new PragmatachException("Exception in toJSON", e);
      }
   }
}
