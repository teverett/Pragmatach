package com.khubla.pragmatach.plugin.mongodb.serializer;

import java.lang.reflect.Method;

import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

/**
 * @author tom
 */
public class MongoInterceptor implements MethodInterceptor {
   private final Object proxiedObject;

   /**
    * ctor
    */
   public MongoInterceptor(Object object) {
      proxiedObject = object;
   }

   @Override
   public Object intercept(Object object, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
      if (method.getName().startsWith("get")) {
         return interceptGetter(object, method, objects, methodProxy);
      } else {
         return method.invoke(proxiedObject, objects);
      }
   }

   private Object interceptGetter(Object object, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
      return method.invoke(proxiedObject, objects);
   }
}
