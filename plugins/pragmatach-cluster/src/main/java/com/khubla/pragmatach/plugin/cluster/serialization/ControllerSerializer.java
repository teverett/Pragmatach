package com.khubla.pragmatach.plugin.cluster.serialization;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.khubla.pragmatach.framework.api.PragmatachException;
import com.khubla.pragmatach.framework.api.Request;
import com.khubla.pragmatach.framework.controller.PragmatachController;

/**
 * simply serialize controllers to json. It's a cheesy hack, and it works.
 * 
 * @author tome
 */
public class ControllerSerializer {
   /**
    * gson
    */
   private static final Gson gson = new GsonBuilder().setExclusionStrategies(new PragmatachExclusionStrategy(Request.class)).create();

   /**
    * deserialize controller from JSON
    */
   public static PragmatachController deserialize(String json) throws PragmatachException {
      try {
         return gson.fromJson(json, PragmatachController.class);
      } catch (final Exception e) {
         throw new PragmatachException("Exception in deserialize", e);
      }
   }

   /**
    * serialize controller to JSON
    */
   public static String serialize(PragmatachController pragmatachController) throws PragmatachException {
      try {
         return gson.toJson(pragmatachController);
      } catch (final Exception e) {
         throw new PragmatachException("Exception in serialize", e);
      }
   }
}
