package com.khubla.pragmatach.framework.dao;

import java.io.Serializable;

import com.khubla.pragmatach.framework.api.PragmatachException;

/**
 * @author tome
 */
public abstract class AbstractDAO<T, I extends Serializable> implements DAO<T, I> {
   /**
    * get a pager
    */
   @Override
   public Pager<T> getPager(int batchsize) throws PragmatachException {
      return new PagerImpl<T, I>(this, batchsize);
   }
}
