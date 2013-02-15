package com.khubla.pragmatach.framework.controller.impl.system;

import java.util.HashMap;
import java.util.Map;

import com.khubla.pragmatach.framework.annotation.Route;
import com.khubla.pragmatach.framework.api.PragmatachException;
import com.khubla.pragmatach.framework.api.Response;
import com.khubla.pragmatach.framework.controller.impl.SimpleTemplateController;

/**
 * @author tome
 */
public class HttpErrorController extends SimpleTemplateController {
   @Route(uri = "/pragmatach/error")
   public Response render() throws PragmatachException {
      try {
         final Map<String, String> parameters = new HashMap<String, String>();
         return template("system/httperror.html", parameters);
      } catch (final Exception e) {
         throw new PragmatachException("Exception in render", e);
      }
   }
}
