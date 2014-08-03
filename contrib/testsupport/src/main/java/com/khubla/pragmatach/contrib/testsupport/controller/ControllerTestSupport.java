package com.khubla.pragmatach.contrib.testsupport.controller;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Vector;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;

import com.khubla.pragmatach.framework.api.PragmatachException;
import com.khubla.pragmatach.framework.controller.PragmatachController;
import com.khubla.pragmatach.framework.servlet.PragmatachServlet;

/**
 * @author tome
 */
public class ControllerTestSupport {
   /**
    * test rendering a controller
    */
   public static String performRender(PragmatachController controller) throws PragmatachException {
      try {
         /*
          * mock up a servlet context
          */
         final ServletConfig servletConfig = mock(ServletConfig.class);
         if (null != servletConfig) {
            when(servletConfig.getInitParameterNames()).thenReturn(new Vector<String>().elements());
            final ServletContext servletContext = mock(ServletContext.class);
            final PragmatachServlet pragmatachServlet = new PragmatachServlet();
            pragmatachServlet.init(servletConfig);
            when(servletConfig.getServletContext()).thenReturn(servletContext);
         }
         /*
          * hm
          */
         return null;
      } catch (final Exception e) {
         throw new PragmatachException("Exception in performRender", e);
      }
   }
}
