package com.khubla.pragmatach.framework.router;

import java.util.Hashtable;

import javax.servlet.http.HttpSession;

import com.khubla.pragmatach.framework.controller.PragmatachController;

/**
 * @author tome
 */
public class SessionScopedControllers {
   /***
    * Session key for session scoped controllers
    */
   private static final String SESSION_CONTROLLERS = "Session-Scoped-Controllers";

   /**
    * get controller with clazz
    */
   public static PragmatachController getController(HttpSession httpSession, Class<?> clazz) {
      final Hashtable<Class<?>, PragmatachController> map = getMap(httpSession);
      return map.get(clazz);
   }

   /**
    * get the map
    */
   @SuppressWarnings("unchecked")
   private static Hashtable<Class<?>, PragmatachController> getMap(HttpSession httpSession) {
      Hashtable<Class<?>, PragmatachController> controllers = (Hashtable<Class<?>, PragmatachController>) httpSession.getAttribute(SESSION_CONTROLLERS);
      if (null == controllers) {
         controllers = new Hashtable<Class<?>, PragmatachController>();
         httpSession.setAttribute(SESSION_CONTROLLERS, controllers);
      }
      return controllers;
   }

   /**
    * set controller
    */
   public static void setController(HttpSession httpSession, PragmatachController pragmatachController) {
      final Hashtable<Class<?>, PragmatachController> map = getMap(httpSession);
      map.put(pragmatachController.getClass(), pragmatachController);
   }
}
