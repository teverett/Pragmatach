package com.khubla.pragmatach.framework.controller.impl.trivial;

import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import com.khubla.pragmatach.framework.api.PragmatachException;
import com.khubla.pragmatach.framework.controller.impl.AbstractResponse;

/**
 * @author tome
 */
public class TrivialResponse extends AbstractResponse {
   /**
    * http code
    */
   private final int httpCode;
   /**
    * response
    */
   private final String response;

   /**
    * ctor
    */
   public TrivialResponse(Map<String, String> cacheHeaders, int httpCode) {
      super(cacheHeaders);
      response = null;
      this.httpCode = httpCode;
   }

   /**
    * ctor
    */
   public TrivialResponse(Map<String, String> cacheHeaders, String response, int httpCode) {
      super(cacheHeaders);
      this.response = response;
      this.httpCode = httpCode;
   }

   @Override
   public String getContentType() throws PragmatachException {
      // dunno
      return null;
   }

   @Override
   public Map<String, String> getHeaders() throws PragmatachException {
      return getCacheHeaders();
   }

   @Override
   public int getHTTPCode() {
      return httpCode;
   }

   @Override
   public void render(HttpServletResponse httpServletResponse) throws PragmatachException {
      try {
         if (null != response) {
            final PrintWriter printWriter = new PrintWriter(httpServletResponse.getOutputStream());
            printWriter.write(response);
            printWriter.flush();
            printWriter.close();
         }
      } catch (final Exception e) {
         throw new PragmatachException("Exception in render", e);
      }
   }
}
