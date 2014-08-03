package com.khubla.pragmatach.plugin.mongodb.util;

import java.lang.reflect.Type;
import java.util.HashSet;

/**
 * TypeUtil for identifying atomic types
 *
 * @author tom
 */
public class AtomicTypeUtil {
   private static HashSet<String> getWrapperTypes() {
      final HashSet<String> ret = new HashSet<String>();
      ret.add(Boolean.class.getName());
      ret.add(Character.class.getName());
      ret.add(Byte.class.getName());
      ret.add(Short.class.getName());
      ret.add(Integer.class.getName());
      ret.add(Long.class.getName());
      ret.add(Float.class.getName());
      ret.add(Double.class.getName());
      ret.add(Void.class.getName());
      return ret;
   }

   public static boolean isSimpleType(Type type) {
      if (type.getClass().isInstance(Class.class)) {
         final Class<?> clazz = (Class<?>) type;
         return (clazz.isPrimitive() || AtomicTypeUtil.isWrapperType(clazz) || (clazz.getName().compareTo("java.lang.String") == 0));
      } else {
         return false;
      }
   }

   public static boolean isWrapperType(Class<?> clazz) {
      return WRAPPER_TYPES.contains(clazz.getName());
   }

   private static final HashSet<String> WRAPPER_TYPES = getWrapperTypes();
}
