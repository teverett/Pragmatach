package com.khubla.pragmatach.plugin.mongodb.proxy;

import java.beans.Introspector;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import javassist.util.proxy.MethodHandler;

import com.khubla.pragmatach.plugin.mongodb.util.ClassTypeUtils;

/**
 * @author tom
 */
public class MongoMethodHandler implements MethodHandler {
   @Override
   public Object invoke(Object object, Method thisMethod, Method proceed, Object[] args) throws Throwable {
      if (thisMethod.getName().startsWith("get")) {
         lazyLoad(object, thisMethod, proceed, args);
      }
      return proceed.invoke(object, args);
   }

   /**
    * get the field for a getter
    */
   private Field getField(Method thisMethod) throws NoSuchFieldException {
      String fieldName = Introspector.decapitalize(thisMethod.getName().substring(3));
      return thisMethod.getDeclaringClass().getDeclaredField(fieldName);
   }

   /**
    * lazy load
    */
   private void lazyLoad(Object object, Method thisMethod, Method proceed, Object[] args) throws NoSuchFieldException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {
      Field field = getField(thisMethod);
      if (ClassTypeUtils.isLazyLoad(field)) {
         Object o = proceed.invoke(object, args);
         if (null == o) {
            System.out.println("Need Lazy load");
         }
      }
   }
}
