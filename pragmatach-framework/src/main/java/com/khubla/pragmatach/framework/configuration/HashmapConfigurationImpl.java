package com.khubla.pragmatach.framework.configuration;

import java.util.HashMap;
import java.util.Map;

import com.khubla.pragmatach.framework.api.Configuration;

/**
 * a configuration class mainly useful for testing
 * 
 * @author tome
 */
public class HashmapConfigurationImpl implements Configuration {
   /**
    * 
    */
   private final Map<String, String> map = new HashMap<String, String>();

   @Override
   public Map<String, String> getAll() {
      return map;
   }

   @Override
   public String getParameter(String name) {
      return map.get(name);
   }

   public void setParameter(String name, String value) {
      map.put(name, value);
   }
}
