package com.khubla.pragmatach.contrib.controllertester;

import java.util.ArrayList;
import java.util.List;

/**
 * @author tome
 */
public class ControllerUrl {
   /**
    * url parts
    */
   private List<String> parts = new ArrayList<String>();

   public static List<ControllerUrl> readUrls(String filename) throws Exception {
      try {
         return null;
      } catch (Exception e) {
         throw new Exception("Exception in readUrls", e);
      }
   }
}
