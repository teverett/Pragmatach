package com.khubla.pragmatach.framework.api;

import java.util.Map;

import javax.servlet.http.HttpServletResponse;

/**
 * @author tome
 */
public interface Response {
   String getContentType() throws PragmatachException;

   Map<String, String> getHeaders() throws PragmatachException;

   int getHTTPCode();

   void render(HttpServletResponse httpServletResponse) throws PragmatachException;
}
