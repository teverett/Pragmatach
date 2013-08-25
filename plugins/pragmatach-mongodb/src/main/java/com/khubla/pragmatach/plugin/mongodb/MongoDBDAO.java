package com.khubla.pragmatach.plugin.mongodb;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;

import com.khubla.pragmatach.framework.api.PragmatachException;
import com.khubla.pragmatach.framework.application.Application;
import com.khubla.pragmatach.framework.dao.AbstractDAO;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;

/**
 * @author tome
 */
public class MongoDBDAO<T, I extends Serializable> extends AbstractDAO<T, I> {
   /**
    * DBCollection
    */
   private DBCollection dbCollection = getDBCollection();
   /**
    * serializer
    */
   private final MongoDBJSONSerializer<T, I> mongoDBJSONSerializer = new MongoDBJSONSerializer<T, I>(this.typeClazz, this.identifierClazz);
   /**
    * the type
    */
   private final Class<T> typeClazz;
   /**
    * the identifier
    */
   private final Class<I> identifierClazz;

   public MongoDBDAO(Class<T> typeClazz, Class<I> identifierClazz) {
      this.typeClazz = typeClazz;
      this.identifierClazz = identifierClazz;
   }

   @Override
   public long count() throws PragmatachException {
      return this.dbCollection.count();
   }

   /**
    * delete
    */
   @Override
   public void delete(T t) throws PragmatachException {
      try {
         final DBObject dbObject = mongoDBJSONSerializer.serialize(t);
         this.dbCollection.remove(dbObject);
      } catch (final Exception e) {
         throw new PragmatachException("Exception in delete", e);
      }
   }

   /**
    * delete
    */
   @Override
   public void deletebyId(I i) throws PragmatachException {
      try {
         final DBObject dbObject = getObjectById(i);
         if (null != dbObject) {
            this.dbCollection.remove(dbObject);
         }
      } catch (final Exception e) {
         throw new PragmatachException("Exception in deletebyId", e);
      }
   }

   /**
    * find by id
    */
   @Override
   public T findById(I i) throws PragmatachException {
      try {
         final DBObject dbObject = getObjectById(i);
         if (null != dbObject) {
            final T t = newInstance(dbObject);
            return t;
         } else {
            return null;
         }
      } catch (final Exception e) {
         throw new PragmatachException("Exception in findById", e);
      }
   }

   /**
    * findall
    */
   @Override
   public List<T> getAll() throws PragmatachException {
      try {
         final DBCursor cursor = this.dbCollection.find();
         final List<T> ret = new ArrayList<T>();
         try {
            while (cursor.hasNext()) {
               final DBObject dbObject = cursor.next();
               final T t = newInstance(dbObject);
               ret.add(t);
            }
         } finally {
            cursor.close();
         }
         return ret;
      } catch (final Exception e) {
         throw new PragmatachException("Exception in getAll", e);
      }
   }

   @Override
   public List<T> getAll(int start, int count) throws PragmatachException {
      return null;
   }

   /**
    * get the DBCollection
    */
   private DBCollection getDBCollection() {
      try {
         /*
          * params
          */
         final String hostname = Application.getConfiguration().getParameter("mongodb.Hostname");
         final String database = Application.getConfiguration().getParameter("mongodb.Database");
         final String username = Application.getConfiguration().getParameter("mongodb.Username");
         final String password = Application.getConfiguration().getParameter("mongodb.Password");
         /*
          * client & db
          */
         final MongoClient mongoClient = new MongoClient(hostname);
         final DB db = mongoClient.getDB(database);
         if (null != username) {
            final boolean auth = db.authenticate(username, password.toCharArray());
            if (false == auth) {
               throw new Exception("Unable to authenticate to db '" + database + "' with username '" + username + "'");
            }
         }
         /*
          * collection name
          */
         final Entity entity = this.getEntity();
         if (null == entity) {
            throw new Exception("Type '" + this.typeClazz.getCanonicalName() + "' is not annotated as an @Entity");
         }
         /*
          * collection
          */
         String collectionName = this.typeClazz.getSimpleName();
         if (null != entity.name()) {
            collectionName = entity.name();
         }
         return db.getCollection(collectionName);
      } catch (final Exception e) {
         throw new ExceptionInInitializerError(e);
      }
   }

   /**
    * get the entity
    */
   private Entity getEntity() {
      return this.typeClazz.getAnnotation(Entity.class);
   }

   @Override
   public Class<I> getIdentifierClazz() {
      return identifierClazz;
   }

   /**
    * get the id field
    */
   private String getIdFieldName() {
      for (final Field field : this.typeClazz.getDeclaredFields()) {
         if (null != field.getAnnotation(Id.class)) {
            return field.getName();
         }
      }
      return null;
   }

   /**
    * get object by id
    */
   private DBObject getObjectById(I i) throws PragmatachException {
      try {
         final BasicDBObject query = new BasicDBObject(this.getIdFieldName(), i);
         final DBCursor cursor = this.dbCollection.find(query);
         return cursor.next();
      } catch (final Exception e) {
         throw new PragmatachException("Exception in getObjectById", e);
      }
   }

   @Override
   public Class<T> getTypeClazz() {
      return typeClazz;
   }

   /**
    * get an instance
    */
   private T newInstance(DBObject dbObject) throws PragmatachException {
      try {
         final T t = this.typeClazz.newInstance();
         mongoDBJSONSerializer.deserialize(t, dbObject);
         return t;
      } catch (final Exception e) {
         throw new PragmatachException("Exception in newInstance", e);
      }
   }

   @Override
   public void reloadConfig() {
      this.dbCollection = this.getDBCollection();
   }

   /**
    * save object
    */
   @Override
   public void save(T t) throws PragmatachException {
      try {
         final BasicDBObject basicDBObject = mongoDBJSONSerializer.serialize(t);
         this.dbCollection.save(basicDBObject);
      } catch (final Exception e) {
         throw new PragmatachException("Exception in save", e);
      }
   }

   /**
    * update object
    */
   @Override
   public void update(T t) throws PragmatachException {
      try {
         final BasicDBObject basicDBObject = mongoDBJSONSerializer.serialize(t);
         this.dbCollection.save(basicDBObject);
      } catch (final Exception e) {
         throw new PragmatachException("Exception in update", e);
      }
   }
}
