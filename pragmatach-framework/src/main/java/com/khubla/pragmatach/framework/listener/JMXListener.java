package com.khubla.pragmatach.framework.listener;

import java.lang.management.*;

import javax.management.*;
import javax.servlet.*;

import org.slf4j.*;

import com.khubla.pragmatach.framework.jmx.*;
import com.khubla.pragmatach.framework.jmx.impl.*;
import com.khubla.pragmatach.framework.servlet.*;

/**
 * @author tome
 */
public class JMXListener implements ServletContextListener {
	/**
	 * logger
	 */
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

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
			if (mBeanServer.isRegistered(systemStatusBeanName)) {
				mBeanServer.unregisterMBean(systemStatusBeanName);
			}
			mBeanServer.registerMBean(systemStatusMBean, systemStatusBeanName);
			/*
			 * performance statistics
			 */
			final ObjectName performanceStatisticsBeanName = new ObjectName("com.khubla.pragmatach.framework.jmx.impl:type=PerformanceStatistics");
			final PerformanceStatisticsMXBean performanceStatisticsMXBean = PragmatachServlet.getPerformancestatistics();
			if (mBeanServer.isRegistered(performanceStatisticsBeanName)) {
				mBeanServer.unregisterMBean(performanceStatisticsBeanName);
			}
			mBeanServer.registerMBean(performanceStatisticsMXBean, performanceStatisticsBeanName);
			/*
			 * route cache
			 */
			final ObjectName routeCacheStatusBeanName = new ObjectName("com.khubla.pragmatach.framework.jmx.impl:type=RouteCacheStatus");
			final RouteCacheStatusMXBean routeCacheStatusMXBean = new RouteCacheStatus();
			if (mBeanServer.isRegistered(routeCacheStatusBeanName)) {
				mBeanServer.unregisterMBean(routeCacheStatusBeanName);
			}
			mBeanServer.registerMBean(routeCacheStatusMXBean, routeCacheStatusBeanName);
		} catch (final Exception e) {
			logger.error("Exception in contextInitialized", e);
		}
	}
}
