package com.khubla.pragmatach.framework.scanner;

import java.lang.annotation.*;
import java.lang.reflect.*;
import java.util.*;

import javax.servlet.*;

import org.reflections.*;
import org.slf4j.*;

import com.khubla.pragmatach.framework.api.*;

/**
 * @author tome
 */
public class AnnotationScanner {
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
	public static void scan(ServletContext servletContext) throws PragmatachException {
		scanClassPath();
		scanWar(servletContext);
	}

	private static void scanClassPath() throws PragmatachException {
		try {
			Reflections classPathReflections = new Reflections(System.getProperty("java.class.path"));
			reflections.merge(classPathReflections);
		} catch (final Exception e) {
			throw new PragmatachException("Exception in scanClassPath", e);
		}
	}

	private static void scanWar(ServletContext servletContext) throws PragmatachException {
		try {
			if (null != servletContext) {
				// final URL[] libURLs = WarUrlFinder.findWebInfLibClasspaths(servletContext);
				// final URL classesURL = WarUrlFinder.findWebInfClassesPath(servletContext);
				// if ((null != libURLs) && (libURLs.length > 0)) {
				// annotationDB.scanArchives(libURLs);
				// } else {
				// logger.error("Unable to get WebInfLibClasspaths");
				// }
				// if (null != classesURL) {
				// annotationDB.scanArchives(classesURL);
				// } else {
				// logger.error("Unable to get WebInfClassesPath");
				// }
			}
		} catch (final Exception e) {
			throw new PragmatachException("Exception in scanWar", e);
		}
	}

	/**
	 * logger
	 */
	private static final Logger logger = LoggerFactory.getLogger(AnnotationScanner.class);
	/**
	 * the DB
	 */
	private static Reflections reflections = new Reflections();
}
