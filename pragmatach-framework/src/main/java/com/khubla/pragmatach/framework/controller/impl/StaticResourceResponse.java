package com.khubla.pragmatach.framework.controller.impl;

import java.io.InputStream;
import java.io.OutputStream;

import org.apache.commons.io.IOUtils;

import com.khubla.pragmatach.framework.api.PragmatachException;
import com.khubla.pragmatach.framework.api.Response;
import com.khubla.pragmatach.framework.controller.AbstractController;

/**
 * @author tome
 */
public class StaticResourceResponse implements Response {
   /**
    * InputStream
    */
   private final InputStream resourceInputStream;

   /**
    * ctor
    */
   public StaticResourceResponse(String uri) {
      if (null != uri) {
         /*
          * some people are just jerks
          */
         if ((uri.contains("WEB-INF")) || (uri.contains("META-INF")) || (uri.startsWith("."))) {
            resourceInputStream = null;
         } else {
            /*
             * ok, go for it
             */
            resourceInputStream = getClass().getResourceAsStream("/" + uri);
         }
      } else {
         resourceInputStream = null;
      }
   }

   @Override
   public int getHTTPCode() {
      if (null != resourceInputStream) {
         return AbstractController.HTTP_OK;
      } else {
         return AbstractController.HTTP_NOTFOUND;
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
      } catch (final Exception e) {
         throw new PragmatachException("Exception in render", e);
      }
   }
}
