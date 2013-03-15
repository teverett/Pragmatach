package test.com.khubla.pragmatach.plugin.cluster.multicast;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.khubla.pragmatach.plugin.cluster.multicast.MulticastMessage;

/**
 * @author tome
 */
public class TestMulticastMessage {
   @Test
   public void testSerialization() {
      try {
         final MulticastMessage multicastMessage = new MulticastMessage(MulticastMessage.MESSAGETYPE_HELLO, "hi there");
         final byte[] bytes = MulticastMessage.serialize(multicastMessage);
         Assert.assertNotNull(bytes);
         final MulticastMessage multicastMessage2 = MulticastMessage.deserialize(bytes);
         Assert.assertNotNull(multicastMessage2);
         Assert.assertTrue(multicastMessage2.getMessageType() == multicastMessage.getMessageType());
         Assert.assertTrue(multicastMessage2.getMessageContent().compareTo(multicastMessage.getMessageContent()) == 0);
      } catch (final Exception e) {
         e.printStackTrace();
         Assert.fail();
      }
   }
}
