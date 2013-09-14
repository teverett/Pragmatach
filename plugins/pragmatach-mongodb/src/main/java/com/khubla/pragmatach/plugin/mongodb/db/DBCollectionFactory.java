package com.khubla.pragmatach.plugin.mongodb.db;

import javax.persistence.Entity;

import com.khubla.pragmatach.framework.api.PragmatachException;
import com.khubla.pragmatach.framework.application.Application;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;

/**
 * @author tom
 */
public class DBCollectionFactory {
   /**
    * singleton
    */
   private static DBCollectionFactory instance = null;

   private static DB getDB() throws PragmatachException {
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
         return db;
      } catch (final Exception e) {
         throw new PragmatachException("Exception in getDB", e);
      }
   }

   public static DBCollectionFactory getInstance() {
      if (null == instance) {
         instance = new DBCollectionFactory();
      }
      return instance;
   }

   /**
    * ctor
    */
   private DBCollectionFactory() {
   }

   /**
    * get the DBCollection
    */
   public <T> DBCollection getDBCollection(Class<T> clazz) {
      try {
         /*
          * DB
          */
         final DB db = getDB();
         /*
          * collection name
          */
         final Entity entity = getEntity(clazz);
         if (null == entity) {
            throw new Exception("Type '" + clazz.getCanonicalName() + "' is not annotated as an @Entity");
         }
         /*
          * collection
          */
         String collectionName = clazz.getSimpleName();
         if (((null != entity.name()) && (entity.name().length() > 0))) {
            collectionName = entity.name();
         }
         /*
          * drop?
          */
         final boolean dropCollection = Boolean.parseBoolean(Application.getConfiguration().getParameter("mongodb.DropCollection"));
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
   private <T> Entity getEntity(Class<T> clazz) {
      return clazz.getAnnotation(Entity.class);
   }
}
