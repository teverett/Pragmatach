package com.khubla.pragmatach.plugin.mongodb.serializer;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Iterator;
import java.util.Set;

import org.apache.commons.beanutils.PropertyUtils;

import com.khubla.pragmatach.framework.api.PragmatachException;
import com.khubla.pragmatach.plugin.mongodb.MongoDBDAO;
import com.khubla.pragmatach.plugin.mongodb.util.ClassTypeUtils;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;

/**
 * @author tom
 */
public class SetFieldSerializer implements FieldSerializer {
   /**
    * type utils
    */
   private final ClassTypeUtils classTypeUtils;
   /**
    * the type
    */
   private final Class<?> typeClazz;

   /**
    * ctor
    */
   public SetFieldSerializer(Class<?> typeClazz) {
      this.typeClazz = typeClazz;
      classTypeUtils = new ClassTypeUtils(this.typeClazz);
   }

   @Override
   public void deserializeField(Object object, Field field, DBObject dbObject) throws PragmatachException {
      try {
         if (null != dbObject) {
            int i = 0;
            DBObject dbo = (DBObject) dbObject.get(Integer.toString(i++));
            while (null != dbo) {
               /*
                * get the id
                */
               final String id = dbo.toString();
               System.out.println("id: " + id);
               /*
                * next one
                */
               dbo = (DBObject) dbObject.get(Integer.toString(i++));
            }
         }
      } catch (final Exception e) {
         throw new PragmatachException("Exception in deserializeField", e);
      }
   }

   /**
    * get the interior type of the set
    */
   private Class<?> getContainedType(Field field) throws PragmatachException {
      final Type type = field.getGenericType();
      final ParameterizedType parameterizedType = (ParameterizedType) type;
      return (Class<?>) parameterizedType.getActualTypeArguments()[0];
   }

   /**
    * the strategy here is that we store the id's in the parent object, and then use DAOs to persist each contained object.
    */
   @Override
   public void serializeField(BasicDBObject parentDBObject, Object object, Field field) throws PragmatachException {
      try {
         /*
          * get the set
          */
         final Set<?> set = (Set<?>) PropertyUtils.getProperty(object, field.getName());
         if (null != set) {
            /*
             * get the contained type of the set
             */
            Class<?> containedType = this.getContainedType(field);
            /*
             * the object for the set id's
             */
            final BasicDBObject dbObject = new BasicDBObject();
            /*
             * walk the set
             */
            int i = 0;
            final Iterator<?> iter = set.iterator();
            while (iter.hasNext()) {
               final Object o = iter.next();
               /*
                * save the object. this has to happen first to generate ids
                */
               MongoDBDAO<?> dao = new MongoDBDAO(containedType);
               // dao.save(o);
               /*
                * store the id
                */
               final String id = classTypeUtils.getId(o);
               dbObject.append(Integer.toString(i++), id);
               /*
                * persist the object
                */
            }
            /*
             * add the id's to the parent
             */
            parentDBObject.append(field.getName(), dbObject);
         }
      } catch (final Exception e) {
         throw new PragmatachException("Exception in serializeField", e);
      }
   }
}
