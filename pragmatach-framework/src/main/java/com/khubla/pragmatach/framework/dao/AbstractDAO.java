package com.khubla.pragmatach.framework.dao;

import com.khubla.pragmatach.framework.api.PragmatachException;

/**
 * @author tome
 */
public abstract class AbstractDAO<T, I> implements DAO<T, I> {
   /**
    * get a pager
    */
   @Override
   public Pager<T> getPager(int batchsize) throws PragmatachException {
      return new PagerImpl<T, I>(this, batchsize);
   }
}
