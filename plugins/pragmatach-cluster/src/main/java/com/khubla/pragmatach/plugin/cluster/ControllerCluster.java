package com.khubla.pragmatach.plugin.cluster;

import org.jgroups.JChannel;

import com.khubla.pragmatach.framework.api.PragmatachException;

/**
 * @author tome
 */
public class ControllerCluster {
   /**
    * the receiver
    */
   private final ControllerClusterReceiver controllerClusterReceiver = new ControllerClusterReceiver();
   /**
    * the channel
    */
   private JChannel jChannel;
   /**
    * cluster name
    */
   private final static String CLUSTER_NAME = "PragmatachSesion";
   /**
    * config file
    */
   private static final String PROPERTIES_FILE = "controllerCluster.xml";

   public ControllerCluster() {
   }

   public void shutdown() {
      if (null != jChannel) {
         jChannel.close();
         jChannel = null;
      }
   }

   public void startup() throws PragmatachException {
      try {
         /*
          * the channel
          */
         jChannel = new JChannel(PROPERTIES_FILE);
         jChannel.setReceiver(controllerClusterReceiver);
         jChannel.connect(CLUSTER_NAME);
      } catch (final Exception e) {
         throw new PragmatachException("Exception in startup", e);
      }
   }
}
