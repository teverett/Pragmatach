package com.khubla.pragmatach.framework.jmx.impl;

import com.khubla.pragmatach.framework.jmx.*;
import com.khubla.pragmatach.framework.router.*;

/**
 * @author tome
 */
public class RouteCacheStatus implements RouteCacheStatusMXBean {
	@Override
	public void clear() {
		Router.getRoutecache().clear();
	}

	@Override
	public long getSize() {
		return Router.getRoutecache().size();
	}

	@Override
	public long getTotalHits() {
		return Router.getRoutecache().getTotalHits();
	}

	@Override
	public long getTotalRequests() {
		return Router.getRoutecache().getTotalRequests();
	}
}
