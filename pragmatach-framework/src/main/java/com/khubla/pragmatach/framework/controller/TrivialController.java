package com.khubla.pragmatach.framework.controller;

import com.khubla.pragmatach.framework.annotation.Controller;
import com.khubla.pragmatach.framework.api.PragmatachException;
import com.khubla.pragmatach.framework.api.Request;
import com.khubla.pragmatach.framework.api.Response;
import com.khubla.pragmatach.framework.api.TrivialResponse;

/**
 * @author tome
 */
@Controller
public abstract class TrivialController extends AbstractController {
   /**
    * message
    */
   private final String message;
   /**
    * code
    */
   private final int httpCode;

   public TrivialController(String message, int httpCode) {
      this.message = message;
      this.httpCode = httpCode;
   }

   @Override
   public Response render(Request request) throws PragmatachException {
      return new TrivialResponse(message, httpCode);
   }
}
