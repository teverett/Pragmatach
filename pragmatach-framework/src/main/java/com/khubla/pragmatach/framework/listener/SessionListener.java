package com.khubla.pragmatach.framework.listener;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.khubla.pragmatach.framework.controller.SessionScopedControllers;

/**
 * @author tome
 */
public class SessionListener implements HttpSessionListener {
   /**
    * logger
    */
   private final Logger logger = LoggerFactory.getLogger(this.getClass());

   @Override
   public void sessionCreated(HttpSessionEvent httpSessionEvent) {
      try {
         SessionScopedControllers.populateSessionControllers(httpSessionEvent.getSession());
      } catch (final Exception e) {
         logger.error("Exception in contextInitialized", e);
      }
   }

   @Override
   public void sessionDestroyed(HttpSessionEvent httpSessionEvent) {
   }
}
