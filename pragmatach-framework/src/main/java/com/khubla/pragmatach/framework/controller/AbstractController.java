package com.khubla.pragmatach.framework.controller;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import com.khubla.pragmatach.framework.annotation.CacheControl;
import com.khubla.pragmatach.framework.api.PragmatachException;
import com.khubla.pragmatach.framework.api.Request;
import com.khubla.pragmatach.framework.api.Response;
import com.khubla.pragmatach.framework.controller.impl.RedirectController;
import com.khubla.pragmatach.framework.resourceloader.ResourceLoader;
import com.khubla.pragmatach.framework.servlet.PragmatachServlet;

/**
 * @author tome
 */
public abstract class AbstractController implements PragmatachController {
   /**
    * build a path from a wildcard path
    */
   public static String buildWildcardResourceURI(String[] resource) {
      String ret = "";
      for (final String s : resource) {
         ret += "/" + s;
      }
      return ret;
   }

   /**
    * request
    */
   private Request request;
   /**
    * cache control
    */
   private static final String CACHECONTROL = "Cache-Control: ";

   /**
    * forward to another controller uri
    */
   public Response forward(String uri) throws PragmatachException {
      final String forwardURI = request.getHttpServletRequest().getContextPath() + uri;
      return new RedirectController(forwardURI).render();
   }

   /**
    * generate the cache headers
    */
   protected Map<String, String> getCacheHeaders() throws PragmatachException {
      try {
         final CacheControl cacheControl = this.getClass().getAnnotation(CacheControl.class);
         if (null != cacheControl) {
            final Map<String, String> ret = new HashMap<String, String>();
            String cacheControlHeader = "";
            boolean first = true;
            /*
             * max age
             */
            if (-1 != cacheControl.maxAge()) {
               cacheControlHeader += "max-age=" + cacheControl.maxAge();
               first = false;
            }
            /*
             * s-max
             */
            if (-1 != cacheControl.sMaxAge()) {
               if (false == first) {
                  cacheControlHeader += ",";
               }
               cacheControlHeader += "s-maxage=" + cacheControl.sMaxAge();
               first = false;
            }
            /*
             * policy
             */
            if (false == first) {
               cacheControlHeader += ",";
            }
            cacheControlHeader += cacheControl.policy().toString().toLowerCase().trim();
            /*
             * done
             */
            ret.put(CACHECONTROL, cacheControlHeader);
            return ret;
         } else {
            return null;
         }
      } catch (final Exception e) {
         throw new PragmatachException("Exception in getCacheHeaders", e);
      }
   }

   /**
    * get a configuration parameter from the pragmatatch configuration
    */
   public String getConfigurationParameter(String name) {
      return PragmatachServlet.getConfiguration().getParameter(name);
   }

   /**
    * get the request
    */
   public Request getRequest() {
      return request;
   }

   /**
    * get a resource using the servlet's class loader
    */
   protected InputStream getResource(String resource) throws PragmatachException {
      try {
         final ResourceLoader resourceLoader = new ResourceLoader(request.getServletContext());
         return resourceLoader.getResource(resource);
      } catch (final Exception e) {
         throw new PragmatachException("Exception in getResource", e);
      }
   }

   /**
    * get the instance of a session-bound controller
    */
   @SuppressWarnings("unchecked")
   public <T> T getSessionScopedController(Class<T> clazz) {
      return (T) SessionScopedControllers.getController(request.getSession(), clazz);
   }

   /**
    * redirect. This API requires a full URL including authority, hostname, port, etc.
    */
   public Response redirect(String uri) throws PragmatachException {
      return new RedirectController(uri).render();
   }

   /**
    * set the request
    */
   public void setRequest(Request request) {
      this.request = request;
   }
}
