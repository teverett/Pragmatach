package com.khubla.pragmatach.framework.i8n;

import java.util.Date;
import java.util.Set;

import com.khubla.pragmatach.framework.api.I8NProvider;
import com.khubla.pragmatach.framework.api.PragmatachException;

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
    * get localized date
    */
   public String date(String locale, Date date) throws PragmatachException {
      if (null != i8nProviders) {
         for (final I8NProvider iI8NProvider : i8nProviders) {
            final String d = iI8NProvider.getDate(locale, date);
            if (null != d) {
               return d;
            }
         }
      }
      return null;
   }

   /**
    * search for i8N string across providers
    */
   public String i8n(String locale, String name) throws PragmatachException {
      if (null != i8nProviders) {
         for (final I8NProvider iI8NProvider : i8nProviders) {
            final String r = iI8NProvider.getString(locale, name);
            if (null != r) {
               return r;
            }
         }
      }
      return null;
   }
}
