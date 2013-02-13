package com.khubla.pragmatach.framework.router;

import java.lang.reflect.Method;

import com.khubla.pragmatach.framework.annotation.Route.HttpMethod;
import com.khubla.pragmatach.framework.api.PragmatachException;
import com.khubla.pragmatach.framework.controller.PragmatachController;

/**
 * a specific route
 * 
 * @author tome
 */
public class PragmatachRoute {
   /**
    * post or get
    */
   private final HttpMethod httpMethod;
   /**
    * path
    */
   private final String path;
   /**
    * controller
    */
   private final Class<?> controllerClazz;
   /**
    * method
    */
   private final Method method;

   /**
    * ctor
    */
   public PragmatachRoute(HttpMethod httpMethod, Method method, String path, Class<?> clazz) throws PragmatachException {
      this.method = method;
      this.path = path;
      controllerClazz = clazz;
      this.httpMethod = httpMethod;
   }

   public Class<?> getControllerClazz() {
      return controllerClazz;
   }

   /**
    * get a class instance of the controller
    */
   public PragmatachController getControllerClazzInstance() throws PragmatachException {
      try {
         return (PragmatachController) controllerClazz.newInstance();
      } catch (final Exception e) {
         throw new PragmatachException("Exception in getControllerClazzInstance", e);
      }
   }

   public HttpMethod getHttpMethod() {
      return httpMethod;
   }

   public Method getMethod() {
      return method;
   }

   public String getPath() {
      return path;
   }

   public boolean matches(String path) {
      return false;
   }
}
