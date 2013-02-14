package com.khubla.pragmatach.framework.controller;

import com.khubla.pragmatach.framework.api.Request;

/**
 * @author tome
 */
public abstract class AbstractController implements PragmatachController {
   /**
    * request
    */
   private static Request request;
   public static int HTTP_OK = 200;
   public static int HTTP_NOTFOUND = 404;

   public static Request getRequest() {
      return request;
   }

   public static void setRequest(Request request) {
      AbstractController.request = request;
   }

   /**
    */
   public AbstractController(Request request) {
      AbstractController.request = request;
   }
}
