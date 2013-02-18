package com.khubla.pragmatach.framework.resourceloader;

import java.io.InputStream;

import javax.servlet.ServletContext;

import org.testng.log4testng.Logger;

import com.khubla.pragmatach.framework.api.PragmatachException;

/**
 * @author tome
 */
public class ResourceLoader {
   /**
    * logger
    */
   private final Logger logger = Logger.getLogger(this.getClass());
   /**
    * servlet context
    */
   private final ServletContext servletContext;

   /**
    * ctor
    */
   public ResourceLoader(ServletContext servletContext) {
      this.servletContext = servletContext;
   }

   /**
    * get a resource using the servlet's class loader
    */
   public InputStream getResource(String resource) throws PragmatachException {
      try {
         if ((resource.contains("WEB-INF")) || (resource.contains("META-INF")) || (resource.startsWith("."))) {
            return null;
         } else {
            String resourcePath = resource;
            if (false == resourcePath.startsWith("/")) {
               resourcePath = "/" + resourcePath;
            }
            /*
             * try loading from the web app
             */
            InputStream ret = servletContext.getResourceAsStream(resourcePath);
            /*
             * try loading locally
             */
            if (null == ret) {
               ret = ResourceLoader.class.getResourceAsStream(resourcePath);
            }
            /*
             * unable to find, log that
             */
            if (null == ret) {
               logger.info("Unable to load resource '" + resource + "'");
            }
            /*
             * done
             */
            return ret;
         }
      } catch (final Exception e) {
         throw new PragmatachException("Exception in getResource", e);
      }
   }
}
