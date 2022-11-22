package com.khubla.pragmatach.framework.filter;

import java.io.*;

import javax.servlet.*;
import javax.servlet.http.*;

import org.apache.commons.codec.*;
import org.apache.commons.codec.binary.*;
import org.apache.commons.lang.StringUtils;

import com.khubla.pragmatach.framework.api.*;
import com.khubla.pragmatach.framework.application.*;

/**
 * @author tome
 */
public class BasicAuthenticationFilter implements Filter {
	/**
	 * config values
	 */
	private final static String APPLICATIONUSER = "pragmatach.applicationuser";
	private final static String APPLICATIONPASSWORD = "pragmatach.applicationpassword";
	private final static String APPLICATIONREALM = "pragmatach.applicationrealm";
	/**
	 * realm
	 */
	private String realm = null;

	/**
	 * authenticate
	 */
	private boolean authenticate(String username, String password) throws PragmatachException {
		try {
			final String applicationUserId = Application.getConfiguration().getParameter(APPLICATIONUSER);
			final String applicationPassword = Application.getConfiguration().getParameter(APPLICATIONPASSWORD);
			if (null != applicationUserId) {
				if ((null != username) && (null != password) && (null != applicationPassword)) {
					if ((username.compareTo(applicationUserId) == 0) && (password.compareTo(applicationPassword) == 0)) {
						return true;
					} else {
						/*
						 * mismatch
						 */
						return false;
					}
				} else {
					/*
					 * nulls, no good
					 */
					return false;
				}
			} else {
				/*
				 * no userid; auth always works
				 */
				return true;
			}
		} catch (final Exception e) {
			throw new PragmatachException("Exception in authenticate", e);
		}
	}

	@Override
	public void destroy() {
	}

	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
		try {
			/*
			 * upcast
			 */
			final HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;
			final HttpServletResponse httpResponse = (HttpServletResponse) servletResponse;
			/*
			 * there is an application userid?
			 */
			if (null != Application.getConfiguration().getParameter(APPLICATIONUSER)) {
				/*
				 * get the header
				 */
				final String auth = httpRequest.getHeader("Authorization");
				if (auth != null) {
					final int index = auth.indexOf(' ');
					if (index > 0) {
						final String[] credentials = StringUtils.split(new String(Base64.decodeBase64(auth.substring(index)), Charsets.UTF_8), ':');
						if (authenticate(credentials[0], credentials[1])) {
							/*
							 * keep going
							 */
							filterChain.doFilter(httpRequest, httpResponse);
							return;
						}
					}
				}
				httpResponse.setHeader("WWW-Authenticate", "Basic realm=\"" + realm + "\"");
				httpResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED);
			} else {
				filterChain.doFilter(httpRequest, httpResponse);
				return;
			}
		} catch (final Exception e) {
			throw new ServletException("Exception in doFilter", e);
		}
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		try {
			realm = Application.getConfiguration().getParameter(APPLICATIONREALM);
			if (realm == null) {
				realm = "pragmatach";
			}
		} catch (final Exception e) {
			throw new ServletException("Exception in init", e);
		}
	}
}
