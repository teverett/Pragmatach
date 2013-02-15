package com.khubla.pragmatach.framework.api;

import java.io.InputStream;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @author tome
 */
public class Request {
   /**
    * HttpServletRequest
    */
   private final HttpServletRequest httpServletRequest;

   /**
    * ctor
    */
   public Request(HttpServletRequest httpServletRequest) {
      this.httpServletRequest = httpServletRequest;
   }

   /**
    * headers
    */
   @SuppressWarnings("unchecked")
   public Map<String, String> getHeaders() {
      final Map<String, String> ret = new HashMap<String, String>();
      final Enumeration<String> enumer = httpServletRequest.getHeaderNames();
      while (enumer.hasMoreElements()) {
         final String key = enumer.nextElement();
         ret.put(key, httpServletRequest.getHeader(key));
      }
      return ret;
   }

   public InputStream getInputStream() throws PragmatachException {
      try {
         return httpServletRequest.getInputStream();
      } catch (final Exception e) {
         throw new PragmatachException("Exception in getInputStream", e);
      }
   }

   /**
    * session
    */
   public HttpSession getSession() {
      return httpServletRequest.getSession();
   }

   /**
    * URI
    */
   public String getURI() {
      return httpServletRequest.getRequestURI();
   }
}
