package com.khubla.pragmatach.framework.dao;

import java.util.*;

import com.khubla.pragmatach.framework.api.*;

/**
 * an iterator for objects, produced by a DAO
 *
 * @author tome
 */
public interface Pager<T> {
	boolean hasNext();

	List<T> next() throws PragmatachException;
}
