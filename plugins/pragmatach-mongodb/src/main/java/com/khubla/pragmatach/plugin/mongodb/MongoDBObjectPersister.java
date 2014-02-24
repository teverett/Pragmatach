package com.khubla.pragmatach.plugin.mongodb;

import com.khubla.pragmatach.framework.api.PragmatachException;
import com.khubla.pragmatach.plugin.mongodb.db.DBCollectionFactory;
import com.khubla.pragmatach.plugin.mongodb.serializer.BasicObjectSerializer;
import com.khubla.pragmatach.plugin.mongodb.serializer.ObjectSerializer;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;

/**
 * @author tom
 */
public class MongoDBObjectPersister {
   /**
    * DBCollection
    */
   private final DBCollection dbCollection;
   /**
    * serializer
    */
   private final ObjectSerializer objectSerializer;

   public MongoDBObjectPersister(Class<?> typeClazz) {
      objectSerializer = new BasicObjectSerializer(typeClazz);
      dbCollection = DBCollectionFactory.getInstance().getDBCollection(typeClazz);
   }

   /**
    * find an instance by an id
    * 
    * @return
    */
   public DBObject find(String id) throws PragmatachException {
      final BasicDBObject basicDBObject = new BasicDBObject();
      basicDBObject.append(MongoDBDAO.ID, id);
      final DBCursor dbCursor = dbCollection.find(basicDBObject);
      if (dbCursor.hasNext()) {
         return dbCursor.next();
      } else {
         return null;
      }
   }

   public Object load(DBObject dbObject) throws PragmatachException {
      try {
         return objectSerializer.deserialize(dbObject);
      } catch (final Exception e) {
         throw new PragmatachException("Exception in load", e);
      }
   }

   public void save(Object object) throws PragmatachException {
      try {
         /*
          * save
          */
         final BasicDBObject basicDBOBject = objectSerializer.serialize(object);
         dbCollection.save(basicDBOBject);
      } catch (final Exception e) {
         throw new PragmatachException("Exception in save", e);
      }
   }
}
