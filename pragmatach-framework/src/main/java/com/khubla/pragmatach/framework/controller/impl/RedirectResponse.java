package com.khubla.pragmatach.framework.controller.impl;

import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

import com.khubla.pragmatach.framework.api.PragmatachException;
import com.khubla.pragmatach.framework.api.Response;
import com.khubla.pragmatach.framework.controller.AbstractController;

/**
 * @author tome
 */
public class RedirectResponse implements Response {
   /**
    * uri
    */
   private final String uri;

   /**
    * ctor
    */
   public RedirectResponse(String uri) {
      this.uri = uri;
   }

   @Override
   public Map<String, String> getHeaders() throws PragmatachException {
      final Map<String, String> map = new HashMap<String, String>();
      map.put("Location", uri);
      return map;
   }

   @Override
   public int getHTTPCode() {
      return AbstractController.HTTP_REDIRECT;
   }

   @Override
   public void render(OutputStream outputStream) throws PragmatachException {
      try {
      } catch (final Exception e) {
         throw new PragmatachException("Exception in render", e);
      }
   }
}
