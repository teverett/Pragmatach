package com.khubla.pragmatach.framework.controller.impl.system;

import com.khubla.pragmatach.framework.annotation.Route;
import com.khubla.pragmatach.framework.api.PragmatachException;
import com.khubla.pragmatach.framework.api.Response;

/**
 * @author tome
 */
public class Http404Controller extends SimpleTemplateController {
   @Route(uri = "/pragmatach/404")
   public Response render() throws PragmatachException {
      return template("http404.html", null);
   }
}
