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
    * get a parameter by name
    */
   String getParameter(String name);

   /**
    * get the path where public assets are found
    */
   String getPublicResourcePath();
}
