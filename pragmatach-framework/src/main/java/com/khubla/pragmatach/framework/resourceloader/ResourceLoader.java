package com.khubla.pragmatach.framework.resourceloader;

import java.io.*;

import com.khubla.pragmatach.framework.api.*;

public interface ResourceLoader {
	/**
	 * get a resource using the servlet's class loader
	 */
	InputStream getResource(String resource) throws PragmatachException;
}