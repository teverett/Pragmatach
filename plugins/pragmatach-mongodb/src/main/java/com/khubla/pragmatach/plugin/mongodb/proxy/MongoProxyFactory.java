package com.khubla.pragmatach.plugin.mongodb.proxy;

import java.util.Hashtable;

import javassist.CannotCompileException;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtField;
import javassist.Modifier;
import javassist.NotFoundException;
import javassist.util.proxy.ProxyFactory;
import javassist.util.proxy.ProxyObject;

import org.apache.commons.lang.reflect.FieldUtils;

/**
 * @author tom
 */
public class MongoProxyFactory {
   /**
    * create a class which extends the typeClazz with some new members
    */
   private static Class<?> createProxyClass(Class<?> typeClazz) throws NotFoundException, CannotCompileException {
      final ClassPool classPool = ClassPool.getDefault();
      final CtClass ctClass = classPool.makeClass(typeClazz.getName() + "_monogolazyload");
      ctClass.setSuperclass(classPool.getCtClass(typeClazz.getName()));
      final CtClass ctStringClass = ClassPool.getDefault().get("java.lang.String");
      final CtField ctIdField = new CtField(ctStringClass, LAZYID, ctClass);
      ctIdField.setModifiers(Modifier.PUBLIC);
      ctClass.addField(ctIdField);
      final CtField ctFetchedField = new CtField(CtClass.booleanType, LAZYFETCHED, ctClass);
      ctFetchedField.setModifiers(Modifier.PUBLIC);
      ctClass.addField(ctFetchedField);
      return ctClass.toClass();
   }

   public static boolean getFetched(Object o) throws IllegalAccessException {
      final Boolean b = (Boolean) FieldUtils.readField(o, MongoProxyFactory.LAZYFETCHED);
      return b.booleanValue();
   }

   public static String getId(Object o) throws IllegalAccessException {
      return (String) FieldUtils.readField(o, MongoProxyFactory.LAZYID);
   }

   /**
    * get proxy
    */
   private static Class<?> getProxyClass(Class<?> clazz) throws NotFoundException, CannotCompileException {
      Class<?> ret = lazyProxies.get(clazz);
      if (null == ret) {
         ret = createProxyClass(clazz);
         lazyProxies.put(clazz, ret);
      }
      return ret;
   }

   /**
    * get a proxy to an instance of a type
    */
   public static Object getProxyObject(Class<?> typeClazz) throws NotFoundException, IllegalAccessException, InstantiationException, CannotCompileException {
      /*
       * the handler
       */
      final MongoMethodHandler mongoMethodHandler = new MongoMethodHandler();
      /*
       * factory
       */
      final ProxyFactory proxyFactory = new ProxyFactory();
      /*
       * create the superclass
       */
      final Class<?> superClass = getProxyClass(typeClazz);
      /*
       * set the super class
       */
      proxyFactory.setSuperclass(superClass);
      /*
       * set the filter
       */
      proxyFactory.setFilter(mongoMethodHandler);
      /*
       * create the proxy class
       */
      final Class<?> clazz = proxyFactory.createClass();
      /*
       * create the instance
       */
      final Object instance = clazz.newInstance();
      /*
       * weird magic required to set the handler
       */
      ((ProxyObject) instance).setHandler(mongoMethodHandler);
      /*
       * done
       */
      return instance;
   }

   public static void setFetched(Object o, boolean fetched) throws IllegalAccessException {
      FieldUtils.writeField(o, MongoProxyFactory.LAZYFETCHED, false);
   }

   public static void setID(Object o, String id) throws IllegalAccessException {
      FieldUtils.writeField(o, MongoProxyFactory.LAZYID, id);
   }

   /**
    * some special names
    */
   public static final String LAZYID = "mongolazyid";
   public static final String LAZYFETCHED = "mongolazyfetched";
   /**
    * static registry of lazy loading proxies
    */
   private static final Hashtable<Class<?>, Class<?>> lazyProxies = new Hashtable<Class<?>, Class<?>>();
}
