package com.khubla.pragmatach.plugin.mongodb;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Set;

import org.apache.commons.beanutils.BeanUtils;
import org.bson.types.ObjectId;

import com.khubla.pragmatach.framework.api.PragmatachException;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;

/**
 * @author tom
 */
public class MongoDBJSONSerializer {
   /**
    * the type
    */
   private final Class<?> typeClazz;
   /**
    * type utils
    */
   private final ClassTypeUtils typeUtils;
   /**
    * id field name
    */
   private final String idFieldName;

   /**
    * ctor
    */
   public MongoDBJSONSerializer(Class<?> typeClazz) {
      this.typeClazz = typeClazz;
      typeUtils = new ClassTypeUtils(this.typeClazz);
      idFieldName = typeUtils.getIdFieldName();
   }

   /**
    * deserialize
    */
   public void deserialize(DBObject dbObject, Object o) throws PragmatachException {
      try {
         /*
          * walk the fields
          */
         for (final Field field : typeClazz.getDeclaredFields()) {
            deserializeField(o, field, dbObject);
         }
      } catch (final Exception e) {
         throw new PragmatachException("Exception in deserialize", e);
      }
   }

   /**
    * deserialize field
    */
   private void deserializeField(Object object, Field field, DBObject dbObject) throws PragmatachException {
      try {
         /*
          * filter the static ones
          */
         if (false == java.lang.reflect.Modifier.isStatic(field.getModifiers())) {
            /*
             * simple types
             */
            if (AtomicTypeUtil.isSimpleType(field.getType())) {
               /*
                * read all fields, treating id as special
                */
               if (field.getName().compareTo(idFieldName) != 0) {
                  BeanUtils.setProperty(object, field.getName(), dbObject.get(field.getName()));
               } else {
                  final String objectId = (String) dbObject.get(MongoDBDAO.ID);
                  if (null == objectId) {
                     throw new PragmatachException("Object '" + dbObject.toString() + "' has null id");
                  }
                  typeUtils.setId(object, objectId);
               }
            } else if (field.getType() == Set.class) {
               /*
                * Set
                */
               final DBObject dbo = (DBObject) dbObject.get(field.getName());
               final Type type = field.getGenericType();
               final ParameterizedType parameterizedType = (ParameterizedType) type;
               final Class<?> containedType = (Class<?>) parameterizedType.getActualTypeArguments()[0];
               final Set<?> set = deserializeSet(dbo, containedType);
               BeanUtils.setProperty(object, field.getName(), set);
            } else {
               throw new PragmatachException("Type '" + field.getType() + "' is not supported for deserialization");
            }
         }
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

   public Class<?> getTypeClazz() {
      return typeClazz;
   }

   /**
    * serialize
    */
   public BasicDBObject serialize(Object object) throws PragmatachException {
      try {
         if (null != object) {
            /*
             * id generation
             */
            if (typeUtils.isGeneratedId()) {
               /*
                * check for an id
                */
               final String id = typeUtils.getId(object);
               if (null == id) {
                  final ObjectId objectId = new ObjectId();
                  typeUtils.setId(object, objectId.toString());
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
               serializeField(ret, object, field);
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

   /**
    * serialize a field
    */
   private void serializeField(BasicDBObject parentDBObject, Object object, Field field) throws PragmatachException {
      try {
         /*
          * filter the static ones
          */
         if (false == java.lang.reflect.Modifier.isStatic(field.getModifiers())) {
            /*
             * simple types
             */
            if (AtomicTypeUtil.isSimpleType(field.getType())) {
               /*
                * persist all fields, treating id as special
                */
               if (field.getName().compareTo(idFieldName) != 0) {
                  final String propertyValue = BeanUtils.getProperty(object, field.getName());
                  parentDBObject.append(field.getName(), propertyValue);
               } else {
                  final String id = typeUtils.getId(object);
                  parentDBObject.append(MongoDBDAO.ID, id);
               }
            } else if (field.getType() == Set.class) {
               /*
                * Set
                */
               final BasicDBObject dbObject = serializeSet(object, field);
               parentDBObject.append(field.getName(), dbObject);
            } else {
               throw new PragmatachException("Type '" + field.getType() + "' is not supported for serialization");
            }
         }
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
