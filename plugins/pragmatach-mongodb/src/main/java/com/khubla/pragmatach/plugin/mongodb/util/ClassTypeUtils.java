package com.khubla.pragmatach.plugin.mongodb.util;

import java.lang.reflect.Field;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.apache.commons.beanutils.BeanUtils;

import com.khubla.pragmatach.framework.api.PragmatachException;

/**
 * @author tom
 */
public class ClassTypeUtils {
   /**
    * the type
    */
   private final Class<?> typeClazz;

   public ClassTypeUtils(Class<?> typeClazz) {
      this.typeClazz = typeClazz;
   }

   /**
    * get id
    */
   public String getId(Object t) throws PragmatachException {
      try {
         final String idField = getIdFieldName();
         return BeanUtils.getProperty(t, idField);
      } catch (final Exception e) {
         throw new PragmatachException("Exception in getId", e);
      }
   }

   /**
    * get the id field
    */
   public String getIdFieldName() {
      for (final Field field : typeClazz.getDeclaredFields()) {
         if (null != field.getAnnotation(Id.class)) {
            return field.getName();
         }
      }
      return null;
   }

   /**
    * Entity has an @GeneratedValue?
    */
   public boolean isGeneratedId() throws PragmatachException {
      try {
         final Field field = typeClazz.getDeclaredField(getIdFieldName());
         if (null != field.getAnnotation(GeneratedValue.class)) {
            return true;
         }
         return false;
      } catch (final Exception e) {
         throw new PragmatachException("Exception in isGeneratedId", e);
      }
   }

   /**
    * set id
    */
   public void setId(Object t, String i) throws PragmatachException {
      try {
         final String idField = getIdFieldName();
         BeanUtils.setProperty(t, idField, i);
      } catch (final Exception e) {
         throw new PragmatachException("Exception in setId", e);
      }
   }
}
