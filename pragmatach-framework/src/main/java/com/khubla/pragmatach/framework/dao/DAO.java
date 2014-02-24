package com.khubla.pragmatach.framework.dao;

import java.util.List;

import com.khubla.pragmatach.framework.api.PragmatachException;

/**
 * @author tome
 */
public interface DAO<T, I> {
   /**
    * get the count of rows in table
    */
   long count() throws PragmatachException;

   /**
    * delete
    */
   void delete(T t) throws PragmatachException;

   /**
    * delete
    */
   void deletebyId(I i) throws PragmatachException;

   /**
    * find by id
    */
   T findById(I i) throws PragmatachException;

   /**
    * get all
    */
   List<T> getAll() throws PragmatachException;

   /**
    * get all
    */
   List<T> getAll(int start, int count) throws PragmatachException;

   Class<I> getIdentifierClazz();

   /**
    * get pager
    */
   Pager<T> getPager(int batchsize) throws PragmatachException;

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