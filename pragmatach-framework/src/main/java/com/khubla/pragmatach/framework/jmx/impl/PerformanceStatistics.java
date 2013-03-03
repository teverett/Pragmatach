package com.khubla.pragmatach.framework.jmx.impl;

import java.util.Date;

import com.khubla.pragmatach.framework.jmx.PerformanceStatisticsMXBean;

/**
 * @author tome
 */
public class PerformanceStatistics implements PerformanceStatisticsMXBean {
   /**
    * last render time (ms)
    */
   private long lastRenderTime;
   /**
    * start time
    */
   private final String serverStartTime = new Date().toString();
   /**
    * total requests
    */
   private long totalRequests;

   public long getLastRenderTime() {
      return lastRenderTime;
   }

   public String getServerStartTime() {
      return serverStartTime;
   }

   public long getTotalRequests() {
      return totalRequests;
   }

   public void incrementTotalRequests() {
      totalRequests++;
   }

   public void setLastRenderTime(long lastRenderTime) {
      this.lastRenderTime = lastRenderTime;
   }

   public void setTotalRequests(long totalRequests) {
      this.totalRequests = totalRequests;
   }
}
