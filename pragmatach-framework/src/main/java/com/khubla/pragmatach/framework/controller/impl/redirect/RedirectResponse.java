package com.khubla.pragmatach.framework.controller.impl.redirect;

import java.util.*;

import javax.servlet.http.*;

import com.khubla.pragmatach.framework.api.*;
import com.khubla.pragmatach.framework.controller.impl.*;

/**
 * @author tome
 */
public class RedirectResponse extends AbstractResponse {
	/**
	 * uri
	 */
	private final String uri;

	/**
	 * ctor
	 */
	public RedirectResponse(Map<String, String> cacheHeaders, String uri) {
		super(cacheHeaders);
		this.uri = uri;
	}

	@Override
	public String getContentType() throws PragmatachException {
		return null;
	}

	@Override
	public Map<String, String> getHeaders() throws PragmatachException {
		Map<String, String> map = getCacheHeaders();
		if (null == map) {
			map = new HashMap<String, String>();
		}
		map.put("Location", uri);
		return map;
	}

	@Override
	public int getHTTPCode() {
		return HttpServletResponse.SC_MOVED_TEMPORARILY;
	}

	@Override
	public void render(HttpServletResponse httpServletResponse) throws PragmatachException {
		try {
		} catch (final Exception e) {
			throw new PragmatachException("Exception in render", e);
		}
	}
}
