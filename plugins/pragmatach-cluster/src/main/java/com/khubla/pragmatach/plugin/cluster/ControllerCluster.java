package com.khubla.pragmatach.plugin.cluster;

import org.jgroups.JChannel;
import org.jgroups.Message;

import com.khubla.pragmatach.framework.api.PragmatachException;

/**
 * The broadcast sender and reciever
 * 
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

   public ControllerCluster() {
   }

   /**
    * send message
    */
   public void send(String message) throws PragmatachException {
      try {
         final Message msg = new Message();
         msg.setBuffer(message.getBytes());
         jChannel.send(msg);
      } catch (final Exception e) {
         throw new PragmatachException("Exception in send", e);
      }
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
         jChannel = new JChannel();
         jChannel.setReceiver(controllerClusterReceiver);
         jChannel.connect(CLUSTER_NAME);
      } catch (final Exception e) {
         throw new PragmatachException("Exception in startup", e);
      }
   }
}
