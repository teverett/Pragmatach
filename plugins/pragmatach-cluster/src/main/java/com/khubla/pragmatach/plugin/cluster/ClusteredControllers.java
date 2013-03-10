package com.khubla.pragmatach.plugin.cluster;

import java.util.Set;

import com.khubla.pragmatach.framework.api.PragmatachException;
import com.khubla.pragmatach.framework.scanner.AnnotationScanner;
import com.khubla.pragmatach.plugin.cluster.annotation.Clustered;

/**
 * @author tome
 */
public class ClusteredControllers {
   /**
    * instance
    */
   private static ClusteredControllers instance;

   /**
    * singleton
    */
   public static ClusteredControllers getInstance() throws PragmatachException {
      if (null == instance) {
         instance = new ClusteredControllers();
      }
      return instance;
   }

   /**
    * clustered beans
    */
   private final Set<Class<?>> clusteredControllerClasses;

   /**
    * ctor
    */
   private ClusteredControllers() throws PragmatachException {
      clusteredControllerClasses = findClusteredControllerClasses();
   }

   /**
    * find all the beans annotated with @Clustered
    */
   private Set<Class<?>> findClusteredControllerClasses() throws PragmatachException {
      try {
         return AnnotationScanner.getAll(Clustered.class);
      } catch (final Exception e) {
         throw new PragmatachException("Exception in findClusteredControllerClasses", e);
      }
   }

   public Set<Class<?>> getClusteredControllerClasses() {
      return clusteredControllerClasses;
   }
}
