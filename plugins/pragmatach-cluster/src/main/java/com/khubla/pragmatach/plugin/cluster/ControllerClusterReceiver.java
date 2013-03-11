package com.khubla.pragmatach.plugin.cluster;

import org.apache.log4j.Logger;
import org.jgroups.Message;
import org.jgroups.ReceiverAdapter;

import com.khubla.pragmatach.framework.annotation.View;

/**
 * This class receives broadcasts from other Pragmatach instances
 * 
 * @author tome
 */
public class ControllerClusterReceiver extends ReceiverAdapter {
   /**
    * logger
    */
   private final Logger logger = Logger.getLogger(this.getClass());

   /**
    * message
    */
   public void receive(Message msg) {
      try {
         // String json = ControllerSerializer.deserialize(msg.toString());
      } catch (final Exception e) {
         logger.error("Exception in receive", e);
      }
   }

   /**
    * accepted
    */
   public void viewAccepted(View new_view) {
      System.out.println("** view: " + new_view);
   }
}
