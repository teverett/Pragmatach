package com.khubla.pragmatach.framework.api;

import java.io.Serializable;
import java.util.List;

/**
 * @author tome
 */
public interface DAO<T, I extends Serializable> {
   /**
    * delete
    */
   void delete(T t) throws PragmatachException;

   /**
    * delete
    */
   void deletebyId(I i) throws PragmatachException;

   /**
    * find all
    */
   List<T> findAll() throws PragmatachException;

   /**
    * find by id
    */
   T findById(I i) throws PragmatachException;

   Class<I> getIdentifierClazz();

   Class<T> getTypeClazz();

   /**
    * do a config reload
    */
   void reloadConfig();

   /**
    * save object
    */
   void save(T t) throws PragmatachException;

   /**
    * update object
    */
   void update(T t) throws PragmatachException;
}