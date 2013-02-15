package com.khubla.pragmatach.framework.controller.impl;

import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Map;

import com.khubla.pragmatach.framework.api.PragmatachException;
import com.khubla.pragmatach.framework.api.Response;

/**
 * @author tome
 */
public class TrivialResponse implements Response {
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
   public TrivialResponse(String response, int httpCode) {
      this.response = response;
      this.httpCode = httpCode;
   }

   @Override
   public Map<String, String> getHeaders() throws PragmatachException {
      return null;
   }

   @Override
   public int getHTTPCode() {
      return httpCode;
   }

   @Override
   public void render(OutputStream outputStream) throws PragmatachException {
      final PrintWriter printWriter = new PrintWriter(outputStream);
      printWriter.write(response);
      printWriter.flush();
      printWriter.close();
   }
}
