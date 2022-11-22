package com.khubla.pragmatach.framework.controller.impl;

import java.util.*;

import javax.servlet.http.*;

import com.khubla.pragmatach.framework.api.*;

/**
 * @author tome
 */
public abstract class AbstractResponse implements Response {
	/**
	 * content type
	 */
	protected final static String CONTENT_TYPE_HTML = "text/html";
	/**
	 * the cache headers
	 */
	private final Map<String, String> cacheHeaders;

	/**
	 * ctor
	 */
	public AbstractResponse(Map<String, String> cacheHeaders) {
		this.cacheHeaders = cacheHeaders;
	}

	public Map<String, String> getCacheHeaders() {
		return cacheHeaders;
	}

	@Override
	public int getHTTPCode() {
		return HttpServletResponse.SC_OK;
	}
}
