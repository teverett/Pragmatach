package com.khubla.pragmatach.plugin.mongodb.serializer;

import java.lang.reflect.Field;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.PropertyUtils;

import com.khubla.pragmatach.framework.api.PragmatachException;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;

/**
 * @author tom
 */
public class EnumFieldSerializer implements FieldSerializer {
   /**
    * ctor
    */
   public EnumFieldSerializer(Class<?> typeClazz) {
   }

   @SuppressWarnings({ "rawtypes", "unchecked" })
   @Override
   public void deserializeField(Object object, Field field, DBObject dbObject) throws PragmatachException {
      try {
         final String v = (String) dbObject.get(field.getName());
         final Enum e = Enum.valueOf((Class<Enum>) field.getType(), v);
         PropertyUtils.setProperty(object, field.getName(), e);
      } catch (final Exception e) {
         throw new PragmatachException("Exception in deserializeField", e);
      }
   }

   @Override
   public void serializeField(BasicDBObject parentDBObject, Object object, Field field) throws PragmatachException {
      try {
         final String v = BeanUtils.getProperty(object, field.getName());
         parentDBObject.append(field.getName(), v);
      } catch (final Exception e) {
         throw new PragmatachException("Exception in serializeField", e);
      }
   }
}
