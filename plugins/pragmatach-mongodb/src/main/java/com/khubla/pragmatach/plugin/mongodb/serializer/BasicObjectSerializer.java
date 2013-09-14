package com.khubla.pragmatach.plugin.mongodb.serializer;

import java.lang.reflect.Field;

import net.sf.cglib.proxy.Enhancer;

import org.bson.types.ObjectId;

import com.khubla.pragmatach.framework.api.PragmatachException;
import com.khubla.pragmatach.plugin.mongodb.util.ClassTypeUtils;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;

/**
 * @author tom
 */
public class BasicObjectSerializer<T> implements ObjectSerializer<T> {
   /**
    * the type
    */
   private final Class<T> typeClazz;
   /**
    * type utils
    */
   private final ClassTypeUtils typeUtils;

   /**
    * ctor
    */
   public BasicObjectSerializer(Class<T> typeClazz) {
      this.typeClazz = typeClazz;
      typeUtils = new ClassTypeUtils(this.typeClazz);
   }

   /**
    * deserialize
    */
   @Override
   public T deserialize(DBObject dbObject) throws PragmatachException {
      try {
         /*
          * instance
          */
         final T t = getInstance();
         /*
          * walk the fields
          */
         for (final Field field : typeClazz.getDeclaredFields()) {
            /*
             * filter the static ones
             */
            if (false == java.lang.reflect.Modifier.isStatic(field.getModifiers())) {
               final FieldSerializer fieldSerializer = FieldSerializerFactory.getFieldSerializer(typeClazz, field);
               fieldSerializer.deserializeField(t, field, dbObject);
            }
         }
         /*
          * done
          */
         return t;
      } catch (final Exception e) {
         throw new PragmatachException("Exception in deserialize", e);
      }
   }

   /**
    * get instance
    */
   @SuppressWarnings("unchecked")
   private T getInstance() throws IllegalAccessException, InstantiationException {
      final T t = this.typeClazz.newInstance();
      return (T) Enhancer.create(this.typeClazz, new MongoInterceptor(t));
   }

   public Class<?> getTypeClazz() {
      return typeClazz;
   }

   /**
    * serialize
    */
   @Override
   public BasicDBObject serialize(T t) throws PragmatachException {
      try {
         if (null != t) {
            /*
             * id generation
             */
            if (typeUtils.isGeneratedId()) {
               /*
                * check for an id
                */
               final String id = typeUtils.getId(t);
               if (null == id) {
                  final ObjectId objectId = new ObjectId();
                  typeUtils.setId(t, objectId.toString());
               }
            }
            /*
             * object node
             */
            final BasicDBObject ret = new BasicDBObject();
            /*
             * walk the fields
             */
            for (final Field field : typeClazz.getDeclaredFields()) {
               /*
                * filter the static ones
                */
               if (false == java.lang.reflect.Modifier.isStatic(field.getModifiers())) {
                  final FieldSerializer fieldSerializer = FieldSerializerFactory.getFieldSerializer(typeClazz, field);
                  fieldSerializer.serializeField(ret, t, field);
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
