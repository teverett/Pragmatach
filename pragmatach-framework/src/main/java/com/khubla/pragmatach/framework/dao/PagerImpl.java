package com.khubla.pragmatach.framework.dao;

import java.util.*;

import com.khubla.pragmatach.framework.api.*;

/**
 * @author tome
 */
public class PagerImpl<T, I> implements Pager<T> {
	/**
	 * number of records to return at a time
	 */
	private final int batchsize;
	/**
	 * current records
	 */
	private int currentRecords = 0;
	/**
	 * total size
	 */
	private final int totalRecords;
	/**
	 * dao
	 */
	private final DAO<T, I> dao;

	/**
	 * ctor
	 */
	public PagerImpl(DAO<T, I> dao, int batchsize) throws PragmatachException {
		this.batchsize = batchsize;
		this.dao = dao;
		this.totalRecords = (int) dao.count();
	}

	public int getBatchsize() {
		return batchsize;
	}

	@Override
	public boolean hasNext() {
		return (currentRecords < totalRecords) ? true : false;
	}

	@Override
	public List<T> next() throws PragmatachException {
		try {
			/*
			 * query
			 */
			final List<T> ret = dao.getAll(currentRecords, batchsize);
			if (null != ret) {
				currentRecords += ret.size();
			}
			return ret;
		} catch (final Exception e) {
			throw new PragmatachException("Exception in next", e);
		}
	}
}
