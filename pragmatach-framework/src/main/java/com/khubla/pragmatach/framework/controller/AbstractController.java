package com.khubla.pragmatach.framework.controller;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import com.khubla.pragmatach.framework.annotation.CacheControl;
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
   /**
    * cache control
    */
   private static final String CACHECONTROL = "Cache-Control: ";

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
            if (-1 != cacheControl.maxAge()) {
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

   public void setRequest(Request request) {
      this.request = request;
   }
}
