package com.khubla.pragmatach.framework.controller.impl.system;

import java.util.HashMap;
import java.util.Map;

import com.khubla.pragmatach.framework.annotation.Controller;
import com.khubla.pragmatach.framework.api.PragmatachException;
import com.khubla.pragmatach.framework.api.Response;
import com.khubla.pragmatach.framework.controller.impl.template.SimpleTemplateController;

/**
 * @author tome
 */
@Controller(name = "pragmatachHttpErrorController")
public class HttpErrorController extends SimpleTemplateController {
   /**
    * Exception
    */
   private final Exception exception;

   /**
    * ctor
    */
   public HttpErrorController(Exception e) {
      exception = e;
   }

   public Response render() throws PragmatachException {
      try {
         final Map<String, String> parameters = new HashMap<String, String>();
         parameters.put("message", exception.getMessage());
         final String trace = PragmatachException.getExceptionTrace(exception);
         parameters.put("trace", trace);
         return template("system/error.html", parameters);
      } catch (final Exception e) {
         throw new PragmatachException("Exception in render", e);
      }
   }
}
