package com.khubla.pragmatach.plugin.jcr;

import java.io.ByteArrayInputStream;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;

import com.khubla.pragmatach.framework.api.PragmatachException;
import com.khubla.pragmatach.framework.controller.impl.AbstractResponse;

/**
 * @author tome
 */
public class JCRResponse extends AbstractResponse {
   /**
    * JSON
    */
   private final String json;

   public JCRResponse(Map<String, String> cacheHeaders, String json) {
      super(cacheHeaders);
      this.json = json;
   }

   @Override
   public String getContentType() throws PragmatachException {
      return "application/json";
   }

   @Override
   public Map<String, String> getHeaders() throws PragmatachException {
      return super.getCacheHeaders();
   }

   @Override
   public void render(HttpServletResponse httpServletResponse) throws PragmatachException {
      try {
         final ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(json.getBytes());
         IOUtils.copy(byteArrayInputStream, httpServletResponse.getOutputStream());
      } catch (final Exception e) {
         throw new PragmatachException("Exception in render", e);
      }
   }
}
