package com.khubla.pragmatach.plugin.mongodb.serializer;

import java.lang.reflect.Field;

import org.apache.commons.beanutils.PropertyUtils;

import com.khubla.pragmatach.framework.api.PragmatachException;
import com.khubla.pragmatach.plugin.mongodb.MongoDBObjectPersister;
import com.khubla.pragmatach.plugin.mongodb.util.ClassTypeUtils;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;

/**
 * @author tom
 */
public class EntityFieldSerializer implements FieldSerializer {
   /**
    * the type
    */
   private final Class<?> typeClazz;
   /**
    * type utils
    */
   private final ClassTypeUtils classTypeUtils;

   /**
    * ctor
    */
   public EntityFieldSerializer(Class<?> typeClazz) {
      this.typeClazz = typeClazz;
      classTypeUtils = new ClassTypeUtils(this.typeClazz);
   }

   @Override
   public void deserializeField(Object object, Field field, DBObject dbObject) throws PragmatachException {
      try {
         /*
          * get id
          */
         final String id = (String) dbObject.get(field.getName());
         /*
          * get object
          */
         final MongoDBObjectPersister mongoDBObjectPersister = new MongoDBObjectPersister(field.getType());
         final DBObject containedObjectJSON = mongoDBObjectPersister.find(id);
         /*
          * load it
          */
         final Object o = mongoDBObjectPersister.load(containedObjectJSON);
         /*
          * set
          */
         PropertyUtils.setProperty(object, field.getName(), o);
      } catch (final Exception e) {
         throw new PragmatachException("Exception in deserializeField", e);
      }
   }

   @Override
   public void serializeField(BasicDBObject parentDBObject, Object object, Field field) throws PragmatachException {
      try {
         /*
          * get the object
          */
         final Object oo = PropertyUtils.getProperty(object, field.getName());
         /*
          * save the object
          */
         final MongoDBObjectPersister mongoDBObjectPersister = new MongoDBObjectPersister(field.getType());
         mongoDBObjectPersister.save(oo);
         /*
          * save the id
          */
         parentDBObject.append(field.getName(), classTypeUtils.getId(oo));
      } catch (final Exception e) {
         throw new PragmatachException("Exception in serializeField", e);
      }
   }
}
