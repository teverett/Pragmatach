package com.khubla.pragmatach.plugin.ebean;

import java.io.Serializable;
import java.util.List;

import com.avaje.ebean.Ebean;
import com.avaje.ebean.EbeanServer;
import com.avaje.ebean.Query;
import com.khubla.pragmatach.framework.api.PragmatachException;

/**
 * @author tome
 */
public class EBeanDAO<T, I extends Serializable> {
   /**
    * EBean
    */
   private final EbeanServer ebeanServer = Ebean.getServer(null);
   /**
    * the type
    */
   private final Class<T> typeClazz;
   /**
    * the identifier
    */
   private final Class<I> identifierClazz;

   public EBeanDAO(Class<T> typeClazz, Class<I> identifierClazz) {
      this.typeClazz = typeClazz;
      this.identifierClazz = identifierClazz;
   }

   /**
    * delete
    */
   public void delete(T t) throws PragmatachException {
      try {
         ebeanServer.delete(t);
      } catch (final Exception e) {
         throw new PragmatachException("Exception in delete", e);
      }
   }

   /**
    * delete
    */
   public void deletebyId(I i) throws PragmatachException {
      try {
         ebeanServer.delete(typeClazz, i);
      } catch (final Exception e) {
         throw new PragmatachException("Exception in deletebyId", e);
      }
   }

   /**
    * find by fluent query
    */
   public Query<T> find() throws PragmatachException {
      return ebeanServer.find(this.typeClazz);
   }

   /**
    * findall
    */
   public List<T> findAll() throws PragmatachException {
      return ebeanServer.find(this.typeClazz).findList();
   }

   /**
    * find by id
    */
   public T findById(I i) throws PragmatachException {
      try {
         return ebeanServer.find(typeClazz, i);
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
         ebeanServer.save(t);
      } catch (final Exception e) {
         throw new PragmatachException("Exception in save", e);
      }
   }

   /**
    * update object
    */
   public void update(T t) throws PragmatachException {
      try {
         ebeanServer.update(t);
      } catch (final Exception e) {
         throw new PragmatachException("Exception in save", e);
      }
   }
}
