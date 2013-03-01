package com.khubla.pragmatach.plugin.responsive;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.Map;

import com.khubla.pragmatach.framework.api.PragmatachException;
import com.khubla.pragmatach.framework.controller.AbstractResponse;

/**
 * @author tome
 */
public class ResponsiveImageResponse extends AbstractResponse {
   /**
    * imageResource
    */
   private final InputStream imageResource;
   /**
    * width
    */
   private final int width;
   /**
    * height
    */
   private final int height;

   public ResponsiveImageResponse(Map<String, String> cacheHeaders, InputStream imageResource, int width, int height) {
      super(cacheHeaders);
      this.imageResource = imageResource;
      this.width = width;
      this.height = height;
   }

   @Override
   public String getContentType() throws PragmatachException {
      return CONTENT_TYPE_HTML;
   }

   @Override
   public Map<String, String> getHeaders() throws PragmatachException {
      return super.getCacheHeaders();
   }

   @Override
   public void render(OutputStream outputStream) throws PragmatachException {
      try {
         ImageResizer.resize(imageResource, outputStream, width, height);
      } catch (final Exception e) {
         throw new PragmatachException("Exception in render", e);
      }
   }
}
