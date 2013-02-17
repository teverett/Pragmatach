package com.khubla.pragmatach.framework.controller.impl;

import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;

import com.khubla.pragmatach.framework.api.PragmatachException;
import com.khubla.pragmatach.framework.controller.AbstractResponse;

/**
 * @author tome
 */
public class StaticResourceResponse extends AbstractResponse {
   /**
    * InputStream
    */
   private final InputStream resourceInputStream;

   /**
    * ctor
    */
   public StaticResourceResponse(InputStream resourceInputStream) {
      this.resourceInputStream = resourceInputStream;
   }

   @Override
   public int getHTTPCode() {
      if (null != resourceInputStream) {
         return HttpServletResponse.SC_OK;
      } else {
         return HttpServletResponse.SC_NOT_FOUND;
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
