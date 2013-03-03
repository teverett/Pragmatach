package com.khubla.pragmatach.framework.api;

/**
 * @author tome
 */
public interface I8NProvider {
   /**
    * provider name
    */
   String getName();

   /**
    * get string
    */
   String getString(String name);
}
