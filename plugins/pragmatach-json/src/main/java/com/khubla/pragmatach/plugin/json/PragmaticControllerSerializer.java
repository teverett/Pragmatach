package com.khubla.pragmatach.plugin.json;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Field;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.io.IOUtils;
import org.json.JSONObject;

import com.google.gson.Gson;
import com.khubla.pragmatach.framework.api.PragmatachException;
import com.khubla.pragmatach.framework.controller.PragmatachController;

/**
 * @author tome
 */
public class PragmaticControllerSerializer {
   public static void deserialize(PragmatachController pragmatachController, InputStream inputStream) throws PragmatachException {
      try {
         final ByteArrayOutputStream baos = new ByteArrayOutputStream();
         IOUtils.copy(inputStream, baos);
         deserialize(pragmatachController, baos.toString());
      } catch (final Exception e) {
         throw new PragmatachException("Exception in deserialize", e);
      }
   }

   public static void deserialize(PragmatachController pragmatachController, String json) throws PragmatachException {
      try {
         final JSONObject jSONObject = new JSONObject(json);
         final String[] names = JSONObject.getNames(jSONObject);
         if (null != names) {
            for (final String name : names) {
               /*
                * get the data we need
                */
               final Class<?> type = PropertyUtils.getPropertyType(pragmatachController, name);
               if (null != type) {
                  final String value = jSONObject.getString(name);
                  final Object fieldValue = deserializeField(value, type);
                  /*
                   * set the field data
                   */
                  PropertyUtils.setProperty(pragmatachController, name, fieldValue);
               }
            }
         }
      } catch (final Exception e) {
         throw new PragmatachException("Exception in deserialize", e);
      }
   }

   private static Object deserializeField(String json, Class<?> type) throws PragmatachException {
      try {
         final Gson gson = new Gson();
         return gson.fromJson(json, type);
      } catch (final Exception e) {
         throw new PragmatachException("Exception in deserializeField", e);
      }
   }

   public static String serialize(PragmatachController pragmatachController) throws PragmatachException {
      try {
         final JSONObject jSONObject = new JSONObject();
         for (final Field field : pragmatachController.getClass().getDeclaredFields()) {
            final String fieldValue = serializeField(PropertyUtils.getProperty(pragmatachController, field.getName()));
            jSONObject.put(field.getName(), fieldValue);
         }
         return jSONObject.toString();
      } catch (final Exception e) {
         throw new PragmatachException("Exception in serialize", e);
      }
   }

   public static void serialize(PragmatachController pragmatachController, OutputStream outputStream) throws PragmatachException {
      try {
         final ByteArrayInputStream bais = new ByteArrayInputStream(serialize(pragmatachController).getBytes());
         IOUtils.copy(bais, outputStream);
      } catch (final Exception e) {
         throw new PragmatachException("Exception in serialize", e);
      }
   }

   private static String serializeField(Object object) throws PragmatachException {
      try {
         final Gson gson = new Gson();
         return gson.toJson(object);
      } catch (final Exception e) {
         throw new PragmatachException("Exception in serializeField", e);
      }
   }
}
