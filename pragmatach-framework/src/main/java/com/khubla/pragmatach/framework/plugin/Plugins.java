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
import org.testng.log4testng.Logger;

import com.khubla.pragmatach.framework.api.PragmatachException;

/**
 * @author tome
 */
public class Plugins {
   /**
    * logger
    */
   private static Logger logger = Logger.getLogger(Plugins.class);
   /**
    * plugin jars
    */
   private static Map<String, Plugin> plugins = new HashMap<String, Plugin>();

   public static Map<String, Plugin> getPlugins() {
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
               final Plugin plugin = new Plugin(url, inputStream);
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
