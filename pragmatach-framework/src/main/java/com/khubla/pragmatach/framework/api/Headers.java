package com.khubla.pragmatach.framework.api;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author tome
 */
public class Headers {
   /**
    * HttpServletRequest
    */
   private final HttpServletRequest httpServletRequest;
   /**
    * HttpServletResponse
    */
   private final HttpServletResponse httpServletResponse;

   /**
    * ctor
    */
   public Headers(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
      this.httpServletRequest = httpServletRequest;
      this.httpServletResponse = httpServletResponse;
   }

   /**
    * Accept-Language
    */
   public String getAcceptLanguage() {
      return httpServletRequest.getHeader("Accept-Language");
   }

   /**
    * get content type
    */
   String getContentType() {
      return httpServletRequest.getContentType();
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

   /**
    * pragma
    */
   public String[] getPragma() {
      final String pragmas = httpServletRequest.getHeader("Pragma");
      if (null != pragmas) {
         return pragmas.split(",");
      }
      return null;
   }

   /**
    * referer
    */
   public String getReferer() {
      return httpServletRequest.getHeader("referer");
   }

   /**
    * User-Agent
    */
   public String getUserAgent() {
      return httpServletRequest.getHeader("User-Agent");
   }

   /**
    * Via
    */
   public String getVia() {
      return httpServletRequest.getHeader("Via");
   }

   /**
    * set header
    */
   public void setHeader(String name, String value) {
      httpServletResponse.setHeader(name, value);
   }
}
