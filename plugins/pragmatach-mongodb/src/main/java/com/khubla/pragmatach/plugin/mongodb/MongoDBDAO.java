package com.khubla.pragmatach.plugin.mongodb;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;

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
public class MongoDBDAO<T> extends AbstractDAO<T, String> {
   /**
    * DBCollection
    */
   private DBCollection dbCollection;
   /**
    * serializer
    */
   private final MongoDBJSONSerializer mongoDBJSONSerializer;
   /**
    * the type
    */
   private final Class<T> typeClazz;
   /**
    * type utils
    */
   private final ClassTypeUtils typeUtils;
   /**
    * _id
    */
   public static final String ID = "_id";

   public MongoDBDAO(Class<T> typeClazz) {
      this.typeClazz = typeClazz;
      this.typeUtils = new ClassTypeUtils(this.typeClazz);
      this.mongoDBJSONSerializer = new MongoDBJSONSerializer(this.typeClazz);
      this.dbCollection = getDBCollection();
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
   public void deletebyId(String i) throws PragmatachException {
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
    * find
    */
   public List<T> find(BasicDBObject query) throws PragmatachException {
      final DBCursor cursor = this.dbCollection.find(query);
      if (cursor.hasNext()) {
         final ArrayList<T> ret = new ArrayList<T>();
         while (cursor.hasNext()) {
            ret.add(this.newInstance(cursor.next()));
         }
         return ret;
      } else {
         return null;
      }
   }

   /**
    * find
    */
   public List<T> find(String[][] terms) throws PragmatachException {
      final BasicDBObject query = new BasicDBObject();
      for (final String[] term : terms) {
         if (term[0].compareTo(this.typeUtils.getIdFieldName()) == 0) {
            query.append(ID, term[1]);
         } else {
            query.append(term[0], term[1]);
         }
      }
      final DBCursor cursor = this.dbCollection.find(query);
      if (cursor.hasNext()) {
         final ArrayList<T> ret = new ArrayList<T>();
         while (cursor.hasNext()) {
            ret.add(this.newInstance(cursor.next()));
         }
         return ret;
      } else {
         return null;
      }
   }

   /**
    * find by id
    */
   @Override
   public T findById(String i) throws PragmatachException {
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
    * find
    */
   public T findOne(BasicDBObject query) throws PragmatachException {
      final DBCursor cursor = this.dbCollection.find(query);
      if (cursor.hasNext()) {
         return this.newInstance(cursor.next());
      } else {
         return null;
      }
   }

   /**
    * find
    */
   public T findOne(String[][] terms) throws PragmatachException {
      final BasicDBObject query = new BasicDBObject();
      for (final String[] term : terms) {
         if (term[0].compareTo(this.typeUtils.getIdFieldName()) == 0) {
            query.append(ID, term[1]);
         } else {
            query.append(term[0], term[1]);
         }
      }
      final DBCursor cursor = this.dbCollection.find(query);
      if (cursor.hasNext()) {
         return this.newInstance(cursor.next());
      } else {
         return null;
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
         final boolean dropCollection = Boolean.parseBoolean(Application.getConfiguration().getParameter("mongodb.DropCollection"));
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
         if (((null != entity.name()) && (entity.name().length() > 0))) {
            collectionName = entity.name();
         }
         /*
          * drop?
          */
         if (true == dropCollection) {
            if (db.collectionExists(collectionName)) {
               db.getCollection(collectionName).drop();
            }
         }
         /*
          * get collection
          */
         final DBCollection ret = db.getCollection(collectionName);
         /*
          * done
          */
         return ret;
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
   public Class<String> getIdentifierClazz() {
      return String.class;
   }

   /**
    * get object by id
    */
   private DBObject getObjectById(String i) throws PragmatachException {
      try {
         final BasicDBObject query = new BasicDBObject(ID, i);
         final DBCursor cursor = this.dbCollection.find(query);
         if (cursor.hasNext()) {
            return cursor.next();
         } else {
            return null;
         }
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
   public T newInstance(DBObject dbObject) throws PragmatachException {
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
         /*
          * save
          */
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
         this.save(t);
      } catch (final Exception e) {
         throw new PragmatachException("Exception in update", e);
      }
   }
}
