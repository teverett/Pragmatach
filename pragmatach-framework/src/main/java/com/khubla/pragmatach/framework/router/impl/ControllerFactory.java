package com.khubla.pragmatach.framework.router.impl;

import java.net.URL;
import java.util.Hashtable;
import java.util.Set;

import javax.servlet.ServletContext;

import org.scannotation.AnnotationDB;
import org.scannotation.ClasspathUrlFinder;
import org.scannotation.WarUrlFinder;

import com.khubla.pragmatach.framework.api.PragmatachException;
import com.khubla.pragmatach.framework.freemarker.FreemarkerTemplate;

/**
 * @author tome
 */
public class ControllerFactory {
   /**
    * instance
    */
   private static ControllerFactory instance = null;
   /**
    * servlet context
    */
   private static ServletContext servletContext;

   /**
    * getter
    */
   public static ControllerFactory getInstance() {
      if (null == instance) {
         instance = new ControllerFactory();
      }
      return instance;
   }

   public static ServletContext getServletContext() {
      return servletContext;
   }

   public static void setServletContext(ServletContext servletContext) {
      ControllerFactory.servletContext = servletContext;
   }

   /**
    * controllers
    */
   private final Hashtable<String, Class<?>> controllers = new Hashtable<String, Class<?>>();

   /**
    * ctor
    */
   private ControllerFactory() {
      try {
         scanClassPath();
         scanWar();
      } catch (final Exception e) {
         e.printStackTrace();
      }
   }

   /**
    * get controller class by name
    */
   public Class<?> getControllerClass(String name) throws PragmatachException {
      try {
         return controllers.get(name);
      } catch (final Exception e) {
         throw new PragmatachException("Exception in getControllerClass", e);
      }
   }

   private void scanClassPath() throws PragmatachException {
      try {
         final URL[] urls = ClasspathUrlFinder.findClassPaths();
         final AnnotationDB annotationDB = new AnnotationDB();
         annotationDB.scanArchives(urls);
         final Set<String> entityClasses = annotationDB.getAnnotationIndex().get(FreemarkerTemplate.class.getName());
         if (null != entityClasses) {
            for (final String name : entityClasses) {
               final Class<?> clazz = Class.forName(name);
               controllers.put(name, clazz);
            }
         }
      } catch (final Exception e) {
         throw new PragmatachException("Exception in scanClassPath", e);
      }
   }

   private void scanWar() throws PragmatachException {
      try {
         if (null != servletContext) {
            final URL[] urls = WarUrlFinder.findWebInfLibClasspaths(servletContext);
            final AnnotationDB annotationDB = new AnnotationDB();
            annotationDB.scanArchives(urls);
            final Set<String> entityClasses = annotationDB.getAnnotationIndex().get(FreemarkerTemplate.class.getName());
            if (null != entityClasses) {
               for (final String name : entityClasses) {
                  final Class<?> clazz = Class.forName(name);
                  controllers.put(name, clazz);
               }
            }
         }
      } catch (final Exception e) {
         throw new PragmatachException("Exception in scanClassPath", e);
      }
   }
}
