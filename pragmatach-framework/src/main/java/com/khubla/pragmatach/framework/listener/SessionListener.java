package com.khubla.pragmatach.framework.listener;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.testng.log4testng.Logger;

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
         logger.error("Exception in sessionCreated", e);
      }
   }

   @Override
   public void sessionDestroyed(HttpSessionEvent httpSessionEvent) {
   }
}
