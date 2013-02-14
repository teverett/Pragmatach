package com.khubla.pragmatach.framework.controller.impl;

import com.khubla.pragmatach.framework.api.PragmatachException;
import com.khubla.pragmatach.framework.api.Request;
import com.khubla.pragmatach.framework.api.Response;
import com.khubla.pragmatach.framework.controller.AbstractController;

/**
 * @author tome
 */
public class StaticResourceController extends AbstractController {
   /**
    * path to static assets
    */
   private final String publicContextPath;

   /**
    * ctor
    */
   public StaticResourceController(String publicContextPath, Request request) {
      super(request);
      this.publicContextPath = publicContextPath;
   }

   public Response render() throws PragmatachException {
      try {
         final String actualPath = getRequest().getHttpServletRequest().getRequestURI().substring(publicContextPath.length());
         return new StaticResourceResponse(actualPath);
      } catch (final Exception e) {
         throw new PragmatachException("Exception in StaticResourceController", e);
      }
   }
}