package com.khubla.pragmatach.framework.plugin;

import java.io.InputStream;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletContext;

import org.apache.log4j.Logger;
import org.scannotation.ClasspathUrlFinder;
import org.scannotation.WarUrlFinder;
import org.scannotation.archiveiterator.IteratorFactory;
import org.scannotation.archiveiterator.StreamIterator;

import com.khubla.pragmatach.framework.api.PragmatachException;

/**
 * @author tome
 */
public class PluginDescriptors {
   /**
    * logger
    */
   private static Logger logger = Logger.getLogger(PluginDescriptors.class);
   /**
    * plugin jars
    */
   private static Map<String, PluginDescriptor> plugins = new HashMap<String, PluginDescriptor>();

   public static Map<String, PluginDescriptor> getPlugins() {
      return plugins;
   }

   /**
    * do the scan
    */
   public static void scan(ServletContext servletContext) throws PragmatachException {
      scanURLs(ClasspathUrlFinder.findClassPaths());
      scanURLs(WarUrlFinder.findWebInfLibClasspaths(servletContext));
      scanURLs(new URL[] { WarUrlFinder.findWebInfClassesPath(servletContext) });
   }

   private static void scanURLs(URL[] urls) throws PragmatachException {
      try {
         final PluginFilter pluginFilter = new PluginFilter();
         for (final URL url : urls) {
            final StreamIterator streamIterator = IteratorFactory.create(url, pluginFilter);
            InputStream inputStream;
            while (null != (inputStream = streamIterator.next())) {
               final PluginDescriptor plugin = new PluginDescriptor(url, inputStream);
               logger.info("Found plugin: " + plugin.getName());
               plugins.put(plugin.getName(), plugin);
               inputStream.close();
            }
         }
      } catch (final Exception e) {
         throw new PragmatachException("Exception in scanURLs", e);
      }
   }
}
