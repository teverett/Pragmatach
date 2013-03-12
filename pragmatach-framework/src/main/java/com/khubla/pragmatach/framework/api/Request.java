package com.khubla.pragmatach.framework.api;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
    * headers
    */
   private final Headers headers;
   /**
    * cookies
    */
   private final Cookies cookies;
   /**
    * creation time
    */
   private final long creationTime = System.currentTimeMillis();

   /**
    * ctor
    */
   public Request(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Route.HttpMethod method) {
      this.httpServletRequest = httpServletRequest;
      this.method = method;
      headers = new Headers(httpServletRequest, httpServletResponse);
      cookies = new Cookies(httpServletRequest, httpServletResponse);
   }

   public Cookies getCookies() {
      return cookies;
   }

   public long getCreationTime() {
      return creationTime;
   }

   public Headers getHeaders() {
      return headers;
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
    * parameters
    */
   public Map<String, String[]> getParameters() {
      return httpServletRequest.getParameterMap();
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
    * get the resource path, taking off the servlet context path
    */
   public String getResourcePath() throws PragmatachException {
      try {
         final String uri = getURI();
         String ret = uri.substring(getHttpServletRequest().getContextPath().length());
         if (ret.endsWith("/")) {
            ret = ret.substring(0, ret.length() - 1);
         }
         if ((null == ret) || (ret.length() == 0)) {
            ret = "/";
         }
         return ret;
      } catch (final Exception e) {
         throw new PragmatachException("Exception in resourcePath", e);
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
