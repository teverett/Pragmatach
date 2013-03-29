package com.khubla.pragmatach.framework.controller.impl;

import java.io.InputStream;

import com.khubla.pragmatach.framework.annotation.Controller;
import com.khubla.pragmatach.framework.annotation.Route;
import com.khubla.pragmatach.framework.api.PragmatachException;
import com.khubla.pragmatach.framework.api.Response;
import com.khubla.pragmatach.framework.controller.AbstractController;

/**
 * @author tome
 */
@Controller(name = "pragmatachStaticResourceController")
public class StaticResourceController extends AbstractController {
   /**
    * public resource dir
    */
   private static final String PUBLIC_RESOURCE_DIR = "/public";

   /**
    * ctor
    */
   public StaticResourceController() {
   }

   protected InputStream getStaticResourceInputStream(String[] imageResource) throws PragmatachException {
      try {
         final String resourceUri = buildWildcardResourceURI(imageResource);
         return getResource(PUBLIC_RESOURCE_DIR + resourceUri);
      } catch (final Exception e) {
         throw new PragmatachException("Exception in getStaticResourceInputStream", e);
      }
   }

   @Route(uri = "/public/*")
   public Response render(String[] imageResource) throws PragmatachException {
      try {
         final InputStream is = getStaticResourceInputStream(imageResource);
         return new StaticResourceResponse(getCacheHeaders(), is);
      } catch (final Exception e) {
         throw new PragmatachException("Exception in render", e);
      }
   }
}