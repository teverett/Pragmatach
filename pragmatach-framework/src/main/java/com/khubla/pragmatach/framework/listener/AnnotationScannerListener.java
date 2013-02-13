package com.khubla.pragmatach.framework.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.khubla.pragmatach.framework.annotation.AnnotationsScanner;

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
      try {
         AnnotationsScanner.scan(servletContextEvent.getServletContext());
      } catch (final Exception e) {
         e.printStackTrace();
      }
   }
}