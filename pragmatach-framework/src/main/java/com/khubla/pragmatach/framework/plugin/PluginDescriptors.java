package com.khubla.pragmatach.framework.plugin;

import java.io.InputStream;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletContext;

import org.scannotation.ClasspathUrlFinder;
import org.scannotation.WarUrlFinder;
import org.scannotation.archiveiterator.IteratorFactory;
import org.scannotation.archiveiterator.StreamIterator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.khubla.pragmatach.framework.api.PragmatachException;

/**
 * @author tome
 */
public class PluginDescriptors {
   public static Map<String, PluginDescriptor> getPlugins() {
      return plugins;
   }

   /**
    * do the scan
    */
   public static void scan(ServletContext servletContext) throws PragmatachException {
      scanURLs(ClasspathUrlFinder.findClassPaths(), servletContext);
      scanURLs(WarUrlFinder.findWebInfLibClasspaths(servletContext), servletContext);
      scanURLs(new URL[] { WarUrlFinder.findWebInfClassesPath(servletContext) }, servletContext);
   }

   private static void scanURLs(URL[] urls, ServletContext servletContext) throws PragmatachException {
      try {
         if (null != servletContext) {
            final PluginFilter pluginFilter = new PluginFilter();
            if ((null != urls) && (urls.length > 0)) {
               for (final URL url : urls) {
                  if (null != url) {
                     final StreamIterator streamIterator = IteratorFactory.create(url, pluginFilter);
                     InputStream inputStream;
                     while (null != (inputStream = streamIterator.next())) {
                        final PluginDescriptor plugin = new PluginDescriptor(url, inputStream, servletContext);
                        logger.info("Found plugin: " + plugin.getName());
                        plugins.put(plugin.getName(), plugin);
                        inputStream.close();
                     }
                  }
               }
            }
         }
      } catch (final Exception e) {
         throw new PragmatachException("Exception in scanURLs", e);
      }
   }

   /**
    * logger
    */
   private static final Logger logger = LoggerFactory.getLogger(PluginDescriptors.class);
   /**
    * plugin jars
    */
   private static Map<String, PluginDescriptor> plugins = new HashMap<String, PluginDescriptor>();
}
