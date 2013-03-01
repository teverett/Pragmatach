package com.khubla.pragmatach.plugin.thymeleaf;

import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.Map;

import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.thymeleaf.templateresolver.ITemplateResolver;

import com.khubla.pragmatach.framework.api.PragmatachException;
import com.khubla.pragmatach.framework.controller.AbstractResponse;

/**
 * @author tome
 */
public class ThymeleafResponse extends AbstractResponse {
   /**
    * template
    */
   private final String templateName;
   /**
    * context
    */
   private final Map<String, Object> context;
   /**
    * resolver
    */
   private final ITemplateResolver templateResolver;

   public ThymeleafResponse(Map<String, String> cacheHeaders, String templateName, Map<String, Object> context, ITemplateResolver templateResolver) {
      super(cacheHeaders);
      this.templateName = templateName;
      this.context = context;
      this.templateResolver = templateResolver;
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
         final TemplateEngine templateEngine = new TemplateEngine();
         templateEngine.setTemplateResolver(templateResolver);
         final Context ctx = new Context();
         ctx.setVariables(context);
         templateEngine.process(templateName, ctx, writer);
      } catch (final Exception e) {
         throw new PragmatachException("Exception in render", e);
      }
   }
}
