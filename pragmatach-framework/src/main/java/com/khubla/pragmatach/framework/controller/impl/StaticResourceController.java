package com.khubla.pragmatach.framework.controller.impl;

import java.io.InputStream;

import com.khubla.pragmatach.framework.annotation.Controller;
import com.khubla.pragmatach.framework.annotation.Route;
import com.khubla.pragmatach.framework.api.PragmatachException;
import com.khubla.pragmatach.framework.api.Response;
import com.khubla.pragmatach.framework.controller.AbstractController;
import com.khubla.pragmatach.framework.servlet.PragmatachServlet;

/**
 * @author tome
 */
@Controller(name = "pragmatachStaticResourceController")
public class StaticResourceController extends AbstractController {
   /**
    * path to static assets
    */
   private final String publicContextPath;

   /**
    * ctor
    */
   public StaticResourceController() {
      publicContextPath = PragmatachServlet.getConfiguration().getPublicResourcePath();
   }

   protected InputStream getStaticResourceInputStream(String[] imageResource) throws PragmatachException {
      try {
         final String resourceUri = buildWildcardResourceURI(imageResource);
         final String actualPath = resourceUri.substring(publicContextPath.length());
         return getResource(actualPath);
      } catch (final Exception e) {
         throw new PragmatachException("Exception in getStaticResourceInputStream", e);
      }
   }

   /**
    * the default route for public resources is /public however this is changed at load time from the configuration
    */
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