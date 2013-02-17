package com.khubla.pragmatach.framework.controller;

import java.io.InputStream;

import com.khubla.pragmatach.framework.api.PragmatachException;
import com.khubla.pragmatach.framework.api.Request;
import com.khubla.pragmatach.framework.resourceloader.ResourceLoader;

/**
 * @author tome
 */
public abstract class AbstractController implements PragmatachController {
   /**
    * request
    */
   private Request request;

   public Request getRequest() {
      return request;
   }

   /**
    * get a resource using the servlet's class loader
    */
   public InputStream getResource(String resource) throws PragmatachException {
      try {
         final ResourceLoader resourceLoader = new ResourceLoader(request.getServletContext());
         return resourceLoader.getResource(resource);
      } catch (final Exception e) {
         throw new PragmatachException("Exception in getResource", e);
      }
   }

   public void setRequest(Request request) {
      this.request = request;
   }
}
