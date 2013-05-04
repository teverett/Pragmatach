package com.khubla.pragmatach.framework.controller.impl.servlet;

import java.io.ByteArrayInputStream;
import java.io.OutputStream;
import java.util.Map;

import javax.servlet.Servlet;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.IOUtils;

import com.khubla.pragmatach.framework.api.PragmatachException;
import com.khubla.pragmatach.framework.controller.impl.AbstractResponse;

/**
 * @author tome
 */
public class ServletServletResponse extends AbstractResponse {
   /**
    * servlet
    */
   private final Servlet servlet;
   /**
    * request
    */
   private final HttpServletRequest httpServletRequest;

   public ServletServletResponse(Map<String, String> cacheHeaders, Servlet servlet, HttpServletRequest httpServletRequest) {
      super(cacheHeaders);
      this.servlet = servlet;
      this.httpServletRequest = httpServletRequest;
   }

   @Override
   public String getContentType() throws PragmatachException {
      return null;
   }

   @Override
   public Map<String, String> getHeaders() throws PragmatachException {
      return getCacheHeaders();
   }

   public HttpServletRequest getHttpServletRequest() {
      return httpServletRequest;
   }

   public Servlet getServlet() {
      return servlet;
   }

   @Override
   public void render(OutputStream outputStream) throws PragmatachException {
      try {
         final StubHttpServletResponse stubHttpServletResponse = new StubHttpServletResponse();
         servlet.service(httpServletRequest, stubHttpServletResponse);
         final ByteArrayInputStream bais = new ByteArrayInputStream(stubHttpServletResponse.getResponse());
         IOUtils.copy(bais, outputStream);
      } catch (final Exception e) {
         throw new PragmatachException("Exception in render", e);
      }
   }
}
