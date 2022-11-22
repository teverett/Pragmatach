package com.khubla.pragmatach.framework.jmx;

/**
 * @author tome
 */
public interface RouteCacheStatusMXBean {
	public void clear();

	public long getSize();

	public long getTotalHits();

	public long getTotalRequests();
}
