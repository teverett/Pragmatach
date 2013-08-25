package com.khubla.pragmatach.plugin.mongodb;

import java.lang.reflect.Field;

import org.apache.commons.beanutils.BeanUtils;

import com.khubla.pragmatach.framework.api.PragmatachException;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;

/**
 * @author tom
 */
public class MongoDBJSONSerializer<T> {
   /**
    * the type
    */
   private final Class<T> typeClazz;
   /**
    * type utils
    */
   private final TypeUtils<T> typeUtils;

   /**
    * ctor
    */
   public MongoDBJSONSerializer(Class<T> typeClazz) {
      this.typeClazz = typeClazz;
      this.typeUtils = new TypeUtils<T>(this.typeClazz);
   }

   /**
    * deserialize
    */
   public void deserialize(T t, DBObject dbObject) throws PragmatachException {
      try {
         /*
          * get the name of the id field
          */
         final String idFieldName = this.typeUtils.getIdFieldName();
         /*
          * walk the fields
          */
         for (final Field field : this.typeClazz.getDeclaredFields()) {
            /*
             * read all fields, treating id as special
             */
            if (field.getName().compareTo(idFieldName) != 0) {
               BeanUtils.setProperty(t, field.getName(), dbObject.get(field.getName()));
            } else {
               final String objectId = dbObject.get(MongoDBDAO.ID).toString();
               this.typeUtils.setId(t, objectId);
            }
         }
      } catch (final Exception e) {
         throw new PragmatachException("Exception in deserialize", e);
      }
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
             * get the name of the id field
             */
            final String idFieldName = this.typeUtils.getIdFieldName();
            /*
             * walk the fields
             */
            for (final Field field : this.typeClazz.getDeclaredFields()) {
               /*
                * persist all fields, treating id as special
                */
               if (field.getName().compareTo(idFieldName) != 0) {
                  final String propertyValue = BeanUtils.getProperty(t, field.getName());
                  ret.append(field.getName(), propertyValue);
               } else {
                  final String id = typeUtils.getId(t);
                  ret.append(MongoDBDAO.ID, id);
               }
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
