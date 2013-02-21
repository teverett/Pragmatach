package com.khubla.pragmatach.framework.listener;

import java.lang.management.ManagementFactory;

import javax.management.MBeanServer;
import javax.management.ObjectName;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.log4j.Logger;

import com.khubla.pragmatach.framework.jmx.SystemStatusMXBean;
import com.khubla.pragmatach.framework.jmx.impl.SystemStatus;

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
      // TODO Auto-generated method stub
   }

   @Override
   public void contextInitialized(ServletContextEvent servletContextEvent) {
      try {
         final MBeanServer mBeanServer = ManagementFactory.getPlatformMBeanServer();
         /**
          * system status bean
          */
         final ObjectName name = new ObjectName("com.khubla.pragmatach.framework.jmx.impl:type=SystemStatus");
         final SystemStatusMXBean systemStatusMBean = new SystemStatus();
         mBeanServer.registerMBean(systemStatusMBean, name);
      } catch (final Exception e) {
         logger.fatal("Exception in contextInitialized", e);
      }
   }
}
