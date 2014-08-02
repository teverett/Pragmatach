package com.khubla.pragmatach.plugin.mongodb.proxy;

import java.beans.Introspector;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import javassist.util.proxy.MethodFilter;
import javassist.util.proxy.MethodHandler;

import com.khubla.pragmatach.plugin.mongodb.util.ClassTypeUtils;

/**
 * @author tom
 */
public class MongoMethodHandler implements MethodHandler, MethodFilter {
   /**
    * get the field for a getter
    */
   private Field getField(Method thisMethod) throws NoSuchFieldException {
      final String fieldName = Introspector.decapitalize(thisMethod.getName().substring(3));
      return thisMethod.getDeclaringClass().getDeclaredField(fieldName);
   }

   @Override
   public Object invoke(Object object, Method thisMethod, Method proceed, Object[] args) throws Throwable {
      /*
       * do the lazy load, if required
       */
      lazyLoad(object, thisMethod, proceed, args);
      /*
       * proceed
       */
      return proceed.invoke(object, args);
   }

   @Override
   public boolean isHandled(Method method) {
      try {
         if (method.getName().startsWith("get")) {
            final Field field = getField(method);
            if (ClassTypeUtils.isLazyLoad(field)) {
               return true;
            }
         }
         return false;
      } catch (final Exception e) {
         System.out.println(e.getMessage());
         return false;
      }
   }

   /**
    * lazy load
    */
   private void lazyLoad(Object object, Method thisMethod, Method proceed, Object[] args) throws NoSuchFieldException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {
      final boolean fetched = MongoProxyFactory.getFetched(object);
      if (false == fetched) {
         final String lazyLoadid = MongoProxyFactory.getId(object);
         System.out.println(lazyLoadid);
      }
   }
}
