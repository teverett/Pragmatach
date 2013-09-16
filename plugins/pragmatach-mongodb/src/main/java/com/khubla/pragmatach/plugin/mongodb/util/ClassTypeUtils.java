package com.khubla.pragmatach.plugin.mongodb.util;

import java.lang.reflect.Field;

import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.apache.commons.beanutils.BeanUtils;

import com.khubla.pragmatach.framework.api.PragmatachException;

/**
 * @author tom
 */
public class ClassTypeUtils {
   /**
    * check if its cascade delete field
    */
   public static boolean isCascadeDelete(Field field) {
      /*
       * get the column annotation, if it exists
       */
      final OneToMany OneToManyAnnotation = field.getAnnotation(OneToMany.class);
      if (null != OneToManyAnnotation) {
         final CascadeType[] cascadetypes = OneToManyAnnotation.cascade();
         for (final CascadeType cascadeType : cascadetypes) {
            if ((cascadeType == CascadeType.REMOVE) || (cascadeType == CascadeType.ALL)) {
               return true;
            }
         }
      }
      return false;
   }

   /**
    * check if its cascade saved field
    */
   public static boolean isCascadeSave(Field field) {
      /*
       * get the column annotation, if it exists
       */
      final OneToMany OneToManyAnnotation = field.getAnnotation(OneToMany.class);
      if (null != OneToManyAnnotation) {
         final CascadeType[] cascadetypes = OneToManyAnnotation.cascade();
         for (final CascadeType cascadeType : cascadetypes) {
            if ((cascadeType == CascadeType.PERSIST) || (cascadeType == CascadeType.ALL)) {
               return true;
            }
         }
      }
      return false;
   }

   /**
    * check if its an eager loaded field
    */
   public static boolean isEagerLoad(Field field) {
      /*
       * get the column annotation, if it exists
       */
      final OneToMany OneToManyAnnotation = field.getAnnotation(OneToMany.class);
      if (null != OneToManyAnnotation) {
         return FetchType.EAGER == OneToManyAnnotation.fetch();
      }
      return false;
   }

   /**
    * check if its an eager loaded field
    */
   public static boolean isLazyLoad(Field field) {
      /*
       * get the column annotation, if it exists
       */
      final OneToMany OneToManyAnnotation = field.getAnnotation(OneToMany.class);
      if (null != OneToManyAnnotation) {
         return FetchType.LAZY == OneToManyAnnotation.fetch();
      }
      return false;
   }

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
