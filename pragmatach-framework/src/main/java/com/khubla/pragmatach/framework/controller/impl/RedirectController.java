package com.khubla.pragmatach.framework.controller.impl;

import java.net.URI;
import java.net.URLEncoder;

import com.khubla.pragmatach.framework.api.PragmatachException;
import com.khubla.pragmatach.framework.api.Response;
import com.khubla.pragmatach.framework.controller.AbstractController;

/**
 * @author tome
 */
public class RedirectController extends AbstractController {
   /**
    * uri
    */
   private final URI uri;

   public RedirectController(String uri) throws PragmatachException {
      try {
         this.uri = new URI(uri);
      } catch (final Exception e) {
         throw new PragmatachException(e);
      }
   }

   public RedirectController(String uri, String[] parameters) throws PragmatachException {
      try {
         String completeURI = uri;
         if (null != parameters) {
            for (final String parameter : parameters) {
               completeURI += "/" + URLEncoder.encode(parameter, "UTF-8");
            }
         }
         this.uri = new URI(completeURI);
      } catch (final Exception e) {
         throw new PragmatachException(e);
      }
   }

   public Response render() throws PragmatachException {
      return new RedirectResponse(uri.toASCIIString());
   }
}
