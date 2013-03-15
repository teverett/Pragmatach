package test.com.khubla.pragmatach.plugin.cluster.serialization;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.khubla.pragmatach.plugin.cluster.serialization.GenericJSONSerializer;

/**
 * @author tome
 */
public class TestGenericJSONSerializer {
   @Test
   public void test1() {
      try {
         final GenericJSONSerializer<TestObject> jsonSerializer = new GenericJSONSerializer<TestObject>();
         final TestObject testObject1 = new TestObject();
         testObject1.setI(12);
         testObject1.setS("dsdsdsd");
         final String s = jsonSerializer.serialize(testObject1);
         Assert.assertNotNull(s);
         TestObject testObject2 = new TestObject();
         testObject2 = jsonSerializer.deserialize(testObject2, s);
         Assert.assertNotNull(s);
         Assert.assertTrue(testObject1.getI() == testObject2.getI());
         Assert.assertTrue(testObject1.getS().compareTo(testObject2.getS()) == 0);
      } catch (final Exception e) {
         e.printStackTrace();
         Assert.fail();
      }
   }
}
