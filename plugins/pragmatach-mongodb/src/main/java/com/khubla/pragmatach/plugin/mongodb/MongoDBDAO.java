package com.khubla.pragmatach.plugin.mongodb;

import java.util.ArrayList;
import java.util.List;

import com.khubla.pragmatach.framework.api.PragmatachException;
import com.khubla.pragmatach.framework.dao.AbstractDAO;
import com.khubla.pragmatach.plugin.mongodb.db.DBCollectionFactory;
import com.khubla.pragmatach.plugin.mongodb.util.ClassTypeUtils;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;

/**
 * @author tome
 */
public class MongoDBDAO<T> extends AbstractDAO<T, String> {
   /**
    * DBCollection
    */
   private DBCollection dbCollection;
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
   /**
    * persister
    */
   private final MongoDBObjectPersister mongoDBObjectPersister;

   public MongoDBDAO(Class<T> typeClazz) {
      this.typeClazz = typeClazz;
      this.typeUtils = new ClassTypeUtils(this.typeClazz);
      mongoDBObjectPersister = new MongoDBObjectPersister(typeClazz);
      this.dbCollection = DBCollectionFactory.getInstance().getDBCollection(typeClazz);
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
         final BasicDBObject basicDBObject = new BasicDBObject();
         basicDBObject.append(ID, this.typeUtils.getId(t));
         this.dbCollection.findAndRemove(basicDBObject);
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
      ArrayList<T> ret = null;
      final DBCursor cursor = this.dbCollection.find(query);
      if (cursor.hasNext()) {
         ret = new ArrayList<T>();
         while (cursor.hasNext()) {
            ret.add(this.newInstance(cursor.next()));
         }
      }
      cursor.close();
      return ret;
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
      ArrayList<T> ret = null;
      final DBCursor cursor = this.dbCollection.find(query);
      if (cursor.hasNext()) {
         ret = new ArrayList<T>();
         while (cursor.hasNext()) {
            ret.add(this.newInstance(cursor.next()));
         }
      }
      cursor.close();
      return ret;
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
      T ret = null;
      final DBCursor cursor = this.dbCollection.find(query);
      if (cursor.hasNext()) {
         ret = this.newInstance(cursor.next());
      }
      cursor.close();
      return ret;
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
      T ret = null;
      final DBCursor cursor = this.dbCollection.find(query);
      if (cursor.hasNext()) {
         ret = this.newInstance(cursor.next());
      }
      cursor.close();
      return ret;
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

   @Override
   public Class<String> getIdentifierClazz() {
      return String.class;
   }

   /**
    * get object by id
    */
   private DBObject getObjectById(String i) throws PragmatachException {
      try {
         DBObject ret = null;
         final BasicDBObject query = new BasicDBObject(ID, i);
         final DBCursor cursor = this.dbCollection.find(query);
         if (cursor.hasNext()) {
            ret = cursor.next();
         }
         cursor.close();
         return ret;
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
   @SuppressWarnings("unchecked")
   public T newInstance(DBObject dbObject) throws PragmatachException {
      try {
         return (T) this.mongoDBObjectPersister.load(dbObject);
      } catch (final Exception e) {
         throw new PragmatachException("Exception in newInstance", e);
      }
   }

   @Override
   public void reloadConfig() {
      this.dbCollection = DBCollectionFactory.getInstance().getDBCollection(typeClazz);
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
         this.mongoDBObjectPersister.save(t);
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
