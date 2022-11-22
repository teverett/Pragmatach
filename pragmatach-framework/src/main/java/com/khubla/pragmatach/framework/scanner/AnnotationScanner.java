package com.khubla.pragmatach.framework.scanner;

import java.lang.annotation.*;
import java.lang.reflect.*;
import java.util.*;

import org.reflections.*;
import org.slf4j.*;

import com.khubla.pragmatach.framework.api.*;

/**
 * @author tome
 */
public class AnnotationScanner {
	/**
	 * logger
	 */
	private static final Logger logger = LoggerFactory.getLogger(AnnotationScanner.class);
	/**
	 * the DB
	 */
	private static Reflections reflections = new Reflections();

	/**
	 * get classes with annotation
	 */
	public static Set<Class<?>> getAllClasses(Class<? extends Annotation> annotationClass) throws PragmatachException {
		try {
			return reflections.getTypesAnnotatedWith(annotationClass);
		} catch (final Exception e) {
			throw new PragmatachException("Exception in getAllClasses", e);
		}
	}

	/**
	 * get classes with annotation
	 */
	public static Set<Method> getAllMethods(Class<? extends Annotation> annotationClass) throws PragmatachException {
		try {
			return reflections.getMethodsAnnotatedWith(annotationClass);
		} catch (final Exception e) {
			throw new PragmatachException("Exception in getAllMethods", e);
		}
	}

	public static Reflections getReflections() {
		return reflections;
	}

	/**
	 * do the scan
	 */
	public static void scan() throws PragmatachException {
		final Reflections classPathReflections = new Reflections(System.getProperty("java.class.path"));
		reflections.merge(classPathReflections);
		final Reflections servletReflections = new Reflections("com.khubla.pragmatach");
		reflections.merge(servletReflections);
		final Reflections servletTestReflections = new Reflections("test.com.khubla.pragmatach");
		reflections.merge(servletTestReflections);
	}
}
