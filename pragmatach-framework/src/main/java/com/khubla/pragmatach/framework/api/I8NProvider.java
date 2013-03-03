package com.khubla.pragmatach.framework.api;

import java.util.Date;

/**
 * @author tome
 */
public interface I8NProvider {
   /**
    * get localized date
    */
   String getDate(String locale, Date date) throws PragmatachException;

   /**
    * provider name
    */
   String getName();

   /**
    * get localized string.
    */
   String getString(String locale, String name) throws PragmatachException;
}
