package com.khubla.pragmatach.plugin.jasper;

import java.io.OutputStream;
import java.util.Map;

import com.khubla.pragmatach.framework.api.PragmatachException;
import com.khubla.pragmatach.framework.controller.impl.AbstractResponse;

/**
 * @author tome
 */
public class JasperResponse extends AbstractResponse {
   public JasperResponse(Map<String, String> cacheHeaders, String templateName, String template, Map<String, Object> context) {
      super(cacheHeaders);
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
      } catch (final Exception e) {
         throw new PragmatachException("Exception in render", e);
      }
   }
}
