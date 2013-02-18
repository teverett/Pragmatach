package com.khubla.pragmatach.framework.api;

import java.util.Map;

/**
 * @author tome
 */
public interface Configuration {
   /**
    * get the path where public assets are found
    */
   String getPublicResourcePath();

   /**
    * all
    */
   Map<String, String> getAll();
}
