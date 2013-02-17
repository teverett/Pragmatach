package com.khubla.pragmatach.framework.controller.impl;

import java.io.OutputStream;
import java.io.PrintWriter;

import com.khubla.pragmatach.framework.api.PragmatachException;
import com.khubla.pragmatach.framework.controller.AbstractResponse;

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
   public TrivialResponse(int httpCode) {
      response = null;
      this.httpCode = httpCode;
   }

   /**
    * ctor
    */
   public TrivialResponse(String response, int httpCode) {
      this.response = response;
      this.httpCode = httpCode;
   }

   @Override
   public int getHTTPCode() {
      return httpCode;
   }

   @Override
   public void render(OutputStream outputStream) throws PragmatachException {
      if (null != response) {
         final PrintWriter printWriter = new PrintWriter(outputStream);
         printWriter.write(response);
         printWriter.flush();
         printWriter.close();
      }
   }
}
