package com.khubla.pragmatach.framework.listener;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.apache.log4j.Logger;

import com.khubla.pragmatach.framework.controller.SessionScopedControllers;

/**
 * @author tome
 */
public class SessionListener implements HttpSessionListener {
   /**
    * logger
    */
   private final Logger logger = Logger.getLogger(this.getClass());

   @Override
   public void sessionCreated(HttpSessionEvent httpSessionEvent) {
      try {
         SessionScopedControllers.populateSessionControllers(httpSessionEvent.getSession());
      } catch (final Exception e) {
         logger.fatal("Exception in contextInitialized", e);
      }
   }

   @Override
   public void sessionDestroyed(HttpSessionEvent httpSessionEvent) {
   }
}
