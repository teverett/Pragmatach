package com.khubla.pragmatach.framework.api;

import java.util.*;

import javax.servlet.http.*;

/**
 * @author tome
 */
public interface Response {
	String getContentType() throws PragmatachException;

	Map<String, String> getHeaders() throws PragmatachException;

	int getHTTPCode();

	void render(HttpServletResponse httpServletResponse) throws PragmatachException;
}
