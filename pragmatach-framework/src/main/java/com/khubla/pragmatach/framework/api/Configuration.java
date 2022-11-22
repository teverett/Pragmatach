package com.khubla.pragmatach.framework.api;

import java.util.*;

/**
 * @author tome
 */
public interface Configuration {
	/**
	 * all
	 */
	Map<String, String> getAll() throws PragmatachException;

	/**
	 * get an object by name
	 */
	Object getObject(String name) throws PragmatachException;

	/**
	 * get a parameter by name
	 */
	String getParameter(String name) throws PragmatachException;
}
