package com.khubla.pragmatach.plugin.thymeleaf;

import java.io.InputStream;

import javax.servlet.ServletContext;

import org.thymeleaf.TemplateProcessingParameters;
import org.thymeleaf.resourceresolver.IResourceResolver;

import com.khubla.pragmatach.framework.resourceloader.ResourceLoader;

/**
 * @author tome
 */
public class PragmatachResourceResolver implements IResourceResolver {
   /**
    * ServletContext
    */
   private final ServletContext servletContext;

   public PragmatachResourceResolver(ServletContext servletContext) {
      this.servletContext = servletContext;
   }

   @Override
   public String getName() {
      return this.getClass().getSimpleName();
   }

   @Override
   public InputStream getResourceAsStream(TemplateProcessingParameters templateProcessingParameters, String resourceName) {
      try {
         final ResourceLoader resourceLoader = new ResourceLoader(servletContext);
         final InputStream is = resourceLoader.getResource(resourceName);
         return is;
      } catch (final Exception e) {
         throw new RuntimeException("Cannot open resource '" + resourceName + "'", e);
      }
   }
}
