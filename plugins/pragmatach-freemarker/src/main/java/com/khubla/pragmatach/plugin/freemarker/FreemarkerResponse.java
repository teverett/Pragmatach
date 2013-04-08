package com.khubla.pragmatach.plugin.freemarker;

import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.Map;

import com.khubla.pragmatach.framework.api.PragmatachException;
import com.khubla.pragmatach.framework.controller.impl.AbstractResponse;

import freemarker.template.Template;

/**
 * @author tome
 */
public class FreemarkerResponse extends AbstractResponse {
   /**
    * template
    */
   private final Template template;
   /**
    * context
    */
   private final Map<String, Object> context;

   public FreemarkerResponse(Map<String, String> cacheHeaders, Template template, Map<String, Object> context) {
      super(cacheHeaders);
      this.template = template;
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
         final Writer writer = new PrintWriter(outputStream);
         template.process(context, writer);
      } catch (final Exception e) {
         throw new PragmatachException("Exception in render", e);
      }
   }
}
