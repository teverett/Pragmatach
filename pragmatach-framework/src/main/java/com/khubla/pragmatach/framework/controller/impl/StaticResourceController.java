package com.khubla.pragmatach.framework.controller.impl;

import com.khubla.pragmatach.framework.api.PragmatachException;
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
   public StaticResourceController(String publicContextPath) {
      this.publicContextPath = publicContextPath;
   }

   public Response render() throws PragmatachException {
      try {
         final String actualPath = getRequest().getURI().substring(publicContextPath.length());
         return new StaticResourceResponse(actualPath);
      } catch (final Exception e) {
         throw new PragmatachException("Exception in render", e);
      }
   }
}