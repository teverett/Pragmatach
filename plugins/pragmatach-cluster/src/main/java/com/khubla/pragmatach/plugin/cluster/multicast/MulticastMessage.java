package com.khubla.pragmatach.plugin.cluster.multicast;

import com.khubla.pragmatach.framework.api.PragmatachException;
import com.khubla.pragmatach.plugin.cluster.serialization.GenericJSONSerializer;

/**
 * @author tome
 */
public class MulticastMessage {
   /**
    * types of message
    */
   public final static int MESSAGETYPE_HELLO = 1;
   public final static int MESSAGETYPE_GBYE = 2;
   public final static int MESSAGETYPE_SOLICITUPDATE = 3;
   public final static int MESSAGETYPE_UPDATE = 4;

   /**
    * from string
    */
   public static MulticastMessage deserialize(byte[] messageBody) throws PragmatachException {
      final MulticastMessage multicastMessage = new MulticastMessage();
      return new GenericJSONSerializer<MulticastMessage>().deserialize(multicastMessage, new String(messageBody));
   }

   /**
    * to String
    */
   public static byte[] serialize(MulticastMessage multicastMessage) throws PragmatachException {
      return new GenericJSONSerializer<MulticastMessage>().serialize(multicastMessage).getBytes();
   }

   /**
    * type
    */
   private int messageType;
   /**
    * content
    */
   private String messageContent;

   /**
    * ctor
    */
   public MulticastMessage() {
   }

   /**
    * ctor
    */
   public MulticastMessage(int messageType, String messageContent) {
      this.messageContent = messageContent;
      this.messageType = messageType;
   }

   public String getMessageContent() {
      return messageContent;
   }

   public int getMessageType() {
      return messageType;
   }

   public void setMessageContent(String messageContent) {
      this.messageContent = messageContent;
   }

   public void setMessageType(int messageType) {
      this.messageType = messageType;
   }
}
