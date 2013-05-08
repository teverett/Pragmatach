package com.khubla.pragmatach.plugin.jsp;

import java.util.Map;

import javax.servlet.Servlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.khubla.pragmatach.framework.api.PragmatachException;
import com.khubla.pragmatach.framework.controller.impl.AbstractResponse;

/**
 * @author tome
 */
public class JSPResponse extends AbstractResponse {
   /**
    * servlet
    */
   private final Servlet servlet;
   /**
    * request
    */
   private final HttpServletRequest httpServletRequest;

   public JSPResponse(Map<String, String> cacheHeaders, Servlet servlet, HttpServletRequest httpServletRequest, Map<String, Object> context) {
      super(cacheHeaders);
      this.servlet = servlet;
      this.httpServletRequest = httpServletRequest;
   }

   @Override
   public String getContentType() throws PragmatachException {
      return CONTENT_TYPE_HTML;
   }

   @Override
   public Map<String, String> getHeaders() throws PragmatachException {
      return super.getCacheHeaders();
   }

   // @Override
   // public void render(HttpServletResponse httpServletResponse) throws PragmatachException {
   // try {
   // requestDispatcher.forward(servletRequest, httpServletResponse);
   // } catch (final Exception e) {
   // throw new PragmatachException("Exception in render", e);
   // }
   // }
   @Override
   public void render(HttpServletResponse httpServletResponse) throws PragmatachException {
      try {
         servlet.service(httpServletRequest, httpServletResponse);
      } catch (final Exception e) {
         throw new PragmatachException("Exception in render", e);
      }
   }
}
