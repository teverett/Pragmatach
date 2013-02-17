package com.khubla.pragmatach.framework.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import com.khubla.pragmatach.framework.annotation.CacheControl;
import com.khubla.pragmatach.framework.api.PragmatachException;
import com.khubla.pragmatach.framework.api.Response;

/**
 * @author tome
 */
public abstract class AbstractResponse implements Response {
   /**
    * cache control
    */
   private static final String CACHECONTROL = "Cache-Control: ";

   @Override
   public Map<String, String> getHeaders() throws PragmatachException {
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
         throw new PragmatachException("Exception in getHeaders", e);
      }
   }

   @Override
   public int getHTTPCode() {
      return HttpServletResponse.SC_OK;
   }
}
