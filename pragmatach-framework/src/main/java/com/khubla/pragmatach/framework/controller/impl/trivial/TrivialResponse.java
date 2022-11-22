package com.khubla.pragmatach.framework.controller.impl.trivial;

import java.io.*;
import java.util.*;

import javax.servlet.http.*;

import com.khubla.pragmatach.framework.api.*;
import com.khubla.pragmatach.framework.controller.impl.*;

/**
 * @author tome
 */
public class TrivialResponse extends AbstractResponse {
	/**
	 * http code
	 */
	private final int httpCode;
	/**
	 * response
	 */
	private final String response;
	/**
	 * content type
	 */
	private final String contentType;

	/**
	 * ctor
	 */
	public TrivialResponse(Map<String, String> cacheHeaders, int httpCode) {
		super(cacheHeaders);
		response = null;
		this.httpCode = httpCode;
		contentType = null;
	}

	/**
	 * ctor
	 */
	public TrivialResponse(Map<String, String> cacheHeaders, String response, int httpCode, String contentType) {
		super(cacheHeaders);
		this.response = response;
		this.httpCode = httpCode;
		this.contentType = contentType;
	}

	@Override
	public String getContentType() throws PragmatachException {
		return contentType;
	}

	@Override
	public Map<String, String> getHeaders() throws PragmatachException {
		return getCacheHeaders();
	}

	@Override
	public int getHTTPCode() {
		return httpCode;
	}

	@Override
	public void render(HttpServletResponse httpServletResponse) throws PragmatachException {
		try {
			if (null != response) {
				final PrintWriter printWriter = new PrintWriter(httpServletResponse.getOutputStream());
				printWriter.write(response);
				printWriter.flush();
				printWriter.close();
			}
		} catch (final Exception e) {
			throw new PragmatachException("Exception in render", e);
		}
	}
}
