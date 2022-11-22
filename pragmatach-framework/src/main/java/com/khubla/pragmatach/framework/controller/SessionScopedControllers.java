package com.khubla.pragmatach.framework.controller;

import java.util.*;

import javax.servlet.http.*;

import com.khubla.pragmatach.framework.annotation.*;
import com.khubla.pragmatach.framework.api.*;

/**
 * @author tome
 */
public class SessionScopedControllers {
	/***
	 * Session key for session scoped controllers
	 */
	private static final String SESSION_CONTROLLERS = "Session-Scoped-Controllers";

	/**
	 * get controller with clazz
	 */
	public static PragmatachController getController(HttpSession httpSession, Class<?> clazz) {
		final Hashtable<Class<?>, PragmatachController> map = getMap(httpSession);
		return map.get(clazz);
	}

	/**
	 * get the map
	 */
	@SuppressWarnings("unchecked")
	public static Hashtable<Class<?>, PragmatachController> getMap(HttpSession httpSession) {
		Hashtable<Class<?>, PragmatachController> controllers = (Hashtable<Class<?>, PragmatachController>) httpSession.getAttribute(SESSION_CONTROLLERS);
		if (null == controllers) {
			controllers = new Hashtable<Class<?>, PragmatachController>();
			httpSession.setAttribute(SESSION_CONTROLLERS, controllers);
		}
		return controllers;
	}

	/**
	 * create instances of all session controllers and make sure they're in the map
	 */
	public static void populateSessionControllers(HttpSession httpSession) throws PragmatachException {
		try {
			final Set<Class<?>> controllers = Controllers.getInstance().getControllers();
			if (null != controllers) {
				for (final Class<?> clazz : controllers) {
					final Controller controller = clazz.getAnnotation(Controller.class);
					if (controller.scope() == Controller.Scope.session) {
						final PragmatachController pragmatachController = Controllers.getInstance(clazz);
						SessionScopedControllers.setController(httpSession, pragmatachController);
					}
				}
			}
		} catch (final Exception e) {
			throw new PragmatachException("Exception in populateSessionControllers", e);
		}
	}

	/**
	 * set controller
	 */
	public static void setController(HttpSession httpSession, PragmatachController pragmatachController) {
		final Hashtable<Class<?>, PragmatachController> map = getMap(httpSession);
		map.put(pragmatachController.getClass(), pragmatachController);
	}
}
