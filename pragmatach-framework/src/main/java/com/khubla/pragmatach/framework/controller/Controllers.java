package com.khubla.pragmatach.framework.controller;

import java.util.Set;

import com.khubla.pragmatach.framework.annotation.AnnotationsScanner;

/**
 * @author tome
 */
public class Controllers {
   /**
    * instance
    */
   private static Controllers instance;

   /**
    * singleton
    */
   public static Controllers getInstance() {
      if (null == instance) {
         instance = new Controllers();
      }
      return instance;
   }

   private final Set<Class<?>> controllers;

   /**
    * ctor
    */
   private Controllers() {
      controllers = AnnotationsScanner.getControllers();
   }

   public Set<Class<?>> getControllers() {
      return controllers;
   }
}
