package com.khubla.pragmatach.framework.controller;

import com.khubla.pragmatach.framework.api.PragmatachException;
import com.khubla.pragmatach.framework.api.Request;
import com.khubla.pragmatach.framework.controller.impl.RedirectController;
import com.khubla.pragmatach.framework.controller.impl.StaticResourceController;
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

   public static RedirectController getRedirectController(Request request, String uri) throws PragmatachException {
      try {
         final RedirectController ret = new RedirectController(uri);
         ret.setRequest(request);
         return ret;
      } catch (final Exception e) {
         throw new PragmatachException(e);
      }
   }

   public static StaticResourceController getStaticResourceController(Request request, String publicContextPath) {
      final StaticResourceController ret = new StaticResourceController(publicContextPath);
      ret.setRequest(request);
      return ret;
   }
}
