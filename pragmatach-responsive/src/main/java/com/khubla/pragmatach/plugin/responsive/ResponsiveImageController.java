package com.khubla.pragmatach.plugin.responsive;

import java.io.InputStream;
import java.util.Map;

import com.khubla.pragmatach.framework.annotation.Controller;
import com.khubla.pragmatach.framework.annotation.Route;
import com.khubla.pragmatach.framework.annotation.RouteParameter;
import com.khubla.pragmatach.framework.api.PragmatachException;
import com.khubla.pragmatach.framework.api.Response;
import com.khubla.pragmatach.framework.controller.AbstractTemplateEngineController;
import com.khubla.pragmatach.framework.controller.BeanBoundController;
import com.khubla.pragmatach.framework.resourceloader.ResourceLoader;

/**
 * @author tome
 */
@Controller(name = "ResponsiveImageController")
public class ResponsiveImageController extends AbstractTemplateEngineController implements BeanBoundController {
   /**
    * ctor
    */
   public ResponsiveImageController() {
   }

   @Override
   public Map<String, String> getPostFieldValues() throws PragmatachException {
      try {
         return null;
      } catch (final Exception e) {
         throw new PragmatachException("Exception in getPostFieldValues", e);
      }
   }

   /**
    * render
    */
   @Route(uri = "/pragmatach/plugin/getproportionallysizedimage/@img/@width/@height")
   public Response render(@RouteParameter(name = "img") String imageResource, @RouteParameter(name = "width") int width, @RouteParameter(name = "height") int height) throws PragmatachException {
      try {
         final ResourceLoader resourceLoader = new ResourceLoader(getRequest().getServletContext());
         final InputStream imageStream = resourceLoader.getResource(imageResource);
         return new ResponsiveImageResponse(getCacheHeaders(), imageStream, width, height);
      } catch (final Exception e) {
         throw new PragmatachException("Exception in render", e);
      }
   }
}
