package com.khubla.pragmatach.framework.controller.impl.servlet;

import javax.servlet.Servlet;

import com.khubla.pragmatach.framework.api.PragmatachException;
import com.khubla.pragmatach.framework.api.Response;
import com.khubla.pragmatach.framework.controller.impl.template.AbstractTemplateEngineController;

/**
 * @author tome
 */
public abstract class AbstractServletController extends AbstractTemplateEngineController {
   /**
    * get the servlet
    */
   public abstract Servlet getServlet() throws PragmatachException;

   /**
    * render
    */
   public Response render() throws PragmatachException {
      try {
         final Servlet servlet = getServlet();
         return new ServletServletResponse(getCacheHeaders(), servlet, getRequest().getHttpServletRequest());
      } catch (final Exception e) {
         throw new PragmatachException("Exception in render", e);
      }
   }
}
