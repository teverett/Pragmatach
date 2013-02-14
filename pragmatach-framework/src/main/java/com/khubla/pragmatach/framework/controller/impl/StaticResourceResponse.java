package com.khubla.pragmatach.framework.controller.impl;

import java.io.InputStream;
import java.io.OutputStream;

import org.apache.commons.io.IOUtils;

import com.khubla.pragmatach.framework.api.PragmatachException;
import com.khubla.pragmatach.framework.api.Response;

/**
 * @author tome
 */
public class StaticResourceResponse implements Response {
   /**
    * InputStream
    */
   private InputStream resourceInputStream;

   /**
    * ctor
    */
   public StaticResourceResponse(String uri) {
      resourceInputStream = getClass().getResourceAsStream("/" + uri);
   }

   @Override
   public int getHTTPCode() {
      if (null != resourceInputStream) {
         return 200;
      } else {
         return 404;
      }
   }

   @Override
   public void render(OutputStream outputStream) throws PragmatachException {
      try {
         if (null != resourceInputStream) {
            IOUtils.copy(resourceInputStream, outputStream);
         }
         outputStream.flush();
         outputStream.close();
      } catch (Exception e) {
         throw new PragmatachException("Exception in render", e);
      }
   }
}
