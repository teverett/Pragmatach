package test.com.khubla.pragmatach.plugin.mongodb;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.khubla.pragmatach.framework.api.Configuration;
import com.khubla.pragmatach.framework.application.Application;
import com.khubla.pragmatach.framework.configuration.HashmapConfigurationImpl;
import com.khubla.pragmatach.framework.dao.AbstractDAO;
import com.khubla.pragmatach.framework.scanner.AnnotationScanner;
import com.khubla.pragmatach.plugin.mongodb.MongoDBDAO;

/**
 * @author tome
 */
public class TestMongoDBPersistence {
   public Configuration getConfiguration() {
      final HashmapConfigurationImpl mongoConfiguration = new HashmapConfigurationImpl();
      /*
       * mongo
       */
      mongoConfiguration.setParameter("mongodb.Hostname", "192.168.77.79");
      mongoConfiguration.setParameter("mongodb.Database", "test");
      mongoConfiguration.setParameter("mongodb.ConnectionUserName", "");
      mongoConfiguration.setParameter("mongodb.ConnectionPassword", "");
      mongoConfiguration.setParameter("mongodb.DropCollection", "true");
      /*
       * done
       */
      return mongoConfiguration;
   }

   /**
    * for the class
    */
   @BeforeClass
   public void setupClass() {
      /*
       * run the scanner
       */
      try {
         AnnotationScanner.scan(null);
      } catch (final Exception e) {
         e.printStackTrace();
         Assert.fail();
      }
      /*
       * set config
       */
      Application.setConfiguration(getConfiguration());
   }

   @Test(enabled = true)
   public void testBasicFunctionality() {
      try {
         /*
          * DAO
          */
         final AbstractDAO<ExamplePOJO1, String> dao = new MongoDBDAO<ExamplePOJO1>(ExamplePOJO1.class);
         /*
          * empty table
          */
         Assert.assertTrue(dao.count() == 0);
         /*
          * create and save an object
          */
         final ExamplePOJO1 examplePOJO1 = new ExamplePOJO1();
         examplePOJO1.setName("abc123");
         examplePOJO1.setDoubleNumber(12.2);
         examplePOJO1.setIntNumber(34);
         dao.save(examplePOJO1);
         /*
          * 1 row
          */
         Assert.assertTrue(dao.count() == 1);
         /*
          * check that the id was generated
          */
         Assert.assertNotNull(examplePOJO1.getId());
         /*
          * get the created object
          */
         ExamplePOJO1 retrievedPojo = dao.findById(examplePOJO1.getId());
         Assert.assertNotNull(retrievedPojo);
         Assert.assertNotNull(retrievedPojo.getId());
         Assert.assertTrue(retrievedPojo.getId().compareTo(examplePOJO1.getId()) == 0);
         Assert.assertTrue(retrievedPojo.getName().compareTo(examplePOJO1.getName()) == 0);
         Assert.assertTrue(retrievedPojo.getDoubleNumber() == examplePOJO1.getDoubleNumber());
         Assert.assertTrue(retrievedPojo.getIntNumber() == examplePOJO1.getIntNumber());
         /*
          * there is 1 object in the DB table
          */
         List<ExamplePOJO1> allObjects = dao.getAll();
         Assert.assertNotNull(allObjects);
         Assert.assertTrue(allObjects.size() == 1, "found '" + allObjects.size() + "' objects.");
         /*
          * cant fetch object with wrong id
          */
         final ExamplePOJO1 noPojo = dao.findById("badid");
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
          * count is zero
          */
         Assert.assertTrue(dao.count() == 0);
         /*
          * no objects
          */
         allObjects = dao.getAll();
         Assert.assertNotNull(allObjects);
         Assert.assertTrue(allObjects.size() == 0);
      } catch (final Exception e) {
         e.printStackTrace();
         Assert.fail();
      }
   }
}
