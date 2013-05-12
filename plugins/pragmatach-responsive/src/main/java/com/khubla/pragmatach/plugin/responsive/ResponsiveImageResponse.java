package com.khubla.pragmatach.plugin.responsive;

import java.io.InputStream;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import com.khubla.pragmatach.framework.api.PragmatachException;
import com.khubla.pragmatach.framework.controller.impl.AbstractResponse;

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
    * xscale
    */
   private final double xscale;
   /**
    * yscale
    */
   private final double yscale;
   /**
    * height
    */
   private final int height;

   public ResponsiveImageResponse(Map<String, String> cacheHeaders, InputStream imageResource, double xscale, double yscale) {
      super(cacheHeaders);
      this.imageResource = imageResource;
      width = 0;
      height = 0;
      this.xscale = xscale;
      this.yscale = yscale;
   }

   public ResponsiveImageResponse(Map<String, String> cacheHeaders, InputStream imageResource, int width, int height) {
      super(cacheHeaders);
      this.imageResource = imageResource;
      this.width = width;
      this.height = height;
      xscale = 0;
      yscale = 0;
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
   public void render(HttpServletResponse httpServletResponse) throws PragmatachException {
      try {
         if (0 != width) {
            ImageResizer.resize(imageResource, httpServletResponse.getOutputStream(), width, height);
         } else {
            ImageResizer.resize(imageResource, httpServletResponse.getOutputStream(), xscale, yscale);
         }
      } catch (final Exception e) {
         throw new PragmatachException("Exception in render", e);
      }
   }
}
