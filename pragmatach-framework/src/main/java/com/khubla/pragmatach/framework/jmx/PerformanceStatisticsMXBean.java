package com.khubla.pragmatach.framework.jmx;

/**
 * @author tome
 */
public interface PerformanceStatisticsMXBean {
	public long getLastRenderTime();

	public String getServerStartTime();

	public long getTotalRequests();
}
