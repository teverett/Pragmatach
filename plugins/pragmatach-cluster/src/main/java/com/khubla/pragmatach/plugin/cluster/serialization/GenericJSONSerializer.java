package com.khubla.pragmatach.plugin.cluster.serialization;

import java.lang.reflect.Field;

import org.apache.commons.beanutils.BeanUtils;
import org.json.JSONObject;

import com.khubla.pragmatach.framework.api.PragmatachException;

/**
 * @author tome
 */
public class GenericJSONSerializer<T> {
   /**
    * deserialize
    */
   public T deserialize(T t, String json) throws PragmatachException {
      try {
         final JSONObject jsonObject = new JSONObject(json);
         final Field[] fields = t.getClass().getDeclaredFields();
         for (final Field field : fields) {
            if (false == java.lang.reflect.Modifier.isStatic(field.getModifiers())) {
               final String value = jsonObject.getString(field.getName());
               if (value != JSONObject.NULL) {
                  BeanUtils.setProperty(t, field.getName(), value);
               } else {
                  BeanUtils.setProperty(t, field.getName(), null);
               }
            }
         }
         return t;
      } catch (final Exception e) {
         throw new PragmatachException("Exception in deserialize", e);
      }
   }

   /**
    * serialize object to JSON
    */
   public String serialize(T t) throws PragmatachException {
      try {
         final JSONObject jsonObject = new JSONObject();
         /*
          * values
          */
         final Field[] fields = t.getClass().getDeclaredFields();
         for (final Field field : fields) {
            if (false == java.lang.reflect.Modifier.isStatic(field.getModifiers())) {
               final String value = BeanUtils.getProperty(t, field.getName());
               if (null != value) {
                  jsonObject.put(field.getName(), value);
               } else {
                  jsonObject.put(field.getName(), JSONObject.NULL);
               }
            }
         }
         /*
          * done
          */
         return jsonObject.toString();
      } catch (final Exception e) {
         throw new PragmatachException("Exception in serialize", e);
      }
   }
}
