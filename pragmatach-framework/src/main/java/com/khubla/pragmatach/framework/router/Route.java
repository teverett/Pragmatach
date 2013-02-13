package com.khubla.pragmatach.framework.router;

/**
 * a specific route
 * 
 * @author tome
 */
public class Route {
   public enum Method {
      get, post;
   }

   /**
    * post or get
    */
   private final Method method;
   /**
    * path
    */
   private final String path;
   /**
    * controller
    */
   private final String controller;

   /**
    * ctor
    */
   public Route(Method method, String path, String controller) {
      this.method = method;
      this.path = path;
      this.controller = controller;
   }

   public String getController() {
      return controller;
   }

   public Method getMethod() {
      return method;
   }

   public String getPath() {
      return path;
   }
}
