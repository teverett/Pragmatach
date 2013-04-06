package test.com.khubla.pragmatach.plugin.json;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.testng.Assert;
import org.testng.annotations.Test;

import test.com.khubla.pragmatach.plugin.json.controller.CollectionController;
import test.com.khubla.pragmatach.plugin.json.controller.SimpleController;

import com.khubla.pragmatach.plugin.json.test.ControllerTestSupport;

/**
 * @author tome
 */
public class TestPragmatachJSONControllerSerialization {
   @Test(enabled = true)
   public void testCollectionController() {
      try {
         /*
          * test object
          */
         final CollectionController collectionController = new CollectionController();
         final List<String> strList = new ArrayList<String>();
         strList.add("a");
         strList.add("b");
         collectionController.setStringList(strList);
         final Set<String> strSet = new HashSet<String>();
         strSet.add("c");
         strSet.add("d");
         strSet.add("e");
         collectionController.setStringSet(strSet);
         /*
          * make json
          */
         final String json = ControllerTestSupport.performRender(collectionController);
         Assert.assertNotNull(json);
         Assert.assertTrue(json.length() > 0);
         /*
          * get it back
          */
         final CollectionController collectionController2 = new CollectionController();
         collectionController2.populateController(json);
         Assert.assertNotNull(collectionController2.getStringList());
         Assert.assertTrue(collectionController2.getStringList().size() == 2);
         Assert.assertNotNull(collectionController2.getStringSet());
         Assert.assertTrue(collectionController2.getStringSet().size() == 3);
      } catch (final Exception e) {
         e.printStackTrace();
         Assert.fail();
      }
   }

   @Test(enabled = true)
   public void testSimpleController() {
      try {
         /*
          * test object
          */
         final SimpleController simpleController = new SimpleController();
         simpleController.setL1(45);
         simpleController.setStr1("abc1234");
         /*
          * make json
          */
         final String json = ControllerTestSupport.performRender(simpleController);
         Assert.assertNotNull(json);
         Assert.assertTrue(json.length() > 0);
         /*
          * get it back
          */
         final SimpleController exampleController2 = new SimpleController();
         exampleController2.populateController(json);
         /*
          * check
          */
         Assert.assertTrue(simpleController.getStr1().compareTo(exampleController2.getStr1()) == 0);
         Assert.assertTrue(simpleController.getL1() == exampleController2.getL1());
      } catch (final Exception e) {
         e.printStackTrace();
         Assert.fail();
      }
   }
}
