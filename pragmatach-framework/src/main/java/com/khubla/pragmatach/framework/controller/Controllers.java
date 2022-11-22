package com.khubla.pragmatach.framework.controller;

import java.util.*;

import com.khubla.pragmatach.framework.api.*;

/**
 * @author tome
 */
public class Controllers {
	/**
	 * instance
	 */
	private static Controllers instance;

	/**
	 * singleton
	 */
	public static Controllers getInstance() {
		if (null == instance) {
			instance = new Controllers();
		}
		return instance;
	}

	/**
	 * get a PragmatachController instance
	 */
	public static PragmatachController getInstance(Class<?> clazz) throws PragmatachException {
		try {
			return (PragmatachController) clazz.newInstance();
		} catch (final Exception e) {
			throw new PragmatachException("Exception in getInstance", e);
		}
	}

	private final Set<Class<?>> controllers;

	/**
	 * ctor
	 */
	private Controllers() {
		controllers = ControllerClasses.getControllers();
	}

	public Set<Class<?>> getControllers() {
		return controllers;
	}
}
