package com.khubla.pragmatach.framework.configuration;

import java.util.*;

import com.khubla.pragmatach.framework.api.*;

/**
 * a configuration class mainly useful for testing
 *
 * @author tome
 */
public class HashmapConfigurationImpl extends BaseConfiguration {
	/**
	 * the parameters
	 */
	private final Map<String, String> map = new HashMap<String, String>();

	/**
	 * ctor
	 */
	public HashmapConfigurationImpl() {
	}

	public HashmapConfigurationImpl(String[][] parameters) {
		for (final String[] set : parameters) {
			map.put(set[0], set[1]);
		}
	}

	@Override
	public Map<String, String> getAll() throws PragmatachException {
		return map;
	}

	@Override
	public Object getObject(String name) throws PragmatachException {
		return resolveObject(map.get(name));
	}

	@Override
	public String getParameter(String name) throws PragmatachException {
		return resolveString(map.get(name));
	}

	public void setParameter(String name, String value) {
		map.put(name, value);
	}
}
