package com.khubla.pragmatach.plugin.json;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.io.IOUtils;
import org.json.JSONObject;

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
         final ByteArrayOutputStream baos = new ByteArrayOutputStream();
         IOUtils.copy(inputStream, baos);
         fromJSON(object, baos.toString());
      } catch (final Exception e) {
         throw new PragmatachException("Exception in fromJSON", e);
      }
   }

   /**
    * convert object from JSON
    */
   public static void fromJSON(Object object, String json) throws PragmatachException {
      try {
         final Map<String, String> values = parseJSON(json);
         if ((null != values) && (values.size() > 0)) {
            for (final Field field : object.getClass().getDeclaredFields()) {
               final String value = values.get(field.getName());
               BeanUtils.setProperty(object, field.getName(), value);
            }
         }
      } catch (final Exception e) {
         throw new PragmatachException("Exception in fromJSON", e);
      }
   }

   /**
    * parse JSON to map
    */
   public static Map<String, String> parseJSON(InputStream inputStream) throws PragmatachException {
      try {
         final ByteArrayOutputStream baos = new ByteArrayOutputStream();
         IOUtils.copy(inputStream, baos);
         return parseJSON(baos.toString());
      } catch (final Exception e) {
         throw new PragmatachException("Exception in parseJSON", e);
      }
   }

   /**
    * parse JSON to map
    */
   public static Map<String, String> parseJSON(String json) throws PragmatachException {
      try {
         final Map<String, String> ret = new HashMap<String, String>();
         final JSONObject jSONObject = new JSONObject(json);
         final String[] names = JSONObject.getNames(jSONObject);
         if (null != names) {
            for (final String name : names) {
               ret.put(name, jSONObject.getString(name));
            }
         }
         return ret;
      } catch (final Exception e) {
         throw new PragmatachException("Exception in parseJSON", e);
      }
   }

   /**
    * convert object to JSON
    */
   public static String toJSON(Object object) throws PragmatachException {
      try {
         final JSONObject jSONObject = new JSONObject();
         for (final Field field : object.getClass().getDeclaredFields()) {
            jSONObject.put(field.getName(), BeanUtils.getSimpleProperty(object, field.getName()));
         }
         return jSONObject.toString();
      } catch (final Exception e) {
         throw new PragmatachException("Exception in toJSON", e);
      }
   }

   /**
    * convert object to JSON
    */
   public static void toJSON(Object object, OutputStream outputStream) throws PragmatachException {
      try {
         final String json = toJSON(object);
         final ByteArrayInputStream bais = new ByteArrayInputStream(json.getBytes());
         IOUtils.copy(bais, outputStream);
      } catch (final Exception e) {
         throw new PragmatachException("Exception in toJSON", e);
      }
   }
}
