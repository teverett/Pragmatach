package com.khubla.pragmatach.plugin.velocity;

import java.io.ByteArrayInputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.Writer;
import java.util.Map;

import org.apache.commons.io.IOUtils;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;

import com.khubla.pragmatach.framework.api.PragmatachException;
import com.khubla.pragmatach.framework.controller.AbstractResponse;

/**
 * @author tome
 */
public class VelocityResponse extends AbstractResponse {
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
   /**
    * velocity
    */
   private final VelocityEngine velocityEngine = new VelocityEngine();

   public VelocityResponse(Map<String, String> cacheHeaders, String templateName, String template, Map<String, Object> context) {
      super(cacheHeaders);
      this.template = template;
      this.context = context;
      this.templateName = templateName;
   }

   @Override
   public Map<String, String> getHeaders() throws PragmatachException {
      return super.getCacheHeaders();
   }

   @Override
   public void render(OutputStream outputStream) throws PragmatachException {
      try {
         final VelocityContext velocityContext = new VelocityContext(context);
         final Reader reader = new StringReader(template);
         final Writer writer = new StringWriter();
         velocityEngine.evaluate(velocityContext, writer, templateName, reader);
         final ByteArrayInputStream bais = new ByteArrayInputStream(writer.toString().getBytes());
         IOUtils.copy(bais, outputStream);
      } catch (final Exception e) {
         throw new PragmatachException("Exception in render", e);
      }
   }
}
