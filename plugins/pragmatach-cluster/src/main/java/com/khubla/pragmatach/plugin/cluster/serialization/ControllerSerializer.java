package com.khubla.pragmatach.plugin.cluster.serialization;

import java.lang.reflect.Field;

import org.apache.commons.beanutils.BeanUtils;
import org.json.JSONObject;

import com.khubla.pragmatach.framework.api.PragmatachException;
import com.khubla.pragmatach.framework.controller.AbstractController;
import com.khubla.pragmatach.framework.controller.PragmatachController;

/**
 * simply serialize controllers to json. It's a cheesy hack, and it works.
 * 
 * @author tome
 */
public class ControllerSerializer {
   /**
    * crack json to a string
    */
   public static JSONObject crackJSONString(String json) throws PragmatachException {
      try {
         return new JSONObject(json);
      } catch (final Exception e) {
         throw new PragmatachException("Exception in crackJSONString", e);
      }
   }

   /**
    * deserialize
    */
   public static void deserialize(PragmatachController pragmatachController, JSONObject jsonObject) throws PragmatachException {
      try {
         final Field[] fields = pragmatachController.getClass().getDeclaredFields();
         for (final Field field : fields) {
            final String value = jsonObject.getString(field.getName());
            if (value != JSONObject.NULL) {
               BeanUtils.setProperty(pragmatachController, field.getName(), value);
            } else {
               BeanUtils.setProperty(pragmatachController, field.getName(), null);
            }
         }
      } catch (final Exception e) {
         throw new PragmatachException("Exception in deserialize", e);
      }
   }

   /**
    * serialize controller to JSON
    */
   public static String serialize(PragmatachController pragmatachController) throws PragmatachException {
      try {
         /*
          * controller name
          */
         final JSONObject jsonObject = new JSONObject();
         jsonObject.put("pragmatach.controller.name", AbstractController.getControllerName(pragmatachController));
         /*
          * values
          */
         final Field[] fields = pragmatachController.getClass().getDeclaredFields();
         for (final Field field : fields) {
            String value = BeanUtils.getProperty(pragmatachController, field.getName());
            if (null != value) {
               jsonObject.put(field.getName(), value);
            } else {
               jsonObject.put(field.getName(), JSONObject.NULL);
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
