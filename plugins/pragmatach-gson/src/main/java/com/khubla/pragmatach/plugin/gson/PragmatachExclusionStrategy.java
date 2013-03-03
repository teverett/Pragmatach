package com.khubla.pragmatach.plugin.gson;

import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;

/**
 * @author tome
 */
public class PragmatachExclusionStrategy implements ExclusionStrategy {
   private final Class<?> typeToSkip;

   public PragmatachExclusionStrategy(Class<?> typeToSkip) {
      this.typeToSkip = typeToSkip;
   }

   public boolean shouldSkipClass(Class<?> clazz) {
      return (clazz == typeToSkip);
   }

   public boolean shouldSkipField(FieldAttributes f) {
      return false;
   }
}
