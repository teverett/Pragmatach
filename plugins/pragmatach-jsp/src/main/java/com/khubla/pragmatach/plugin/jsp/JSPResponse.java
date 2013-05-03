package com.khubla.pragmatach.plugin.jsp;

import java.io.OutputStream;
import java.util.Map;

import javax.servlet.Servlet;

import com.khubla.pragmatach.framework.api.PragmatachException;
import com.khubla.pragmatach.framework.controller.impl.AbstractResponse;

/**
 * @author tome
 *         <p>
 *         The idea here is pretty simple. Add the context objects, and redirect.
 *         </p>
 */
public class JSPResponse extends AbstractResponse {
   public JSPResponse(Map<String, String> cacheHeaders, String templateName, Map<String, Object> context) {
      super(cacheHeaders);
   }

   @Override
   public String getContentType() throws PragmatachException {
      return CONTENT_TYPE_HTML;
   }

   @Override
   public Map<String, String> getHeaders() throws PragmatachException {
      return super.getCacheHeaders();
   }

   @Override
   public void render(OutputStream outputStream) throws PragmatachException {
      try {
         JSPCompiler jspCompiler = new JSPCompiler();
         Servlet servlet = jspCompiler.getServlet("sdsd");
         // servlet.service(this.g, arg1)
      } catch (final Exception e) {
         throw new PragmatachException("Exception in render", e);
      }
   }
}
