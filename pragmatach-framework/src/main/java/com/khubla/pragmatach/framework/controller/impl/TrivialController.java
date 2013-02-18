package com.khubla.pragmatach.framework.controller.impl;

import com.khubla.pragmatach.framework.annotation.Controller;
import com.khubla.pragmatach.framework.api.PragmatachException;
import com.khubla.pragmatach.framework.api.Response;
import com.khubla.pragmatach.framework.controller.AbstractController;

/**
 * @author tome
 */
@Controller(name = "pragmatachTrivialController")
public class TrivialController extends AbstractController {
   /**
    * message
    */
   private final String message;
   /**
    * code
    */
   private final int httpCode;

   public TrivialController(int httpCode) {
      message = null;
      this.httpCode = httpCode;
   }

   public TrivialController(String message, int httpCode) {
      this.message = message;
      this.httpCode = httpCode;
   }

   public Response render() throws PragmatachException {
      return new TrivialResponse(getCacheHeaders(), message, httpCode);
   }
}
