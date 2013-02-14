package com.khubla.pragmatach.framework.controller.impl;

import java.io.OutputStream;

import com.khubla.pragmatach.framework.api.PragmatachException;
import com.khubla.pragmatach.framework.api.Response;

/**
 * @author tome
 */
public class StaticResourceResponse implements Response {
   /**
    * ctor
    */
   public StaticResourceResponse(String filename) {
   }

   @Override
   public int getHTTPCode() {
      return 200;
   }

   @Override
   public void render(OutputStream outputStream) throws PragmatachException {
   }
}
