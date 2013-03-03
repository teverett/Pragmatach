package com.khubla.pragmatach.framework.i8n;

import java.util.Set;

import com.khubla.pragmatach.framework.api.I8NProvider;

/**
 * @author tome
 */
public class I8NResolver {
   /**
    * providers
    */
   private final Set<I8NProvider> i8nProviders;

   /**
    * ctor
    */
   public I8NResolver(Set<I8NProvider> i8nProviders) {
      this.i8nProviders = i8nProviders;
   }

   /**
    * search for i8N string across providers
    */
   public String i8n(String name) {
      if (null != i8nProviders) {
         for (final I8NProvider iI8NProvider : i8nProviders) {
            final String r = iI8NProvider.getString(name);
            if (null != r) {
               return r;
            }
         }
      }
      return null;
   }
}
