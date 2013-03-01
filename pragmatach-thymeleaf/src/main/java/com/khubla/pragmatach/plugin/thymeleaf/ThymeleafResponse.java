package com.khubla.pragmatach.plugin.thymeleaf;

import java.io.ByteArrayInputStream;
import java.io.OutputStream;
import java.util.Map;

import org.apache.commons.io.IOUtils;
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
         final TemplateEngine templateEngine = new TemplateEngine();
         templateEngine.setTemplateResolver(templateResolver);
         final Context ctx = new Context();
         ctx.setVariables(context);
         String result = templateEngine.process(templateName, ctx);
         IOUtils.copy(new ByteArrayInputStream(result.getBytes()), outputStream);
      } catch (final Exception e) {
         throw new PragmatachException("Exception in render", e);
      }
   }
}
