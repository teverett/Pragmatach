package com.khubla.pragmatach.framework.jmx.impl;

import java.util.*;

import org.slf4j.*;

import com.khubla.pragmatach.framework.api.*;
import com.khubla.pragmatach.framework.application.*;
import com.khubla.pragmatach.framework.controller.*;
import com.khubla.pragmatach.framework.jmx.*;
import com.khubla.pragmatach.framework.plugin.*;
import com.khubla.pragmatach.framework.router.*;

/**
 * @author tome
 */
public class SystemStatus implements SystemStatusMXBean {
	/**
	 * logger
	 */
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Override
	public String[] getConfiguration() {
		try {
			final List<String> configurations = new ArrayList<String>();
			for (final String key : Application.getConfiguration().getAll().keySet()) {
				configurations.add(key + ":" + Application.getConfiguration().getAll().get(key));
			}
			final String[] ret = new String[configurations.size()];
			configurations.toArray(ret);
			return ret;
		} catch (final Exception e) {
			logger.error("Exception in getConfiguration", e);
			return null;
		}
	}

	@Override
	public String[] getControllers() {
		try {
			final List<String> controllerDescriptions = new ArrayList<String>();
			for (final Class<?> controllerClass : Controllers.getInstance().getControllers()) {
				controllerDescriptions.add(controllerClass.getName());
			}
			final String[] ret = new String[controllerDescriptions.size()];
			controllerDescriptions.toArray(ret);
			return ret;
		} catch (final Exception e) {
			logger.error("Exception in getControllers", e);
			return null;
		}
	}

	@Override
	public String[] getPlugins() {
		try {
			final List<String> plugins = new ArrayList<String>();
			for (final Plugin plugin : PluginsRegistry.getPlugins().values()) {
				plugins.add(plugin.getName());
			}
			final String[] ret = new String[plugins.size()];
			plugins.toArray(ret);
			return ret;
		} catch (final Exception e) {
			logger.error("Exception in getPlugins", e);
			return null;
		}
	}

	@Override
	public String[] getRouters() {
		try {
			final List<String> routeDescriptions = new ArrayList<String>();
			for (final PragmatachRoute pragmatachRoute : PragmatachRoutes.getInstance().getGETRoutes()) {
				routeDescriptions.add(pragmatachRoute.getDescription());
			}
			for (final PragmatachRoute pragmatachRoute : PragmatachRoutes.getInstance().getPOSTRoutes()) {
				routeDescriptions.add(pragmatachRoute.getDescription());
			}
			final String[] ret = new String[routeDescriptions.size()];
			routeDescriptions.toArray(ret);
			return ret;
		} catch (final Exception e) {
			logger.error("Exception in getRouters", e);
			return null;
		}
	}
}
