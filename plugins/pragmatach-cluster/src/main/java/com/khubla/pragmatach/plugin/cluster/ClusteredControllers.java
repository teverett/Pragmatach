package com.khubla.pragmatach.plugin.cluster;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import com.khubla.pragmatach.framework.annotation.Controller;
import com.khubla.pragmatach.framework.api.PragmatachException;
import com.khubla.pragmatach.framework.controller.PragmatachController;
import com.khubla.pragmatach.framework.scanner.AnnotationScanner;
import com.khubla.pragmatach.plugin.cluster.annotation.Clustered;

/**
 * A collection of the clustered controller instances
 * 
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
    * controllers
    */
   private final Map<String, PragmatachController> controllerInstances = new HashMap<String, PragmatachController>();
   /**
    * clustered beans
    */
   private final Set<Class<?>> clusteredControllerClasses;

   /**
    * ctor
    */
   private ClusteredControllers() throws PragmatachException {
      clusteredControllerClasses = findClusteredControllerClasses();
      instantiateControllers();
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

   private String getClusteredControllerName(Class<?> clazz) throws PragmatachException {
      try {
         final Controller controller = clazz.getAnnotation(Controller.class);
         if (null != controller) {
            return controller.name();
         } else {
            throw new Exception("ClusteredController '" + clazz.getName() + "' does not have a @Controller annotation");
         }
      } catch (final Exception e) {
         throw new PragmatachException("Exception in getClusteredControllerName", e);
      }
   }

   public Map<String, PragmatachController> getControllerInstances() {
      return controllerInstances;
   }

   /**
    * create the instances
    */
   private void instantiateControllers() throws PragmatachException {
      try {
         for (final Class<?> controllerClazz : clusteredControllerClasses) {
            final PragmatachController pragmatachController = (PragmatachController) controllerClazz.newInstance();
            final String name = getClusteredControllerName(controllerClazz);
            controllerInstances.put(name, pragmatachController);
         }
      } catch (final Exception e) {
         throw new PragmatachException("Exception in instantiateControllers", e);
      }
   }
}
