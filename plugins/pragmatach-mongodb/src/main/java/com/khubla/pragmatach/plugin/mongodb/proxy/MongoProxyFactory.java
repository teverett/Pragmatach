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
      final ProxyFactory proxyFactory = new ProxyFactory();
      proxyFactory.setSuperclass(typeClazz);
      final Class<?> clazz = proxyFactory.createClass();
      final Object instance = clazz.newInstance();
      ((ProxyObject) instance).setHandler(new MongoMethodHandler());
      return instance;
   }
}
