package com.khubla.pragmatach.plugin.cluster;

import java.util.Hashtable;
import java.util.Map;

import org.apache.log4j.Logger;

import com.khubla.pragmatach.framework.api.PragmatachException;
import com.khubla.pragmatach.framework.controller.AbstractController;
import com.khubla.pragmatach.framework.controller.PragmatachController;
import com.khubla.pragmatach.plugin.cluster.multicast.JGroupsSenderReceiver;
import com.khubla.pragmatach.plugin.cluster.serialization.GenericJSONSerializer;
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
   private final JGroupsSenderReceiver jGroupsSenderReceiver;
   /**
    * checksums
    */
   private Hashtable<String, String> hashes = new Hashtable<String, String>();

   /**
    * ctor
    */
   public ClusterNotifierThread(long interval) {
      super(interval);
      /*
       * create the controllerCluster
       */
      jGroupsSenderReceiver = new JGroupsSenderReceiver();
      try {
         jGroupsSenderReceiver.startup();
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
          * name
          */
         String controllerName = AbstractController.getControllerName(pragmatachController);
         if (null != controllerName) {
            /*
             * serialize the controller
             */
            final String controllerString = new GenericJSONSerializer<PragmatachController>().serialize(pragmatachController);
            /*
             * check hash
             */
            if (hashes.containsKey(controllerName)) {
            } else {
            }
            /*
             * send
             */
            // jGroupsSenderReceiver.send(controllerString);
         } else {
            throw new PragmatachException("Could not get Controller name for '" + pragmatachController.getClass().getName() + "'");
         }
      } catch (final Exception e) {
         throw new PragmatachException("Exception in exec", e);
      }
   }
}
