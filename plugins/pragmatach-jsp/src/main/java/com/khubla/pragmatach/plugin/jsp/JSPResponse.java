package com.khubla.pragmatach.plugin.jsp;

import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.Map;

import com.khubla.pragmatach.framework.api.PragmatachException;
import com.khubla.pragmatach.framework.controller.impl.AbstractResponse;

/**
 * @author tome
 */
public class JSPResponse extends AbstractResponse {
   /**
    * template
    */
   private final String templateName;
   /**
    * context
    */
   private final Map<String, Object> context;

   public JSPResponse(Map<String, String> cacheHeaders, String templateName, Map<String, Object> context) {
      super(cacheHeaders);
      this.templateName = templateName;
      this.context = context;
   }

   @Override
   public String getContentType() throws PragmatachException {
      return CONTENT_TYPE_HTML;
   }

   @Override
   public Map<String, String> getHeaders() throws PragmatachException {
      return super.getCacheHeaders();
   }

   @Override
   public void render(OutputStream outputStream) throws PragmatachException {
      try {
         final Writer writer = new OutputStreamWriter(outputStream);
         writer.flush();
      } catch (final Exception e) {
         throw new PragmatachException("Exception in render", e);
      }
   }
}
