package com.khubla.pragmatach.framework.freemarker;

import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.Hashtable;

import com.khubla.pragmatach.framework.api.PragmatachException;
import com.khubla.pragmatach.framework.api.Response;

import freemarker.template.Template;

/**
 * @author tome
 */
public class FreemarkerResponse implements Response {
   /**
    * template
    */
   private final Template template;
   /**
    * context
    */
   private final Hashtable<String, String> context;

   public FreemarkerResponse(Template template, Hashtable<String, String> context) {
      this.template = template;
      this.context = context;
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
