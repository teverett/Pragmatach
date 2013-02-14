package com.khubla.pragmatach.framework.controller;

import com.khubla.pragmatach.framework.api.Request;

/**
 * @author tome
 */
public abstract class AbstractController implements PragmatachController {
   /**
    * request
    */
   private Request request;
   /**
    * static values
    */
   public static int HTTP_OK = 200;
   public static int HTTP_NOTFOUND = 404;

   public Request getRequest() {
      return request;
   }

   public void setRequest(Request request) {
      this.request = request;
   }
}
