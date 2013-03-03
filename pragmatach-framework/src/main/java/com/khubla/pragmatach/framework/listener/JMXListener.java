package com.khubla.pragmatach.framework.listener;

import java.lang.management.ManagementFactory;

import javax.management.MBeanServer;
import javax.management.ObjectName;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.log4j.Logger;

import com.khubla.pragmatach.framework.jmx.PerformanceStatisticsMXBean;
import com.khubla.pragmatach.framework.jmx.RouteCacheStatusMXBean;
import com.khubla.pragmatach.framework.jmx.SystemStatusMXBean;
import com.khubla.pragmatach.framework.jmx.impl.RouteCacheStatus;
import com.khubla.pragmatach.framework.jmx.impl.SystemStatus;
import com.khubla.pragmatach.framework.servlet.PragmatachServlet;

/**
 * @author tome
 */
public class JMXListener implements ServletContextListener {
   /**
    * logger
    */
   private final Logger logger = Logger.getLogger(this.getClass());

   @Override
   public void contextDestroyed(ServletContextEvent servletContextEvent) {
   }

   @Override
   public void contextInitialized(ServletContextEvent servletContextEvent) {
      try {
         final MBeanServer mBeanServer = ManagementFactory.getPlatformMBeanServer();
         /*
          * system status bean
          */
         final ObjectName systemStatusBeanName = new ObjectName("com.khubla.pragmatach.framework.jmx.impl:type=SystemStatus");
         final SystemStatusMXBean systemStatusMBean = new SystemStatus();
         mBeanServer.registerMBean(systemStatusMBean, systemStatusBeanName);
         /*
          * performance statistics
          */
         final ObjectName performanceStatisticsBeanName = new ObjectName("com.khubla.pragmatach.framework.jmx.impl:type=PerformanceStatistics");
         final PerformanceStatisticsMXBean performanceStatisticsMXBean = PragmatachServlet.getPerformancestatistics();
         mBeanServer.registerMBean(performanceStatisticsMXBean, performanceStatisticsBeanName);
         /*
          * route cache
          */
         final ObjectName routeCacheStatusBeanName = new ObjectName("com.khubla.pragmatach.framework.jmx.impl:type=RouteCacheStatus");
         final RouteCacheStatusMXBean routeCacheStatusMXBean = new RouteCacheStatus();
         mBeanServer.registerMBean(routeCacheStatusMXBean, routeCacheStatusBeanName);
      } catch (final Exception e) {
         logger.fatal("Exception in contextInitialized", e);
      }
   }
}
