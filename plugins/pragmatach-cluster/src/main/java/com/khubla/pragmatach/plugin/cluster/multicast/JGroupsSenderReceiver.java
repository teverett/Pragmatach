package com.khubla.pragmatach.plugin.cluster.multicast;

import org.jgroups.JChannel;
import org.jgroups.Message;
import org.jgroups.ReceiverAdapter;
import org.jgroups.View;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.khubla.pragmatach.framework.api.PragmatachException;

/**
 * The broadcast sender and reciever
 * 
 * @author tome
 */
public class JGroupsSenderReceiver extends ReceiverAdapter {
   /**
    * logger
    */
   private final Logger logger = LoggerFactory.getLogger(this.getClass());
   /**
    * the channel
    */
   private JChannel jChannel;
   /**
    * cluster name
    */
   private final static String CLUSTER_NAME = "PragmatachSesion";

   public JGroupsSenderReceiver() {
   }

   /**
    * message
    */
   @Override
   public void receive(Message message) {
      try {
         if (null != message) {
            byte[] buffer = message.getBuffer();
            if (null != buffer) {
               MulticastMessage.deserialize(message.getBuffer());
            }
         }
      } catch (final Exception e) {
         logger.error("Exception in receive", e);
      }
   }

   /**
    * send message
    */
   public void send(MulticastMessage multicastMessage) throws PragmatachException {
      try {
         final Message msg = new Message();
         msg.setBuffer(MulticastMessage.serialize(multicastMessage));
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
         jChannel.setReceiver(this);
         jChannel.connect(CLUSTER_NAME);
      } catch (final Exception e) {
         throw new PragmatachException("Exception in startup", e);
      }
   }

   /**
    * accepted
    */
   @Override
   public void viewAccepted(View view) {
   }
}
