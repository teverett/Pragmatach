package com.khubla.pragmatach.framework.api;

import java.io.*;
import java.util.*;

import javax.servlet.*;
import javax.servlet.http.*;

import org.apache.commons.io.*;

import com.khubla.pragmatach.framework.annotation.*;

/**
 * @author tome
 */
public class Request {
	/**
	 * HttpServletRequest
	 */
	private final HttpServletRequest httpServletRequest;
	/**
	 * method
	 */
	private final Route.HttpMethod method;
	/**
	 * headers
	 */
	private final Headers headers;
	/**
	 * cookies
	 */
	private final Cookies cookies;
	/**
	 * creation time
	 */
	private final long creationTime = System.currentTimeMillis();
	/**
	 * servlet config
	 */
	private final ServletConfig servletConfig;

	/**
	 * ctor
	 */
	public Request(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Route.HttpMethod method, ServletConfig servletConfig) {
		this.httpServletRequest = httpServletRequest;
		this.servletConfig = servletConfig;
		this.method = method;
		headers = new Headers(httpServletRequest, httpServletResponse);
		cookies = new Cookies(httpServletRequest, httpServletResponse);
	}

	public Cookies getCookies() {
		return cookies;
	}

	public long getCreationTime() {
		return creationTime;
	}

	public Headers getHeaders() {
		return headers;
	}

	public HttpServletRequest getHttpServletRequest() {
		return httpServletRequest;
	}

	public InputStream getInputStream() throws PragmatachException {
		try {
			return httpServletRequest.getInputStream();
		} catch (final Exception e) {
			throw new PragmatachException("Exception in getInputStream", e);
		}
	}

	public Route.HttpMethod getMethod() {
		return method;
	}

	/**
	 * parameters
	 */
	public Map<String, String[]> getParameters() {
		return httpServletRequest.getParameterMap();
	}

	/**
	 * get the HTTP POST body
	 */
	public String getPostBody() throws PragmatachException {
		try {
			final ByteArrayOutputStream baos = new ByteArrayOutputStream();
			IOUtils.copy(getInputStream(), baos);
			return baos.toString("UTF-8");
		} catch (final Exception e) {
			throw new PragmatachException("Exception in getRequestBody", e);
		}
	}

	/**
	 * get the resource path, taking off the servlet context path
	 */
	public String getResourcePath() throws PragmatachException {
		try {
			final String uri = getURI();
			String ret = uri.substring(getHttpServletRequest().getContextPath().length());
			if (ret.endsWith("/")) {
				ret = ret.substring(0, ret.length() - 1);
			}
			if ((null == ret) || (ret.length() == 0)) {
				ret = "/";
			}
			return ret;
		} catch (final Exception e) {
			throw new PragmatachException("Exception in resourcePath", e);
		}
	}

	public ServletConfig getServletConfig() {
		return servletConfig;
	}

	/**
	 * servlet context
	 */
	public ServletContext getServletContext() {
		return httpServletRequest.getSession().getServletContext();
	}

	/**
	 * session
	 */
	public HttpSession getSession() {
		return httpServletRequest.getSession();
	}

	/**
	 * URI
	 */
	public String getURI() {
		return httpServletRequest.getRequestURI();
	}
}
