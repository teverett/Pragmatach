package com.khubla.pragmatach.plugin.mongodb.proxy;

import javassist.util.proxy.ProxyFactory;
import javassist.util.proxy.ProxyObject;

/**
 * @author tom
 */
public class MongoProxyFactory {
   /**
    * get a proxy to an instance of a type
    */
   public static Object getProxyObject(Class<?> typeClazz) throws IllegalAccessException, InstantiationException {
      final MongoMethodHandler mongoMethodHandler = new MongoMethodHandler();
      final ProxyFactory proxyFactory = new ProxyFactory();
      proxyFactory.setSuperclass(typeClazz);
      proxyFactory.setFilter(mongoMethodHandler);
      final Class<?> clazz = proxyFactory.createClass();
      final Object instance = clazz.newInstance();
      ((ProxyObject) instance).setHandler(mongoMethodHandler);
      return instance;
   }
}
