package com.khubla.pragmatach.plugin.hibernate;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.khubla.pragmatach.framework.api.PragmatachException;

/**
 * @author tome
 */
public class HibernateDAO<T, I extends Serializable> {
   /**
    * the type
    */
   private final Class<T> typeClazz;
   /**
    * the identifier
    */
   private final Class<I> identifierClazz;

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
         session = HibernateSessionUtil.getSessionFactory().openSession();
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
         session = HibernateSessionUtil.getSessionFactory().openSession();
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
         session = HibernateSessionUtil.getSessionFactory().openSession();
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
         session = HibernateSessionUtil.getSessionFactory().openSession();
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
         session = HibernateSessionUtil.getSessionFactory().openSession();
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
         session = HibernateSessionUtil.getSessionFactory().openSession();
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
         session = HibernateSessionUtil.getSessionFactory().openSession();
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
