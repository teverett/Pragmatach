package com.khubla.pragmatach.plugin.mongodb;

import java.io.Serializable;
import java.lang.reflect.Field;

import org.apache.commons.beanutils.BeanUtils;

import com.khubla.pragmatach.framework.api.PragmatachException;
import com.mongodb.BasicDBObject;

/**
 * @author tom
 */
public class MongoDBJSONSerializer<T, I extends Serializable> {
   /**
    * the type
    */
   private final Class<T> typeClazz;
   /**
    * the identifier
    */
   private final Class<I> identifierClazz;

   /**
    * ctor
    */
   public MongoDBJSONSerializer(Class<T> typeClazz, Class<I> identifierClazz) {
      this.typeClazz = typeClazz;
      this.identifierClazz = identifierClazz;
   }

   /**
    * deserialize
    */
   public void deserialize(T t, BasicDBObject basicDBObject) throws PragmatachException {
      try {
         /*
          * walk the fields
          */
         for (final Field field : this.typeClazz.getDeclaredFields()) {
            BeanUtils.setProperty(t, field.getName(), basicDBObject.get(field.getName()));
         }
      } catch (final Exception e) {
         throw new PragmatachException("Exception in deserialize", e);
      }
   }

   public Class<I> getIdentifierClazz() {
      return identifierClazz;
   }

   public Class<T> getTypeClazz() {
      return typeClazz;
   }

   /**
    * serialize
    */
   public BasicDBObject serialize(T t) throws PragmatachException {
      try {
         if (null != t) {
            /*
             * object node
             */
            final BasicDBObject ret = new BasicDBObject();
            /*
             * walk the fields
             */
            final Field[] fields = this.typeClazz.getDeclaredFields();
            for (final Field field : fields) {
               final String propertyValue = BeanUtils.getProperty(t, field.getName());
               ret.append(field.getName(), propertyValue);
            }
            /*
             * done
             */
            return ret;
         } else {
            throw new PragmatachException("Cannot serialize null object");
         }
      } catch (final Exception e) {
         throw new PragmatachException("Exception in serialize", e);
      }
   }
}
