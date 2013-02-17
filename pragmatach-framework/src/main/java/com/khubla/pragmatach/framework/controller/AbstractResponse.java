package com.khubla.pragmatach.framework.controller;

import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import com.khubla.pragmatach.framework.api.PragmatachException;
import com.khubla.pragmatach.framework.api.Response;

/**
 * @author tome
 */
public abstract class AbstractResponse implements Response {
   @Override
   public Map<String, String> getHeaders() throws PragmatachException {
      return null;
   }

   @Override
   public int getHTTPCode() {
      return HttpServletResponse.SC_OK;
   }
}
