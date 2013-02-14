package com.khubla.pragmatach.framework.controller;

import com.khubla.pragmatach.framework.api.Request;

/**
 * @author tome
 */
public abstract class AbstractController implements PragmatachController {
   public static Request getRequest() {
      return request;
   }

   public static void setRequest(Request request) {
      AbstractController.request = request;
   }

   /**
    * request
    */
   private static Request request;

   /**
    */
   public AbstractController(Request request) {
      this.request = request;
   }
}
