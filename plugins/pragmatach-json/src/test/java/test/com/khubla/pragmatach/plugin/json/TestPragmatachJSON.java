package test.com.khubla.pragmatach.plugin.json;

import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.khubla.pragmatach.plugin.json.PragmatachJSON;

/**
 * @author tome
 */
public class TestPragmatachJSON {
   @Test
   public void test1() {
      try {
         /*
          * test object
          */
         ExampleController exampleController = new ExampleController();
         exampleController.setL1(45);
         exampleController.setStr1("abc1234");
         /*
          * make json
          */
         String json = PragmatachJSON.toJSON(exampleController);
         Assert.assertNotNull(json);
         /*
          * parse
          */
         Map<String, String> jsonValues = PragmatachJSON.parseJSON(json);
         Assert.assertTrue(jsonValues.size() == 2);
         /*
          * get it back
          */
         ExampleController exampleController2 = new ExampleController();
         PragmatachJSON.fromJSON(exampleController2, json);
         /*
          * check
          */
         Assert.assertTrue(exampleController.getStr1().compareTo(exampleController2.getStr1()) == 0);
         Assert.assertTrue(exampleController.getL1() == exampleController2.getL1());
      } catch (Exception e) {
         e.printStackTrace();
         Assert.fail();
      }
   }
}
