package com.khubla.pragmatach.framework.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.log4j.Logger;

import com.khubla.pragmatach.framework.annotation.AnnotationsScanner;
import com.khubla.pragmatach.framework.plugin.Plugins;

/**
 * @author tome
 */
public class AnnotationScannerListener implements ServletContextListener {
   /**
    * logger
    */
   private final Logger logger = Logger.getLogger(this.getClass());

   @Override
   public void contextDestroyed(ServletContextEvent servletContextEvent) {
      // TODO Auto-generated method stub
   }

   @Override
   public void contextInitialized(ServletContextEvent servletContextEvent) {
      try {
         AnnotationsScanner.scan(servletContextEvent.getServletContext());
         Plugins.scan(servletContextEvent.getServletContext());
      } catch (final Exception e) {
         logger.fatal("Exception in contextInitialized", e);
      }
   }
}