package com.khubla.pragmatach.framework.listener;

import java.lang.reflect.*;
import java.util.*;

import javax.servlet.*;

import org.slf4j.*;

import com.khubla.pragmatach.framework.annotation.*;
import com.khubla.pragmatach.framework.api.*;
import com.khubla.pragmatach.framework.application.*;
import com.khubla.pragmatach.framework.controller.*;
import com.khubla.pragmatach.framework.i8n.*;
import com.khubla.pragmatach.framework.plugin.*;
import com.khubla.pragmatach.framework.router.*;
import com.khubla.pragmatach.framework.scanner.*;

/**
 * @author tome
 */
public class StartupListener implements ServletContextListener {
	/**
	 * configuration
	 */
	private static final String CONFIGURATION = "configuration";
	/**
	 * logger
	 */
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Override
	public void contextDestroyed(ServletContextEvent servletContextEvent) {
		try {
			invokeShutdownMethods();
		} catch (final Exception e) {
			logger.error("Exception in contextDestroyed", e);
		}
	}

	@Override
	public void contextInitialized(ServletContextEvent servletContextEvent) {
		try {
			/*
			 * get the configuration
			 */
			loadConfiguration(servletContextEvent.getServletContext());
			/*
			 * scan the annotations (@Route and @Controller, and everything else)
			 */
			AnnotationScanner.scan();
			/*
			 * find the controllers and routes
			 */
			ControllerClasses.buildDB();
			/*
			 * scan the plugins
			 */
			PluginsRegistry.scan(servletContextEvent.getServletContext());
			/*
			 * start all the plugins
			 */
			for (final Plugin plugin : PluginsRegistry.getPlugins().values()) {
				plugin.startup();
			}
			/*
			 * this loads and validates the routes
			 */
			PragmatachRoutes.getInstance();
			/*
			 * report the routes
			 */
			reportRoutes();
			/*
			 * load the i8N providers
			 */
			I8NProviders.getInstance();
			/*
			 * startup methods
			 */
			invokeStartupMethods();
		} catch (final Exception e) {
			logger.error("Exception in contextInitialized", e);
		}
	}

	private void invokeShutdownMethods() throws PragmatachException {
		try {
			final Set<Class<?>> classes = AnnotationScanner.getAllClasses(OnShutdown.class);
			for (final Class<?> clazz : classes) {
				for (final Method method : clazz.getDeclaredMethods()) {
					if (Modifier.isStatic(method.getModifiers())) {
						if (null != method.getAnnotation(OnShutdown.class)) {
							method.invoke(null, (Object[]) null);
						}
					}
				}
			}
		} catch (final Exception e) {
			throw new PragmatachException(e);
		}
	}

	private void invokeStartupMethods() throws PragmatachException {
		try {
			final Set<Class<?>> classes = AnnotationScanner.getAllClasses(OnStartup.class);
			for (final Class<?> clazz : classes) {
				for (final Method method : clazz.getDeclaredMethods()) {
					if (Modifier.isStatic(method.getModifiers())) {
						if (null != method.getAnnotation(OnStartup.class)) {
							method.invoke(null, (Object[]) null);
						}
					}
				}
			}
		} catch (final Exception e) {
			throw new PragmatachException(e);
		}
	}

	/**
	 * load the application configuration
	 */
	private void loadConfiguration(ServletContext servletContext) throws Exception {
		try {
			/*
			 * get the name
			 */
			final String configurationClassName = servletContext.getInitParameter(CONFIGURATION);
			if (null != configurationClassName) {
				/*
				 * get the class
				 */
				logger.info("Pragmatach configuration loaded from class '" + configurationClassName + "'");
				final Class<?> configurationClazz = Class.forName(configurationClassName);
				/*
				 * get the configuration
				 */
				Application.setConfiguration((Configuration) configurationClazz.newInstance());
			} else {
				throw new ServletException("Configuration parameter '" + CONFIGURATION + "' not found");
			}
		} catch (final Exception e) {
			throw new Exception("Exception in loadConfiguration", e);
		}
	}

	private void reportRoutes() {
		try {
			/*
			 * get routes
			 */
			final PragmatachRoutes pragmatachRoutes = PragmatachRoutes.getInstance();
			/*
			 * GET routes
			 */
			logger.info("Ordered GET routes");
			for (final PragmatachRoute pragmatachRoute : pragmatachRoutes.getGETRoutes()) {
				logger.info("GET " + pragmatachRoute.getDescription());
			}
			/*
			 * POST routes
			 */
			logger.info("Ordered POST routes");
			for (final PragmatachRoute pragmatachRoute : pragmatachRoutes.getPOSTRoutes()) {
				logger.info("POST " + pragmatachRoute.getDescription());
			}
		} catch (final Exception e) {
			logger.error("Exceptioin in reportRoutes", e);
			e.printStackTrace();
		}
	}
}