package com.khubla.pragmatach.plugin.cluster;

import java.util.Map;

import org.apache.log4j.Logger;

import com.khubla.pragmatach.framework.api.PragmatachException;
import com.khubla.pragmatach.framework.controller.PragmatachController;
import com.khubla.pragmatach.plugin.cluster.serialization.ControllerSerializer;
import com.khubla.pragmatach.plugin.cluster.thread.PeriodicThread;

/**
 * @author tome
 */
public class ClusterNotifierThread extends PeriodicThread {
   /**
    * logger
    */
   private final Logger logger = Logger.getLogger(this.getClass());
   /**
    * cluster object
    */
   private final ControllerCluster controllerCluster;

   /**
    * ctor
    */
   public ClusterNotifierThread(long interval) {
      super(interval);
      /*
       * create the controllerCluster
       */
      controllerCluster = new ControllerCluster();
      try {
         controllerCluster.startup();
      } catch (final Exception e) {
         logger.error("Exception starting ControllerCluster", e);
      }
   }

   @Override
   public void exec() throws PragmatachException {
      try {
         /*
          * walk the objects, syncing them
          */
         final Map<String, PragmatachController> controllerInstances = ClusteredControllers.getInstance().getControllerInstances();
         if (null != controllerInstances) {
            /*
             * walk clustered controllers
             */
            for (final String controllerName : controllerInstances.keySet()) {
               /*
                * sync a single controller
                */
               sync(controllerInstances.get(controllerName));
            }
         }
      } catch (final Exception e) {
         throw new PragmatachException("Exception in exec", e);
      }
   }

   /**
    * sync a single controller
    */
   private void sync(PragmatachController pragmatachController) throws PragmatachException {
      try {
         /*
          * serialize the controller
          */
         final String controllerString = ControllerSerializer.serialize(pragmatachController);
         /*
          * send
          */
         controllerCluster.send(controllerString);
      } catch (final Exception e) {
         throw new PragmatachException("Exception in exec", e);
      }
   }
}
