package com.khubla.pragmatach.plugin.jsp;

import javax.servlet.Servlet;

import com.khubla.pragmatach.framework.api.PragmatachException;
import com.khubla.pragmatach.framework.controller.impl.servlet.AbstractServletController;

/**
 * @author tome
 */
public class JSPController extends AbstractServletController {
   /**
    * ctor
    */
   public JSPController() {
   }

   @Override
   public Servlet getServlet() throws PragmatachException {
      try {
         JSPCompiler jspCompiler = new JSPCompiler(this.getRequest().getServletConfig(), this.getRequest().getServletContext());
         return jspCompiler.getServlet(this.getTemplate());
      } catch (Exception e) {
         throw new PragmatachException("Exception in getServlet", e);
      }
   }
}
