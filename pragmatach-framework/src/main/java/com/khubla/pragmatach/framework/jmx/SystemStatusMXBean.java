package com.khubla.pragmatach.framework.jmx;

/**
 * @author tome
 */
public interface SystemStatusMXBean {
   String[] getConfiguration();

   String[] getControllers();

   String[] getPlugins();

   String[] getRouters();
}
