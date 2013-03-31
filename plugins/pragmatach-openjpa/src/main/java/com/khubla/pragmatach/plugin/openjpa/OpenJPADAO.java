package com.khubla.pragmatach.plugin.openjpa;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaBuilder;

import com.khubla.pragmatach.framework.api.PragmatachException;
import com.khubla.pragmatach.framework.application.Application;
import com.khubla.pragmatach.framework.scanner.AnnotationScanner;

/**
 * @author tome
 */
public class OpenJPADAO<T, I extends Serializable> {
   /**
    * create the EntityManager
    * <p>
    * http://openjpa.apache.org/builds/2.1.1/apache-openjpa/docs/ref_guide_conf.html
    * </p>
    */
   private static EntityManager getEntityManager() {
      try {
         /*
          * EntityManagerFactory
          */
         EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("Pragmatach", System.getProperties());
         /*
          * set up the properties
          */
         Map<String, String> properties = new HashMap<String, String>();
         properties.put("openjpa.ConnectionURL", Application.getConfiguration().getParameter("openjpa.ConnectionURL"));
         properties.put("openjpa.ConnectionDriverName", Application.getConfiguration().getParameter("openjpa.ConnectionDriverName"));
         properties.put("openjpa.ConnectionUserName", Application.getConfiguration().getParameter("openjpa.ConnectionUserName"));
         properties.put("openjpa.ConnectionPassword", Application.getConfiguration().getParameter("openjpa.ConnectionPassword"));
         /*
          * add classes
          */
         final Set<Class<?>> entityClasses = getEntityClasses();
         if (null != entityClasses) {
            for (final Class<?> clazz : entityClasses) {
               // serverConfig.addClass(clazz);
            }
         }
         /*
          * the EntityManager
          */
         return entityManagerFactory.createEntityManager(properties);
      } catch (final Exception e) {
         throw new ExceptionInInitializerError(e);
      }
   }

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
    * EntityManager
    */
   private final EntityManager entityManager = getEntityManager();
   /**
    * the type
    */
   private final Class<T> typeClazz;
   /**
    * the identifier
    */
   private final Class<I> identifierClazz;

   public OpenJPADAO(Class<T> typeClazz, Class<I> identifierClazz) {
      this.typeClazz = typeClazz;
      this.identifierClazz = identifierClazz;
   }

   /**
    * delete
    */
   public void delete(T t) throws PragmatachException {
      try {
         entityManager.remove(t);
      } catch (final Exception e) {
         throw new PragmatachException("Exception in delete", e);
      }
   }

   /**
    * delete
    */
   public void deletebyId(I i) throws PragmatachException {
      try {
         T t = entityManager.find(typeClazz, i);
         if (null != t) {
            entityManager.remove(t);
         }
      } catch (final Exception e) {
         throw new PragmatachException("Exception in deletebyId", e);
      }
   }

   /**
    * get criteria builder
    */
   public CriteriaBuilder find() throws PragmatachException {
      return entityManager.getCriteriaBuilder();
   }

   /**
    * findall
    */
   public List<T> findAll() throws PragmatachException {
      return null;
   }

   /**
    * find by id
    */
   public T findById(I i) throws PragmatachException {
      try {
         return entityManager.find(typeClazz, i);
      } catch (final Exception e) {
         throw new PragmatachException("Exception in findById", e);
      }
   }

   public Class<I> getIdentifierClazz() {
      return identifierClazz;
   }

   public Class<T> getTypeClazz() {
      return typeClazz;
   }

   /**
    * save object
    */
   public void save(T t) throws PragmatachException {
      try {
         entityManager.persist(t);
      } catch (final Exception e) {
         throw new PragmatachException("Exception in save", e);
      }
   }

   /**
    * update object
    */
   public void update(T t) throws PragmatachException {
      try {
         entityManager.merge(t);
      } catch (final Exception e) {
         throw new PragmatachException("Exception in save", e);
      }
   }
}
