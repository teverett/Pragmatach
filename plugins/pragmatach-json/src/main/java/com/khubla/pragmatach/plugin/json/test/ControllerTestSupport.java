package com.khubla.pragmatach.plugin.json.test;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;

import com.khubla.pragmatach.framework.api.PragmatachException;
import com.khubla.pragmatach.framework.api.Response;
import com.khubla.pragmatach.plugin.json.JSONController;

/**
 * @author tome
 */
public class ControllerTestSupport {
   /**
    * test rendering a controller
    */
   public static String performRender(JSONController jsonController) throws PragmatachException {
      try {
         final Response response = jsonController.render();
         final ByteArrayOutputStream baos = new ByteArrayOutputStream();
         response.render(baos);
         return baos.toString();
      } catch (final Exception e) {
         throw new PragmatachException("Exception in performRender", e);
      }
   }

   /**
    * unrender controller
    */
   public static String unRender(JSONController jsonController, InputStream inputStream) throws PragmatachException {
      try {
         return null;
      } catch (final Exception e) {
         throw new PragmatachException("Exception in unRender", e);
      }
   }
}
