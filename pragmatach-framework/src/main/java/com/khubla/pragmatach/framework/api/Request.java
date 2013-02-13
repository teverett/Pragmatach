package com.khubla.pragmatach.framework.api;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author tome
 */
public class Request {
   /**
    * HttpServletRequest
    */
   private final HttpServletRequest httpServletRequest;
   /**
    * HttpServletResponse
    */
   private final HttpServletResponse httpServletResponse;

   public Request(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
      this.httpServletRequest = httpServletRequest;
      this.httpServletResponse = httpServletResponse;
   }

   public HttpServletRequest getHttpServletRequest() {
      return httpServletRequest;
   }

   public HttpServletResponse getHttpServletResponse() {
      return httpServletResponse;
   }
}
