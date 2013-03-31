package com.khubla.pragmatach.plugin.hibernate;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

import javax.persistence.Entity;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

import com.khubla.pragmatach.framework.api.PragmatachException;
import com.khubla.pragmatach.framework.application.Application;
import com.khubla.pragmatach.framework.scanner.AnnotationScanner;

/**
 * @author tome
 */
public class HibernateDAO<T, I extends Serializable> {
   public static SessionFactory getSessionFactory() {
      return sessionFactory;
   }

   /**
    * the type
    */
   private final Class<T> typeClazz;
   /**
    * the identifier
    */
   private final Class<I> identifierClazz;
   /**
    * the hibernate session factory
    */
   private static final SessionFactory sessionFactory = buildSessionFactory();
   /**
    * the hibernate service registry
    */
   private static ServiceRegistry serviceRegistry;

   private static SessionFactory buildSessionFactory() {
      try {
         /*
          * make config
          */
         final Configuration configuration = new Configuration();
         configuration.setProperty("hibernate.driver", Application.getConfiguration().getParameter("hibernate.driver"));
         configuration.setProperty("hibernate.dialect", Application.getConfiguration().getParameter("hibernate.dialect"));
         configuration.setProperty("hibernate.connection.url", Application.getConfiguration().getParameter("hibernate.connection.url"));
         configuration.setProperty("hibernate.connection.username", Application.getConfiguration().getParameter("hibernate.connection.username"));
         configuration.setProperty("hibernate.connection.password", Application.getConfiguration().getParameter("hibernate.connection.password"));
         configuration.setProperty("hibernate.hbm2ddl.auto", Application.getConfiguration().getParameter("hibernate.hbm2ddl.auto"));
         /*
          * add classes
          */
         final Set<Class<?>> entityClasses = getEntityClasses();
         if (null != entityClasses) {
            for (final Class<?> clazz : entityClasses) {
               configuration.addAnnotatedClass(clazz);
            }
         }
         /*
          * go for it
          */
         serviceRegistry = new ServiceRegistryBuilder().applySettings(configuration.getProperties()).buildServiceRegistry();
         return configuration.buildSessionFactory(serviceRegistry);
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

   public HibernateDAO(Class<T> typeClazz, Class<I> identifierClazz) {
      this.typeClazz = typeClazz;
      this.identifierClazz = identifierClazz;
   }

   /**
    * delete
    */
   public void delete(T t) throws PragmatachException {
      Session session = null;
      Transaction transaction = null;
      try {
         session = getSessionFactory().openSession();
         transaction = session.beginTransaction();
         session.delete(t);
         transaction.commit();
      } catch (final Exception e) {
         if (null != transaction) {
            transaction.rollback();
         }
         throw new PragmatachException("Exception in delete", e);
      } finally {
         if (null != session) {
            session.close();
         }
      }
   }

   /**
    * delete
    */
   @SuppressWarnings("unchecked")
   public void deletebyId(I i) throws PragmatachException {
      Session session = null;
      Transaction transaction = null;
      try {
         session = getSessionFactory().openSession();
         transaction = session.beginTransaction();
         final T t = (T) session.get(typeClazz, i);
         if (null != t) {
            session.delete(t);
         }
         transaction.commit();
      } catch (final Exception e) {
         if (null != transaction) {
            transaction.rollback();
         }
         throw new PragmatachException("Exception in deletebyId", e);
      } finally {
         if (null != session) {
            session.close();
         }
      }
   }

   /**
    * find by fluent query. note that this leaves a session open!
    */
   public Criteria find() throws PragmatachException {
      Session session = null;
      try {
         session = getSessionFactory().openSession();
         return session.createCriteria(this.typeClazz);
      } catch (final Exception e) {
         throw new PragmatachException("Exception in find", e);
      }
   }

   /**
    * findall
    */
   @SuppressWarnings("unchecked")
   public List<T> findAll() throws PragmatachException {
      Session session = null;
      try {
         session = getSessionFactory().openSession();
         final Criteria criteria = session.createCriteria(this.typeClazz);
         return criteria.list();
      } catch (final Exception e) {
         throw new PragmatachException("Exception in findAll", e);
      } finally {
         if (null != session) {
            session.close();
         }
      }
   }

   /**
    * find by id
    */
   @SuppressWarnings("unchecked")
   public T findById(I i) throws PragmatachException {
      Session session = null;
      try {
         session = getSessionFactory().openSession();
         return (T) session.get(typeClazz, i);
      } catch (final Exception e) {
         throw new PragmatachException("Exception in findById", e);
      } finally {
         if (null != session) {
            session.close();
         }
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
      Session session = null;
      Transaction transaction = null;
      try {
         session = getSessionFactory().openSession();
         transaction = session.beginTransaction();
         session.save(t);
         transaction.commit();
      } catch (final Exception e) {
         if (null != transaction) {
            transaction.rollback();
         }
         throw new PragmatachException("Exception in save", e);
      } finally {
         if (null != session) {
            session.close();
         }
      }
   }

   /**
    * update object
    */
   public void update(T t) throws PragmatachException {
      Session session = null;
      Transaction transaction = null;
      try {
         session = getSessionFactory().openSession();
         transaction = session.beginTransaction();
         session.update(t);
         transaction.commit();
      } catch (final Exception e) {
         if (null != transaction) {
            transaction.rollback();
         }
         throw new PragmatachException("Exception in update", e);
      } finally {
         if (null != session) {
            session.close();
         }
      }
   }
}
