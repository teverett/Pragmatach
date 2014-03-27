package com.khubla.pragmatach.framework.api;

import java.util.Hashtable;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.khubla.pragmatach.framework.application.Application;
import com.khubla.pragmatach.framework.crypto.AES;

/**
 * @author tome
 */
public class Cookies {
	/**
	 * for long term cookies
	 */
	private static final int SECONDS_PER_YEAR = 60 * 60 * 24 * 365;
	/**
	 * HttpServletRequest
	 */
	private final HttpServletRequest httpServletRequest;
	/**
	 * HttpServletResponse
	 */
	private final HttpServletResponse httpServletResponse;
	/**
	 * config key
	 */
	private final static String COOKIE_CONFIG_KEY = "pragmatach.cookie.cryptokey";

	/**
	 * ctor
	 */
	public Cookies(HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {
		this.httpServletRequest = httpServletRequest;
		this.httpServletResponse = httpServletResponse;
	}

	/**
	 * clear all cookies
	 */
	public void clearAll() throws PragmatachException {
		final Cookie[] cookies = httpServletRequest.getCookies();
		if ((null != cookies) && (cookies.length > 0)) {
			for (final Cookie cookie : cookies) {
				removeCookie(cookie.getName());
			}
		}
	}

	/**
	 * decrypt cookie
	 */
	private String decryptCookie(String cookie) throws Exception {
		final String key = getCryptoKey();
		if (null != key) {
			return AES.decrypt(cookie, key);
		} else {
			return cookie;
		}
	}

	/**
	 * encrypt cookie
	 */
	private String encryptCookie(String cookie) throws Exception {
		final String key = getCryptoKey();
		if (null != key) {
			return AES.encrypt(cookie, key);
		} else {
			return cookie;
		}
	}

	/**
	 * get a cookie by name
	 */
	public String getCookie(String name) throws PragmatachException {
		try {
			if ((null != name) && (name.length() > 0)) {
				final Hashtable<String, String> cookies = getCookies();
				if (null != cookies) {
					return cookies.get(name);
				}
			}
			return null;
		} catch (final Exception e) {
			throw new PragmatachException("Exception in getCookie", e);
		}
	}

	/**
	 * get all the cookies
	 */
	public Hashtable<String, String> getCookies() throws PragmatachException {
		try {
			final Cookie[] cookies = httpServletRequest.getCookies();
			if ((null != cookies) && (cookies.length > 0)) {
				final Hashtable<String, String> ret = new Hashtable<String, String>();
				for (final Cookie cookie : cookies) {
					ret.put(cookie.getName(), cookie.getValue());
				}
				return ret;
			}
			return null;
		} catch (final Exception e) {
			throw new PragmatachException("Exception in getCookies", e);
		}
	}

	private String getCryptoKey() throws PragmatachException {
		return Application.getConfiguration().getParameter(COOKIE_CONFIG_KEY);
	}

	/**
	 * get a encrypter cookie by name
	 */
	public String getEncryptedCookie(String name) throws PragmatachException {
		try {
			if ((null != name) && (name.length() > 0)) {
				final Hashtable<String, String> cookies = getCookies();
				if (null != cookies) {
					final String k = cookies.get(name);
					if (null != k) {
						return decryptCookie(k);
					}
				}
			}
			return null;
		} catch (final Exception e) {
			throw new PragmatachException("Exception in getCookie", e);
		}
	}

	/**
	 * remove a cookie
	 */
	public void removeCookie(String name) throws PragmatachException {
		final Cookie[] cookies = httpServletRequest.getCookies();
		if ((null != cookies) && (cookies.length > 0)) {
			for (final Cookie cookie : cookies) {
				if (cookie.getName().compareTo(name) == 0) {
					cookie.setMaxAge(0);
					cookie.setPath("/");
					httpServletResponse.addCookie(cookie);
					break;
				}
			}
		}
	}

	/**
	 * set a cookie
	 */
	public void setCookie(String name, String value) throws PragmatachException {
		try {
			final Cookie cookie = new Cookie(name, value);
			cookie.setMaxAge(SECONDS_PER_YEAR);
			cookie.setPath("/");
			httpServletResponse.addCookie(cookie);
		} catch (final Exception e) {
			throw new PragmatachException("Exception in setCookie", e);
		}
	}

	/**
	 * set a cookie
	 */
	public void setEncryptedCookie(String name, String value)
			throws PragmatachException {
		try {
			final Cookie cookie = new Cookie(name, encryptCookie(value));
			cookie.setMaxAge(SECONDS_PER_YEAR);
			cookie.setPath("/");
			httpServletResponse.addCookie(cookie);
		} catch (final Exception e) {
			throw new PragmatachException("Exception in setCookie", e);
		}
	}
}
