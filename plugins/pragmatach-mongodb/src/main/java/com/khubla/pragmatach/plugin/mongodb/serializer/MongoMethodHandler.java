package com.khubla.pragmatach.plugin.mongodb.serializer;

import java.lang.reflect.Method;

import javassist.util.proxy.MethodHandler;

/**
 * @author tom
 */
public class MongoMethodHandler implements MethodHandler {
   @Override
   public Object invoke(Object object, Method thisMethod, Method proceed, Object[] args) throws Throwable {
      return proceed.invoke(object, args);
   }
}
