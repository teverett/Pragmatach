package com.khubla.pragmatach.plugin.jsp;

import java.util.Map;

import org.apache.jasper.runtime.HttpJspBase;

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
         /*
          * get the template name from the annotation
          */
         final String templateName = getTemplateName();
         /*
          * get the servlet
          */
         final JSPCompiler jspCompiler = new JSPCompiler(templateName, getRequest().getServletConfig(), getRequest().getServletContext());
         final HttpJspBase httpServlet = jspCompiler.getServlet();
         httpServlet.init(getRequest().getServletConfig());
         /*
          * add the context info into the request
          */
         final Map<String, Object> ctx = getTemplateContext();
         for (final String s : ctx.keySet()) {
            getRequest().getServletContext().setAttribute(s, ctx.get(s));
         }
         /*
          * go for it
          */
         return new JSPResponse(getCacheHeaders(), httpServlet, getRequest().getHttpServletRequest());
      } catch (final Exception e) {
         throw new PragmatachException("Exception in render", e);
      }
   }
}
