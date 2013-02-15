package com.khubla.pragmatach.framework.filter;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;

/**
 * @author tome
 */
public class ErrorHandlerFilterRequestWrapper extends HttpServletResponseWrapper {
   public ErrorHandlerFilterRequestWrapper(HttpServletResponse httpServletResponse) {
      super(httpServletResponse);
   }

   public void sendError(int errorCode) throws IOException {
      if (errorCode == HttpServletResponse.SC_NOT_FOUND) {
         throw new PageNotFoundException();
      }
      super.sendError(errorCode);
   }

   public void setStatus(int sc) {
      super.setStatus(sc);
   }
}