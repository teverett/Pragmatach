package com.khubla.pragmatach.plugin.jsp;

import java.util.concurrent.ConcurrentHashMap;

/**
 * @author tome
 */
public class JSPClassCache {
   /**
    * classes
    */
   private final ConcurrentHashMap<String, Class<?>> concurrentHashMap = new ConcurrentHashMap<String, Class<?>>();

   /**
    * add
    */
   public void add(Class<?> clazz, String name) {
      concurrentHashMap.put(name, clazz);
   }

   /**
    * find
    */
   public Class<?> find(String name) {
      return concurrentHashMap.get(name);
   }
}
