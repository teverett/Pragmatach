package com.khubla.pragmatach.dbtestsuite;

import java.util.List;

import org.testng.Assert;

import com.khubla.pragmatach.dbtestsuite.pojo.ExamplePOJO;
import com.khubla.pragmatach.framework.api.DAO;

/**
 * @author tome
 */
public class BasicTests {
   public static void testBasicFunctionality(DAO<ExamplePOJO, Long> dao) {
      try {
         /*
          * create and save an object
          */
         final ExamplePOJO examplePOJO1 = new ExamplePOJO();
         examplePOJO1.setName("abc123");
         examplePOJO1.setDoubleNumber(12.2);
         examplePOJO1.setIntNumber(34);
         dao.save(examplePOJO1);
         /*
          * check that the id was generated
          */
         Assert.assertNotNull(examplePOJO1.getId());
         /*
          * get the created object
          */
         ExamplePOJO retrievedPojo = dao.findById(examplePOJO1.getId());
         Assert.assertNotNull(retrievedPojo);
         Assert.assertNotNull(retrievedPojo.getId());
         Assert.assertTrue(retrievedPojo.getId().longValue() == examplePOJO1.getId().longValue());
         Assert.assertTrue(retrievedPojo.getName().compareTo(examplePOJO1.getName()) == 0);
         Assert.assertTrue(retrievedPojo.getDoubleNumber() == examplePOJO1.getDoubleNumber());
         Assert.assertTrue(retrievedPojo.getIntNumber() == examplePOJO1.getIntNumber());
         /*
          * there is 1 object in the DB table
          */
         List<ExamplePOJO> allObjects = dao.findAll();
         Assert.assertNotNull(allObjects);
         Assert.assertTrue(allObjects.size() == 1, "found '" + allObjects.size() + "' objects.");
         /*
          * cant fetch object with wrong id
          */
         final ExamplePOJO noPojo = dao.findById(new Long(909090));
         Assert.assertNull(noPojo);
         /*
          * delete it
          */
         dao.delete(examplePOJO1);
         /*
          * make sure it's gone
          */
         retrievedPojo = dao.findById(examplePOJO1.getId());
         Assert.assertNull(retrievedPojo);
         /*
          * no objects
          */
         allObjects = dao.findAll();
         Assert.assertNotNull(allObjects);
         Assert.assertTrue(allObjects.size() == 0);
      } catch (final Exception e) {
         e.printStackTrace();
         Assert.fail();
      }
   }
}
