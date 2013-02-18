package com.khubla.pragmatach.framework.controller.impl;

import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import com.khubla.pragmatach.framework.api.PragmatachException;
import com.khubla.pragmatach.framework.controller.AbstractResponse;

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
   public void render(OutputStream outputStream) throws PragmatachException {
      try {
      } catch (final Exception e) {
         throw new PragmatachException("Exception in render", e);
      }
   }
}
