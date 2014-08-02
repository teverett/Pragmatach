package com.khubla.pragmatach.framework.dao;

import java.util.List;

import com.khubla.pragmatach.framework.api.PragmatachException;

/**
 * an iterator for objects, produced by a DAO
 *
 * @author tome
 */
public interface Pager<T> {
   boolean hasNext();

   List<T> next() throws PragmatachException;
}
