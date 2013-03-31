package com.khubla.pragmatach.framework.configuration;

import java.util.HashMap;
import java.util.Map;

import com.khubla.pragmatach.framework.api.PragmatachException;

/**
 * a configuration class mainly useful for testing
 * 
 * @author tome
 */
public class HashmapConfigurationImpl extends BaseConfiguration {
   /**
    * 
    */
   private final Map<String, String> map = new HashMap<String, String>();

   @Override
   public Map<String, String> getAll() throws PragmatachException {
      return map;
   }

   @Override
   public Object getObject(String name) throws PragmatachException {
      return resolveObject(map.get(name));
   }

   @Override
   public String getParameter(String name) throws PragmatachException {
      return resolveString(map.get(name));
   }

   public void setParameter(String name, String value) {
      map.put(name, value);
   }
}
