package com.khubla.pragmatach.plugin.responsive;

import java.io.InputStream;

import com.khubla.pragmatach.framework.annotation.Controller;
import com.khubla.pragmatach.framework.annotation.Route;
import com.khubla.pragmatach.framework.annotation.RouteParameter;
import com.khubla.pragmatach.framework.api.PragmatachException;
import com.khubla.pragmatach.framework.api.Response;
import com.khubla.pragmatach.framework.controller.impl.StaticResourceController;

/**
 * @author tome
 */
@Controller(name = "ResponsiveImageController")
public class ResponsiveImageController extends StaticResourceController {
   /**
    * ctor
    */
   public ResponsiveImageController() {
   }

   /**
    * render
    */
   @Route(uri = "/pragmatach/plugin/getsizedimage/@img/@width/@height")
   public Response render(@RouteParameter(name = "img") String imageResource, @RouteParameter(name = "width") int width, @RouteParameter(name = "height") int height) throws PragmatachException {
      try {
         final InputStream imageStream = getResource(imageResource);
         return new ResponsiveImageResponse(getCacheHeaders(), imageStream, width, height);
      } catch (final Exception e) {
         throw new PragmatachException("Exception in render", e);
      }
   }
}
