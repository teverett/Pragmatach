package com.khubla.pragmatach.plugin.mongodb;

import net.sf.cglib.proxy.Enhancer;

import com.khubla.pragmatach.framework.api.PragmatachException;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;

/**
 * @author tom
 */
public class MongoDBObjectPersister<T> {
   /**
    * the type
    */
   private final Class<T> typeClazz;
   /**
    * collection
    */
   private final DBCollection dbCollection;
   /**
    * serializer
    */
   private final MongoDBJSONSerializer mongoDBJSONSerializer;

   /**
    * ctor
    */
   public MongoDBObjectPersister(Class<T> typeClazz) {
      this.typeClazz = typeClazz;
      this.mongoDBJSONSerializer = new MongoDBJSONSerializer(this.typeClazz);
      this.dbCollection = DBCollectionFactory.getInstance().getDBCollection(typeClazz);
   }

   /**
    * deserialize
    */
   public T deserialize(DBObject dbObject) throws PragmatachException {
      try {
         /*
          * deserialize the root object
          */
         final T t = getInstance();
         this.mongoDBJSONSerializer.deserialize(dbObject, t);
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
      T t = this.typeClazz.newInstance();
      return (T) Enhancer.create(this.typeClazz, new MongoInterceptor(t));
   }

   /**
    * serialize
    */
   public void serialize(T t) throws PragmatachException {
      try {
         /*
          * save the main object
          */
         final BasicDBObject basicDBObject = this.mongoDBJSONSerializer.serialize(t);
         this.dbCollection.save(basicDBObject);
      } catch (final Exception e) {
         throw new PragmatachException("Exception in serialize", e);
      }
   }
}
