package com.khubla.pragmatach.plugin.mongodb;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Entity;

import com.khubla.pragmatach.framework.api.PragmatachException;
import com.khubla.pragmatach.framework.application.Application;
import com.khubla.pragmatach.framework.scanner.AnnotationScanner;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;

/**
 * @author tome
 */
public class MongoDBDAO<T, I extends Serializable> /* extends AbstractDAO<T, I> */{
   /**
    * the annotation scanner will have run; we can just query for annotated classes
    */
   protected static Set<Class<?>> getEntityClasses() throws PragmatachException {
      try {
         return AnnotationScanner.getAll(Entity.class);
      } catch (final Exception e) {
         throw new PragmatachException("Exception in getAnnotatedClasses", e);
      }
   }

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

   /**
    * get the DBCollection
    */
   private DBCollection getDBCollection() {
      try {
         /*
          * params
          */
         final String hostname = Application.getConfiguration().getParameter("mongo.Hostname");
         final String database = Application.getConfiguration().getParameter("mongo.Database");
         final String username = Application.getConfiguration().getParameter("mongo.Username");
         final String password = Application.getConfiguration().getParameter("mongo.Password");
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

   // @Override
   // public long count() throws PragmatachException {
   // final CriteriaBuilder qb = entityManager.getCriteriaBuilder();
   // final CriteriaQuery<Long> cq = qb.createQuery(Long.class);
   // cq.select(qb.count(cq.from(this.typeClazz)));
   // return entityManager.createQuery(cq).getSingleResult();
   // }
   // /**
   // * delete
   // */
   // public void delete(T t) throws PragmatachException {
   // EntityTransaction entityTransaction = null;
   // try {
   // entityTransaction = entityManager.getTransaction();
   // entityTransaction.begin();
   // entityManager.remove(t);
   // entityTransaction.commit();
   // } catch (final Exception e) {
   // if (null != entityTransaction) {
   // entityTransaction.rollback();
   // }
   // throw new PragmatachException("Exception in delete", e);
   // }
   // }
   // /**
   // * delete
   // */
   // public void deletebyId(I i) throws PragmatachException {
   // EntityTransaction entityTransaction = null;
   // try {
   // entityTransaction = entityManager.getTransaction();
   // entityTransaction.begin();
   // final T t = entityManager.find(typeClazz, i);
   // if (null != t) {
   // entityManager.remove(t);
   // }
   // entityTransaction.commit();
   // } catch (final Exception e) {
   // if (null != entityTransaction) {
   // entityTransaction.rollback();
   // }
   // throw new PragmatachException("Exception in deletebyId", e);
   // }
   // }
   // /**
   // * get criteria builder
   // */
   // public CriteriaQuery<T> find() throws PragmatachException {
   // return entityManager.getCriteriaBuilder().createQuery(this.typeClazz);
   // }
   // /**
   // * find by id
   // */
   // public T findById(I i) throws PragmatachException {
   // try {
   // return entityManager.find(typeClazz, i);
   // } catch (final Exception e) {
   // throw new PragmatachException("Exception in findById", e);
   // }
   // }
   // /**
   // * findall
   // */
   // public List<T> getAll() throws PragmatachException {
   // final CriteriaQuery<T> criteria = entityManager.getCriteriaBuilder().createQuery(typeClazz);
   // criteria.select(criteria.from(typeClazz));
   // return entityManager.createQuery(criteria).getResultList();
   // }
   // @Override
   // public List<T> getAll(int start, int count) throws PragmatachException {
   // return entityManager.createQuery(this.find()).setFirstResult(start).setMaxResults(count).getResultList();
   // }
   public Class<I> getIdentifierClazz() {
      return identifierClazz;
   }

   public Class<T> getTypeClazz() {
      return typeClazz;
   }

   public void reloadConfig() {
      this.dbCollection = this.getDBCollection();
   }

   /**
    * save object
    */
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
   public void update(T t) throws PragmatachException {
      try {
         final BasicDBObject basicDBObject = mongoDBJSONSerializer.serialize(t);
         this.dbCollection.save(basicDBObject);
      } catch (final Exception e) {
         throw new PragmatachException("Exception in update", e);
      }
   }
}
