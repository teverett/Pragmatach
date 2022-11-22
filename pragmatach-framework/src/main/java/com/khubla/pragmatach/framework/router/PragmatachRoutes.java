package com.khubla.pragmatach.framework.router;

import java.lang.reflect.*;
import java.util.*;

import com.khubla.pragmatach.framework.annotation.*;
import com.khubla.pragmatach.framework.api.*;
import com.khubla.pragmatach.framework.controller.*;

/**
 * all routes, both GET and POST, sorted.
 *
 * @author tome
 */
public class PragmatachRoutes {
	/**
	 * singleton
	 */
	private static PragmatachRoutes instance = null;

	/**
	 * getter
	 */
	public static PragmatachRoutes getInstance() throws PragmatachException {
		try {
			if (null == instance) {
				instance = new PragmatachRoutes();
			}
			return instance;
		} catch (final Exception e) {
			throw new PragmatachException(e);
		}
	}

	/**
	 * GET routes
	 */
	private final List<PragmatachRoute> GETRoutes = new ArrayList<PragmatachRoute>();
	/**
	 * POST routes
	 */
	private final List<PragmatachRoute> POSTRoutes = new ArrayList<PragmatachRoute>();

	/**
	 * ctor
	 */
	private PragmatachRoutes() throws PragmatachException {
		readRoutes();
	}

	/**
	 * get all known routes for all HTTP methods
	 */
	public List<PragmatachRoute> getAllRoutes() {
		final List<PragmatachRoute> ret = new ArrayList<PragmatachRoute>();
		ret.addAll(GETRoutes);
		ret.addAll(POSTRoutes);
		return ret;
	}

	/**
	 * get all GET routes
	 */
	public List<PragmatachRoute> getGETRoutes() {
		return GETRoutes;
	}

	/**
	 * get all POST routes
	 */
	public List<PragmatachRoute> getPOSTRoutes() {
		return POSTRoutes;
	}

	/**
	 * read the routes
	 */
	private void readRoutes() throws PragmatachException {
		try {
			/*
			 * get the routes
			 */
			final Set<Method> routerMethods = ControllerClasses.getRouterMethods();
			if (null != routerMethods) {
				for (final Method method : routerMethods) {
					final Route route = method.getAnnotation(Route.class);
					final Route.HttpMethod httpMethod = route.method();
					if (httpMethod == com.khubla.pragmatach.framework.annotation.Route.HttpMethod.get) {
						GETRoutes.add(new PragmatachRoute(method));
					} else {
						POSTRoutes.add(new PragmatachRoute(method));
					}
				}
				/*
				 * sort the routes
				 */
				Collections.sort(GETRoutes);
				Collections.sort(POSTRoutes);
			}
		} catch (final Exception e) {
			throw new PragmatachException("Exception in readRoutes", e);
		}
	}
}
