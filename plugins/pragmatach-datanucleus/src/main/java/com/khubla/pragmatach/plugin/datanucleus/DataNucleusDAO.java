package com.khubla.pragmatach.plugin.datanucleus;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.jdo.JDOEnhancer;
import javax.jdo.JDOHelper;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

import org.datanucleus.api.jpa.JPAEntityManagerFactory;
import org.datanucleus.metadata.PersistenceUnitMetaData;

import com.khubla.pragmatach.framework.api.PragmatachException;
import com.khubla.pragmatach.framework.application.Application;
import com.khubla.pragmatach.framework.dao.AbstractDAO;
import com.khubla.pragmatach.framework.scanner.AnnotationScanner;

/**
 * @author tome
 */
public class DataNucleusDAO<T, I extends Serializable> extends AbstractDAO<T, I> {
   /**
    * EntityManager
    */
   private static EntityManager entityManager = getEntityManager();

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
    * create the EntityManager
    */
   private static EntityManager getEntityManager() {
      try {
         /*
          * get the entity classes
          */
         final Set<Class<?>> entityClasses = getEntityClasses();
         /*
          * set up the properties
          */
         final Map<String, String> properties = new HashMap<String, String>();
         final String dataSource = Application.getConfiguration().getParameter("datanucleus.ConnectionFactoryName");
         if ((null != dataSource) && (dataSource.length() > 0)) {
            properties.put("datanucleus.ConnectionFactoryName", dataSource);
         } else {
            properties.put("datanucleus.ConnectionURL", Application.getConfiguration().getParameter("datanucleus.ConnectionURL"));
            properties.put("datanucleus.ConnectionDriverName", Application.getConfiguration().getParameter("datanucleus.ConnectionDriverName"));
            properties.put("datanucleus.ConnectionUserName", Application.getConfiguration().getParameter("datanucleus.ConnectionUserName"));
            properties.put("datanucleus.ConnectionPassword", Application.getConfiguration().getParameter("datanucleus.ConnectionPassword"));
         }
         /*
          * more properties
          */
         properties.put("datanucleus.autoCreateSchema", Application.getConfiguration().getParameter("datanucleus.autoCreateSchema"));
         /*
          * add classes at runtime
          */
         JDOEnhancer enhancer = JDOHelper.getEnhancer();
         enhancer.setVerbose(true);
         /*
          * enhance classes
          */
         if (null != entityClasses) {
            for (final Class<?> clazz : entityClasses) {
               enhancer.addClasses(clazz.getCanonicalName());
            }
         }
         /*
          * enhance
          */
         enhancer.enhance();
         /*
          * get the enhanced classes
          */
         DataNucleusClassLoader dataNucleusClassLoader = new DataNucleusClassLoader(Thread.currentThread().getContextClassLoader());
         PersistenceUnitMetaData persistenceUnitMetaData = new PersistenceUnitMetaData("dynamic-unit", "RESOURCE_LOCAL", null);
         if (null != entityClasses) {
            for (final Class<?> clazz : entityClasses) {
               byte[] enhancedBytes = enhancer.getEnhancedBytes(clazz.getCanonicalName());
               dataNucleusClassLoader.addClass(clazz.getCanonicalName(), enhancedBytes);
               persistenceUnitMetaData.addClassName(clazz.getCanonicalName());
            }
         }
         /*
          * EntityManagerFactory
          */
         final EntityManagerFactory entityManagerFactory = new JPAEntityManagerFactory(persistenceUnitMetaData, properties);
         /*
          * the EntityManager
          */
         return entityManagerFactory.createEntityManager();
      } catch (final Exception e) {
         throw new ExceptionInInitializerError(e);
      }
   }

   /**
    * the type
    */
   private final Class<T> typeClazz;
   /**
    * the identifier
    */
   private final Class<I> identifierClazz;

   public DataNucleusDAO(Class<T> typeClazz, Class<I> identifierClazz) {
      this.typeClazz = typeClazz;
      this.identifierClazz = identifierClazz;
   }

   @Override
   public long count() throws PragmatachException {
      final CriteriaBuilder qb = entityManager.getCriteriaBuilder();
      final CriteriaQuery<Long> cq = qb.createQuery(Long.class);
      cq.select(qb.count(cq.from(this.typeClazz)));
      return entityManager.createQuery(cq).getSingleResult();
   }

   /**
    * delete
    */
   @Override
   public void delete(T t) throws PragmatachException {
      EntityTransaction entityTransaction = null;
      try {
         entityTransaction = entityManager.getTransaction();
         entityTransaction.begin();
         entityManager.remove(t);
         entityTransaction.commit();
      } catch (final Exception e) {
         if (null != entityTransaction) {
            entityTransaction.rollback();
         }
         throw new PragmatachException("Exception in delete", e);
      }
   }

   /**
    * delete
    */
   @Override
   public void deletebyId(I i) throws PragmatachException {
      EntityTransaction entityTransaction = null;
      try {
         entityTransaction = entityManager.getTransaction();
         entityTransaction.begin();
         final T t = entityManager.find(typeClazz, i);
         if (null != t) {
            entityManager.remove(t);
         }
         entityTransaction.commit();
      } catch (final Exception e) {
         if (null != entityTransaction) {
            entityTransaction.rollback();
         }
         throw new PragmatachException("Exception in deletebyId", e);
      }
   }

   /**
    * get criteria builder
    */
   public CriteriaQuery<T> find() throws PragmatachException {
      return entityManager.getCriteriaBuilder().createQuery(this.typeClazz);
   }

   /**
    * find by id
    */
   @Override
   public T findById(I i) throws PragmatachException {
      try {
         return entityManager.find(typeClazz, i);
      } catch (final Exception e) {
         throw new PragmatachException("Exception in findById", e);
      }
   }

   /**
    * findall
    */
   @Override
   public List<T> getAll() throws PragmatachException {
      final CriteriaQuery<T> criteria = entityManager.getCriteriaBuilder().createQuery(typeClazz);
      criteria.select(criteria.from(typeClazz));
      return entityManager.createQuery(criteria).getResultList();
   }

   @Override
   public List<T> getAll(int start, int count) throws PragmatachException {
      return entityManager.createQuery(this.find()).setFirstResult(start).setMaxResults(count).getResultList();
   }

   @Override
   public Class<I> getIdentifierClazz() {
      return identifierClazz;
   }

   @Override
   public Class<T> getTypeClazz() {
      return typeClazz;
   }

   @Override
   public void reloadConfig() {
      entityManager = getEntityManager();
   }

   /**
    * save object
    */
   @Override
   public void save(T t) throws PragmatachException {
      EntityTransaction entityTransaction = null;
      try {
         entityTransaction = entityManager.getTransaction();
         entityTransaction.begin();
         entityManager.persist(t);
         entityTransaction.commit();
      } catch (final Exception e) {
         if (null != entityTransaction) {
            entityTransaction.rollback();
         }
         throw new PragmatachException("Exception in save", e);
      }
   }

   /**
    * update object
    */
   @Override
   public void update(T t) throws PragmatachException {
      EntityTransaction entityTransaction = null;
      try {
         entityTransaction = entityManager.getTransaction();
         entityTransaction.begin();
         entityManager.merge(t);
         entityTransaction.commit();
      } catch (final Exception e) {
         if (null != entityTransaction) {
            entityTransaction.rollback();
         }
         throw new PragmatachException("Exception in update", e);
      }
   }
}
