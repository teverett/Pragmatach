package com.khubla.pragmatach.plugin.jsp;

import javax.servlet.Servlet;

import com.khubla.pragmatach.framework.api.PragmatachException;
import com.khubla.pragmatach.framework.api.Response;
import com.khubla.pragmatach.framework.controller.impl.template.AbstractTemplateEngineController;

/**
 * @author tome
 */
public class JSPController extends AbstractTemplateEngineController {
   /**
    * ctor
    */
   public JSPController() {
   }

   /**
    * render
    */
   public Response render() throws PragmatachException {
      try {
         final String templateName = getTemplateName();
         final JSPCompiler jspCompiler = new JSPCompiler(getRequest().getServletConfig(), getRequest().getServletContext());
         final Servlet servlet = jspCompiler.getServlet(templateName);
         return new JSPResponse(getCacheHeaders(), servlet, getRequest().getHttpServletRequest(), getTemplateContext());
      } catch (final Exception e) {
         throw new PragmatachException("Exception in render", e);
      }
   }
}
