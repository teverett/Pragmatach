package com.khubla.pragmatach.framework.configuration;

import java.io.*;
import java.util.*;

import com.khubla.pragmatach.framework.api.*;

/**
 * @author tome
 */
public class PropertiesFileConfigurationImpl extends BaseConfiguration {
	/**
	 * file
	 */
	private static final String CONFIGURATION_FILE = "/pragmatach.properties";
	/**
	 * properties
	 */
	private static Properties properties;

	@Override
	public Map<String, String> getAll() throws PragmatachException {
		readProperties();
		final Map<String, String> ret = new HashMap<String, String>();
		final Enumeration<Object> enumer = properties.keys();
		while (enumer.hasMoreElements()) {
			final String k = (String) enumer.nextElement();
			ret.put(k, properties.getProperty(k));
		}
		return ret;
	}

	@Override
	public Object getObject(String name) throws PragmatachException {
		return resolveObject(properties.getProperty(name));
	}

	@Override
	public String getParameter(String name) throws PragmatachException {
		readProperties();
		return resolveString(properties.getProperty(name));
	}

	/**
	 * read properties
	 */
	private void readProperties() {
		try {
			if (null == properties) {
				properties = new Properties();
				InputStream is = null;
				try {
					is = PropertiesFileConfigurationImpl.class.getResourceAsStream(CONFIGURATION_FILE);
					if (null != is) {
						properties.load(is);
					}
				} finally {
					if (null != is) {
						is.close();
					}
				}
			}
		} catch (final Exception e) {
			e.printStackTrace();
		}
	}
}
