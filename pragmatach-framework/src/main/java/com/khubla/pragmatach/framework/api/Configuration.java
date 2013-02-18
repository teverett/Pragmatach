package com.khubla.pragmatach.framework.api;

import java.util.Map;

/**
 * @author tome
 */
public interface Configuration {
   /**
    * all
    */
   Map<String, String> getAll();

   /**
    * get the path where public assets are found
    */
   String getPublicResourcePath();
}
