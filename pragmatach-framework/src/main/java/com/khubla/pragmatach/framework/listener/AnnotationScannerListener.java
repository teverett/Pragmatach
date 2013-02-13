package com.khubla.pragmatach.framework.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.khubla.pragmatach.framework.router.impl.ControllerFactory;

/**
 * @author tome
 */
public class AnnotationScannerListener implements ServletContextListener {
   @Override
   public void contextDestroyed(ServletContextEvent servletContextEvent) {
      // TODO Auto-generated method stub
   }

   @Override
   public void contextInitialized(ServletContextEvent servletContextEvent) {
      ControllerFactory.setServletContext(servletContextEvent.getServletContext());
   }
}
