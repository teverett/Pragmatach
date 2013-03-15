package test.com.khubla.pragmatach.plugin.cluster.serialization;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.khubla.pragmatach.framework.controller.PragmatachController;
import com.khubla.pragmatach.plugin.cluster.serialization.GenericJSONSerializer;

/**
 * @author tome
 */
public class TestControllerSerialization {
   @Test
   public void test1() {
      try {
         final GenericJSONSerializer<PragmatachController> jsonSerializer = new GenericJSONSerializer<PragmatachController>();
         final TestController testController1 = new TestController();
         testController1.setTestString("33333");
         final String s = jsonSerializer.serialize(testController1);
         Assert.assertNotNull(s);
         TestController testController2 = new TestController();
         testController2 = (TestController) jsonSerializer.deserialize(testController2, s);
         Assert.assertNotNull(s);
         Assert.assertTrue(testController1.getTestString().compareTo(testController2.getTestString()) == 0);
      } catch (final Exception e) {
         e.printStackTrace();
         Assert.fail();
      }
   }
}
