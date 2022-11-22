package com.khubla.pragmatach.framework.controller;

import java.lang.reflect.*;
import java.util.*;

import org.slf4j.*;

import com.khubla.pragmatach.framework.annotation.*;
import com.khubla.pragmatach.framework.api.*;
import com.khubla.pragmatach.framework.scanner.*;

/**
 * @author tome
 */
public class ControllerClasses {
	/**
	 * logger
	 */
	private static Logger logger = LoggerFactory.getLogger(ControllerClasses.class);
	/**
	 * routes
	 */
	private static Set<Method> routerMethods = new HashSet<Method>();
	/**
	 * controllers
	 */
	private static Set<Class<?>> controllers = new HashSet<Class<?>>();

	/**
	 * do the annotations scan
	 */
	public static void buildDB() throws PragmatachException {
		try {
			/*
			 * controllers
			 */
			final Set<Class<?>> annotatedControllers = AnnotationScanner.getAllClasses(Controller.class);
			for (final Class<?> clazz : annotatedControllers) {
				controllers.add(clazz);
				logger.info("Found controller '" + clazz.getName() + "'");
			}
			/*
			 * find routes
			 */
			for (final Class<?> clazz : annotatedControllers) {
				final Method[] methods = clazz.getMethods();
				if (null != methods) {
					for (final Method method : methods) {
						if (method.isAnnotationPresent(Route.class)) {
							if (method.isAnnotationPresent(Route.class)) {
								routerMethods.add(method);
								logger.info(
										"Found router method '" + method.getDeclaringClass().getName() + ":" + method.getName() + "' with route specification '" + method.getAnnotation(Route.class).uri() + "'");
							}
						}
					}
				}
			}
		} catch (final Exception e) {
			throw new PragmatachException("Exception in processAnnotations", e);
		}
	}

	public static Set<Class<?>> getControllers() {
		return controllers;
	}

	public static Set<Method> getRouterMethods() {
		return routerMethods;
	}

	public static void setControllers(Set<Class<?>> controllers) {
		ControllerClasses.controllers = controllers;
	}

	public static void setRouterMethods(Set<Method> routerMethods) {
		ControllerClasses.routerMethods = routerMethods;
	}
}
