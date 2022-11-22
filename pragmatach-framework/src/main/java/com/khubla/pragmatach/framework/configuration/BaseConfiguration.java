package com.khubla.pragmatach.framework.configuration;

import javax.naming.*;

import com.khubla.pragmatach.framework.api.*;

/**
 * @author tome
 */
public abstract class BaseConfiguration implements Configuration {
	/**
	 * jndi strings start with this
	 */
	private final static String JAVA = "java:";

	/**
	 * resolve an object
	 */
	public static Object resolveObject(String value) throws PragmatachException {
		try {
			if (null != value) {
				if (value.startsWith(JAVA)) {
					final InitialContext initialContext = new InitialContext();
					return initialContext.lookup(value);
				} else {
					return value;
				}
			} else {
				return null;
			}
		} catch (final Exception e) {
			throw new PragmatachException("Exception in resolveObject: '" + value + "'", e);
		}
	}

	/**
	 * resolve a string
	 */
	public static String resolveString(String value) throws PragmatachException {
		try {
			if (null != value) {
				if (value.startsWith(JAVA)) {
					final InitialContext initialContext = new InitialContext();
					return (String) initialContext.lookup(value);
				} else {
					return value;
				}
			} else {
				return null;
			}
		} catch (final Exception e) {
			throw new PragmatachException("Exception in resolveString: '" + value + "'", e);
		}
	}
}
