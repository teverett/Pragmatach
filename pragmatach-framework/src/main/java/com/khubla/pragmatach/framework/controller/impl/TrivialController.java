package com.khubla.pragmatach.framework.controller.impl;

import com.khubla.pragmatach.framework.annotation.Controller;
import com.khubla.pragmatach.framework.api.PragmatachException;
import com.khubla.pragmatach.framework.api.Request;
import com.khubla.pragmatach.framework.api.Response;
import com.khubla.pragmatach.framework.controller.AbstractController;

/**
 * @author tome
 */
@Controller
public class TrivialController extends AbstractController {
   /**
    * message
    */
   private final String message;
   /**
    * code
    */
   private final int httpCode;

   public TrivialController(Request request, String message, int httpCode) {
      super(request);
      this.message = message;
      this.httpCode = httpCode;
   }

   public Response render() throws PragmatachException {
      return new TrivialResponse(message, httpCode);
   }
}
