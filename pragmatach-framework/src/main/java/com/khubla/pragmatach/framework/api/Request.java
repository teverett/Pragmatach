package com.khubla.pragmatach.framework.api;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.IOUtils;

import com.khubla.pragmatach.framework.annotation.Route;

/**
 * @author tome
 */
public class Request {
   /**
    * HttpServletRequest
    */
   private final HttpServletRequest httpServletRequest;
   /**
    * method
    */
   private final Route.HttpMethod method;

   /**
    * ctor
    */
   public Request(HttpServletRequest httpServletRequest, Route.HttpMethod method) {
      this.httpServletRequest = httpServletRequest;
      this.method = method;
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

   public HttpServletRequest getHttpServletRequest() {
      return httpServletRequest;
   }

   public InputStream getInputStream() throws PragmatachException {
      try {
         return httpServletRequest.getInputStream();
      } catch (final Exception e) {
         throw new PragmatachException("Exception in getInputStream", e);
      }
   }

   public Route.HttpMethod getMethod() {
      return method;
   }

   /**
    * get the HTTP POST body
    */
   public String getPostBody() throws PragmatachException {
      try {
         final ByteArrayOutputStream baos = new ByteArrayOutputStream();
         IOUtils.copy(getInputStream(), baos);
         return baos.toString();
      } catch (final Exception e) {
         throw new PragmatachException("Exception in getRequestBody", e);
      }
   }

   /**
    * servlet context
    */
   public ServletContext getServletContext() {
      return httpServletRequest.getSession().getServletContext();
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
