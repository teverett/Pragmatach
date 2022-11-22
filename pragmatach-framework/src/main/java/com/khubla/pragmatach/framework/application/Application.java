package com.khubla.pragmatach.framework.application;

import com.khubla.pragmatach.framework.api.*;
import com.khubla.pragmatach.framework.controller.*;
import com.khubla.pragmatach.framework.lifecycle.*;
import com.khubla.pragmatach.framework.router.*;

/**
 * @author tome
 */
public class Application {
	/**
	 * configuration
	 */
	private static Configuration configuration;
	/**
	 * lifecycle listeners
	 */
	private static final LifecycleListeners lifecycleListeners = new LifecycleListeners();

	public static Configuration getConfiguration() {
		return configuration;
	}

	/**
	 * get all known controllers
	 */
	public static Controllers getControllers() {
		return Controllers.getInstance();
	}

	public static LifecycleListeners getLifecyclelisteners() {
		return lifecycleListeners;
	}

	/**
	 * get all known routes
	 */
	public static PragmatachRoutes getRoutes() throws PragmatachException {
		return PragmatachRoutes.getInstance();
	}

	public static void setConfiguration(Configuration configuration) {
		Application.configuration = configuration;
	}
}
