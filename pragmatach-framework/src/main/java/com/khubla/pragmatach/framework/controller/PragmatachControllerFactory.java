package com.khubla.pragmatach.framework.controller;

import com.khubla.pragmatach.framework.api.Request;
import com.khubla.pragmatach.framework.controller.impl.system.HttpErrorController;

/**
 * @author tome
 */
public class PragmatachControllerFactory {
   public static HttpErrorController getHttpErrorController(Request request, Exception e) {
      final HttpErrorController ret = new HttpErrorController(e);
      ret.setRequest(request);
      return ret;
   }
}
