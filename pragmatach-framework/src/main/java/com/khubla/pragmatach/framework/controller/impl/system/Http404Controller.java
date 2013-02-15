package com.khubla.pragmatach.framework.controller.impl.system;

import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;

import com.khubla.pragmatach.framework.annotation.Route;
import com.khubla.pragmatach.framework.api.PragmatachException;
import com.khubla.pragmatach.framework.api.Response;
import com.khubla.pragmatach.framework.controller.impl.SimpleTemplateController;

/**
 * @author tome
 */
public class Http404Controller extends SimpleTemplateController {
   @Route(uri = "/pragmatach/404")
   public Response render(String uri) throws PragmatachException {
      try {
         final Map<String, String> parameters = new HashMap<String, String>();
         parameters.put("uri", URLDecoder.decode(uri, "UTF-8"));
         return template("system/http404.html", parameters);
      } catch (final Exception e) {
         throw new PragmatachException("Exception in render", e);
      }
   }
}
