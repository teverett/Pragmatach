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
    * resourceUri
    */
   private final String resourceUri;

   /**
    * ctor
    */
   public StaticResourceController(String resourceUri, String publicContextPath) {
      this.publicContextPath = publicContextPath;
      this.resourceUri = resourceUri;
   }

   public Response render() throws PragmatachException {
      try {
         final String actualPath = resourceUri.substring(publicContextPath.length());
         return new StaticResourceResponse(getResource(actualPath));
      } catch (final Exception e) {
         throw new PragmatachException("Exception in render", e);
      }
   }
}