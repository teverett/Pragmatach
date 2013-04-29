package com.khubla.pragmatach.plugin.datanucleus;

import java.util.HashMap;
import java.util.Map;

/**
 * @author tome
 */
public class DataNucleusClassLoader extends ClassLoader {
   /**
    * classes
    */
   private Map<String, Class<?>> classes = new HashMap<String, Class<?>>();

   /**
    * ctor
    */
   public DataNucleusClassLoader(ClassLoader classLoader) {
      super(classLoader);
   }

   @Override
   public Class<?> findClass(String name) throws ClassNotFoundException {
      if (classes.containsKey(name)) {
         return classes.get(name);
      } else {
         throw new ClassNotFoundException("Class '" + name + "' not found ");
      }
   }

   public void addClass(String className, byte[] enhancedBytes) {
      Class<?> clazz = this.defineClass(className, enhancedBytes, 0, enhancedBytes.length);
      this.classes.put(clazz.getCanonicalName(), clazz);
   }
}
