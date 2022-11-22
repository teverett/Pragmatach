package com.khubla.pragmatach.framework.controller.impl.stat;

import java.io.*;
import java.util.*;

import javax.servlet.http.*;

import org.apache.commons.io.*;

import com.khubla.pragmatach.framework.api.*;
import com.khubla.pragmatach.framework.controller.impl.*;

/**
 * @author tome
 */
public class StaticResourceResponse extends AbstractResponse {
	/**
	 * InputStream
	 */
	private final InputStream resourceInputStream;

	/**
	 * ctor
	 */
	public StaticResourceResponse(Map<String, String> cacheHeaders, InputStream resourceInputStream) {
		super(cacheHeaders);
		this.resourceInputStream = resourceInputStream;
	}

	@Override
	public String getContentType() throws PragmatachException {
		/*
		 * dunno what content type
		 */
		return null;
	}

	@Override
	public Map<String, String> getHeaders() throws PragmatachException {
		return getCacheHeaders();
	}

	@Override
	public int getHTTPCode() {
		if (null != resourceInputStream) {
			return HttpServletResponse.SC_OK;
		} else {
			return HttpServletResponse.SC_NOT_FOUND;
		}
	}

	@Override
	public void render(HttpServletResponse httpServletResponse) throws PragmatachException {
		try {
			if (null != resourceInputStream) {
				IOUtils.copy(resourceInputStream, httpServletResponse.getOutputStream());
			}
		} catch (final Exception e) {
			throw new PragmatachException("Exception in render", e);
		}
	}
}
