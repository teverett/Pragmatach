package test.com.khubla.pragmatach.plugin.mongodb;

import java.util.HashSet;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.khubla.pragmatach.framework.api.Configuration;
import com.khubla.pragmatach.framework.application.Application;
import com.khubla.pragmatach.framework.configuration.HashmapConfigurationImpl;
import com.khubla.pragmatach.framework.dao.AbstractDAO;
import com.khubla.pragmatach.framework.scanner.AnnotationScanner;
import com.khubla.pragmatach.plugin.mongodb.MongoDBDAO;
import com.khubla.pragmatach.plugin.mongodb.db.DBCollectionFactory;

/**
 * @author tome
 */
public class TestMongoDBPersistence {
   @BeforeTest
   public void beforeTest() {
   }

   /**
    * get the test configuration
    */
   public Configuration getConfiguration() {
      final HashmapConfigurationImpl mongoConfiguration = new HashmapConfigurationImpl();
      /*
       * mongo
       */
      mongoConfiguration.setParameter("mongodb.Hostname", "192.168.77.79");
      mongoConfiguration.setParameter("mongodb.Database", "test");
      mongoConfiguration.setParameter("mongodb.ConnectionUserName", "");
      mongoConfiguration.setParameter("mongodb.ConnectionPassword", "");
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
          * drop the db
          */
         DBCollectionFactory.getInstance().dropDB();
         /*
          * DAO
          */
         final AbstractDAO<ExamplePOJO1, String> dao = new MongoDBDAO<ExamplePOJO1>(ExamplePOJO1.class);
         /*
          * empty table
          */
         final long count = dao.count();
         Assert.assertTrue(count == 0, "Expected 0 items, found " + count);
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

   @Test(enabled = true)
   public void testSetFunctionality() {
      try {
         /*
          * drop the db
          */
         DBCollectionFactory.getInstance().dropDB();
         /*
          * DAO
          */
         final AbstractDAO<ExamplePOJO2, String> pojo2DAO = new MongoDBDAO<ExamplePOJO2>(ExamplePOJO2.class);
         final AbstractDAO<ExamplePOJO1, String> pojo1DAO = new MongoDBDAO<ExamplePOJO1>(ExamplePOJO1.class);
         /*
          * empty table
          */
         Assert.assertTrue(pojo2DAO.count() == 0);
         Assert.assertTrue(pojo1DAO.count() == 0);
         /*
          * create and save an object
          */
         final ExamplePOJO2 examplePOJO2 = new ExamplePOJO2();
         examplePOJO2.setName("abc123");
         final HashSet<ExamplePOJO1> epo = new HashSet<ExamplePOJO1>();
         final ExamplePOJO1 ep1 = new ExamplePOJO1();
         ep1.setName("one");
         ep1.setDoubleNumber(44.0);
         ep1.setIntNumber(3);
         epo.add(ep1);
         final ExamplePOJO1 ep2 = new ExamplePOJO1();
         ep2.setName("two");
         ep2.setDoubleNumber(66666.9);
         ep2.setIntNumber(90);
         epo.add(ep2);
         examplePOJO2.setExamplePOJO1s(epo);
         pojo2DAO.save(examplePOJO2);
         /*
          * 1 row in POJO1
          */
         Assert.assertTrue(pojo2DAO.count() == 1);
         final List<ExamplePOJO1> allPOJO1s = pojo1DAO.getAll();
         Assert.assertNotNull(allPOJO1s);
         Assert.assertTrue(allPOJO1s.size() == 2);
         Assert.assertTrue(pojo1DAO.count() == 2, "Expected 2 POJO1 objects, there are :" + pojo1DAO.count());
         /*
          * check that the id was generated
          */
         Assert.assertNotNull(examplePOJO2.getId());
         for (final ExamplePOJO1 examplePOJO1 : examplePOJO2.getExamplePOJO1s()) {
            Assert.assertNotNull(examplePOJO1);
            Assert.assertNotNull(examplePOJO1.getId());
         }
         /*
          * get it back
          */
         final ExamplePOJO2 retrievedPojo = pojo2DAO.findById(examplePOJO2.getId());
         Assert.assertNotNull(retrievedPojo);
         /*
          * there are two contained objects
          */
         Assert.assertTrue(retrievedPojo.getExamplePOJO1s() != null);
         Assert.assertTrue(retrievedPojo.getExamplePOJO1s().size() == 2);
      } catch (final Exception e) {
         e.printStackTrace();
         Assert.fail();
      }
   }
}
