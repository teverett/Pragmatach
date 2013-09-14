package com.khubla.pragmatach.plugin.mongodb;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Set;

import org.apache.commons.beanutils.BeanUtils;

import com.khubla.pragmatach.framework.api.PragmatachException;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;

/**
 * @author tom
 */
public class SetFieldSerializer implements FieldSerializer {
   /**
    * id field name
    */
   private final String idFieldName;
   /**
    * type utils
    */
   private final ClassTypeUtils typeUtils;
   /**
    * the type
    */
   private final Class<?> typeClazz;

   /**
    * ctor
    */
   public SetFieldSerializer(Class<?> typeClazz) {
      this.typeClazz = typeClazz;
      typeUtils = new ClassTypeUtils(this.typeClazz);
      idFieldName = typeUtils.getIdFieldName();
   }

   @Override
   public void deserializeField(Object object, Field field, DBObject dbObject) throws PragmatachException {
      try {
         /*
          * Set
          */
         final DBObject dbo = (DBObject) dbObject.get(field.getName());
         final Type type = field.getGenericType();
         final ParameterizedType parameterizedType = (ParameterizedType) type;
         final Class<?> containedType = (Class<?>) parameterizedType.getActualTypeArguments()[0];
         final Set<?> set = deserializeSet(dbo, containedType);
         BeanUtils.setProperty(object, field.getName(), set);
      } catch (final Exception e) {
         throw new PragmatachException("Exception in deserializeField", e);
      }
   }

   /**
    * deserialize Set
    */
   private Set<?> deserializeSet(DBObject dbObject, Class<?> containedType) throws PragmatachException {
      try {
         // final Set ret = new HashSet();
         // if (null != dbObject) {
         // int i = 0;
         // DBObject dbo = (DBObject) dbObject.get(Integer.toString(i++));
         // while (null != dbo) {
         // /*
         // * deserialize
         // */
         // final MongoDBJSONSerializer mongoDBJSONSerializer = new MongoDBJSONSerializer(containedType);
         // Object instance = mongoDBJSONSerializer.deserialize(dbo, containedType);
         // ret.add(instance);
         // /*
         // * next one
         // */
         // dbo = (DBObject) dbObject.get(Integer.toString(i++));
         // }
         // }
         // return ret;
         return null;
      } catch (final Exception e) {
         throw new PragmatachException("Exception in deserializeSet", e);
      }
   }

   @Override
   public void serializeField(BasicDBObject parentDBObject, Object object, Field field) throws PragmatachException {
      try {
         /*
          * Set
          */
         final BasicDBObject dbObject = serializeSet(object, field);
         parentDBObject.append(field.getName(), dbObject);
      } catch (final Exception e) {
         throw new PragmatachException("Exception in serializeField", e);
      }
   }

   /**
    * serialize a Set
    */
   private BasicDBObject serializeSet(Object object, Field field) throws PragmatachException {
      try {
         // /*
         // * get the set
         // */
         // final Set<?> set = (Set<?>) PropertyUtils.getProperty(object, field.getName());
         // if (null != set) {
         // /*
         // * walk the set members
         // */
         // final BasicDBObject dbObject = new BasicDBObject();
         // final Iterator<?> iter = set.iterator();
         // int i = 0;
         // while (iter.hasNext()) {
         // final Object o = iter.next();
         // final MongoDBJSONSerializer mongoDBJSONSerializer = new MongoDBJSONSerializer(o.getClass());
         // final BasicDBObject internalObject = mongoDBJSONSerializer.serialize(o);
         // dbObject.append(Integer.toString(i++), internalObject);
         // }
         // return dbObject;
         // } else {
         // return null;
         // }
         return null;
      } catch (final Exception e) {
         throw new PragmatachException("Exception in serializeSet", e);
      }
   }
}
