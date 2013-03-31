package com.khubla.pragmatach.plugin.cluster;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javassist.util.proxy.ProxyFactory;
import javassist.util.proxy.ProxyObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.khubla.pragmatach.framework.annotation.Controller;
import com.khubla.pragmatach.framework.api.PragmatachException;
import com.khubla.pragmatach.framework.controller.PragmatachController;
import com.khubla.pragmatach.framework.scanner.AnnotationScanner;
import com.khubla.pragmatach.plugin.cluster.annotation.Clustered;
import com.khubla.pragmatach.plugin.cluster.multicast.JGroupsSenderReceiver;

/**
 * A collection of the clustered controller instances
 * 
 * @author tome
 */
public class ClusteredControllers {
   /**
    * logger
    */
   private final Logger logger = LoggerFactory.getLogger(this.getClass());
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
    * cluster object
    */
   private final JGroupsSenderReceiver jGroupsSenderReceiver;
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
      /*
       * create the controllerCluster
       */
      jGroupsSenderReceiver = new JGroupsSenderReceiver();
      try {
         jGroupsSenderReceiver.startup();
      } catch (final Exception e) {
         logger.error("Exception starting ClusteredControllers", e);
      }
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
    * create the instances and return proxies to them
    */
   private void instantiateControllers() throws PragmatachException {
      try {
         for (final Class<?> controllerClazz : clusteredControllerClasses) {
            final ProxyFactory proxyFactory = new ProxyFactory();
            proxyFactory.setSuperclass(controllerClazz);
            final Class<?> c = proxyFactory.createClass();
            final PragmatachController pragmatachController = (PragmatachController) c.newInstance();
            ((ProxyObject) pragmatachController).setHandler(new ControllerMethodHandler());
            final String name = getClusteredControllerName(controllerClazz);
            controllerInstances.put(name, pragmatachController);
         }
      } catch (final Exception e) {
         throw new PragmatachException("Exception in instantiateControllers", e);
      }
   }
}
