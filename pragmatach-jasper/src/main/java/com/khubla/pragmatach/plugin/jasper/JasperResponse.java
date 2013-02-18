package com.khubla.pragmatach.plugin.jasper;

import java.io.OutputStream;
import java.util.Map;

import com.khubla.pragmatach.framework.api.PragmatachException;
import com.khubla.pragmatach.framework.controller.AbstractResponse;

/**
 * @author tome
 */
public class JasperResponse extends AbstractResponse {
   /**
    * template
    */
   private final String template;
   /**
    * templatename
    */
   private final String templateName;
   /**
    * context
    */
   private final Map<String, Object> context;

   public JasperResponse(String templateName, String template, Map<String, Object> context) {
      this.template = template;
      this.context = context;
      this.templateName = templateName;
   }

   @Override
   public void render(OutputStream outputStream) throws PragmatachException {
      try {
      } catch (final Exception e) {
         throw new PragmatachException("Exception in render", e);
      }
   }
}
