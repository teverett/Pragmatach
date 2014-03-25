package com.khubla.pragmatach.framework.cache;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * A simple LRU cache, based on multiple examples from "The Google".
 * 
 * @author tome
 */
public class LRUCache<K, V> {
	/**
	 * load factor
	 */
	private static final float hashTableLoadFactor = 0.75f;
	/**
	 * map of entries
	 */
	private LinkedHashMap<K, V> map;
	/**
	 * the cache size
	 */
	private int cacheSize;
	/**
	 * total requests
	 */
	private long totalRequests = 0;
	/**
	 * total hits
	 */
	private long totalHits = 0;

	/**
	 * ctor
	 */
	public LRUCache(int cacheSize) {
		this.cacheSize = cacheSize;
		final int hashTableCapacity = (int) Math.ceil(cacheSize
				/ hashTableLoadFactor) + 1;
		map = new LinkedHashMap<K, V>(hashTableCapacity, hashTableLoadFactor,
				true) {
			private static final long serialVersionUID = 1;

			@Override
			protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
				return map.size() > LRUCache.this.cacheSize;
			}
		};
	}

	public synchronized void clear() {
		this.totalHits = 0;
		this.totalRequests = 0;
		map.clear();
	}

	public synchronized V get(K key) {
		final V v = map.get(key);
		this.totalRequests++;
		if (null != v) {
			this.totalHits++;
		}
		return v;
	}

	public synchronized Collection<Map.Entry<K, V>> getAll() {
		return new ArrayList<Map.Entry<K, V>>(map.entrySet());
	}

	public long getTotalHits() {
		return totalHits;
	}

	public long getTotalRequests() {
		return totalRequests;
	}

	public synchronized void put(K key, V value) {
		map.put(key, value);
	}

	public synchronized int size() {
		return map.size();
	}
}