package com.khubla.pragmatach.framework.controller.impl.system;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.Map;

import com.khubla.pragmatach.framework.api.PragmatachException;
import com.khubla.pragmatach.framework.api.Response;
import com.khubla.pragmatach.framework.controller.AbstractController;
import com.khubla.pragmatach.framework.templater.TrivialTemplater;

/**
 * @author tome
 */
public class SimpleTemplateResponse implements Response {
   /**
    * InputStream
    */
   private final InputStream resourceInputStream;
   /**
    * parameters
    */
   private final Map<String, String> parameters;

   /**
    * ctor
    */
   public SimpleTemplateResponse(String template, Map<String, String> parameters) {
      this.parameters = parameters;
      if (null != template) {
         /*
          * ok, go for it
          */
         resourceInputStream = getClass().getResourceAsStream("/" + template);
      } else {
         resourceInputStream = null;
      }
   }

   @Override
   public int getHTTPCode() {
      return AbstractController.HTTP_OK;
   }

   @Override
   public void render(OutputStream outputStream) throws PragmatachException {
      try {
         TrivialTemplater.template(resourceInputStream, outputStream, parameters);
      } catch (final Exception e) {
         throw new PragmatachException("Exception in render", e);
      }
   }
}
