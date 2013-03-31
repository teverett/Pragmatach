package test.com.khubla.pragmatach.plugin.openjpa;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.khubla.pragmatach.framework.application.Application;
import com.khubla.pragmatach.framework.configuration.HashmapConfigurationImpl;
import com.khubla.pragmatach.framework.scanner.AnnotationScanner;

/**
 * @author tome
 */
public class TestOpenJPAPersistence {
   /**
    * setup
    */
   @BeforeTest
   public void setup() {
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
       * create a config object
       */
      final HashmapConfigurationImpl configuration = new HashmapConfigurationImpl();
      configuration.setParameter("openjpa.ConnectionDriverName", "org.hsqldb.jdbcDriver");
      configuration.setParameter("openjpa.ConnectionURL", "jdbc:hsqldb:mem:testdb");
      configuration.setParameter("openjpa.ConnectionUserName", "sa");
      configuration.setParameter("openjpa.ConnectionPassword", "");
      configuration.setParameter("openjpa.jdbc.SynchronizeMappings", "buildSchema(ForeignKeys=true)");
      /*
       * set the application config
       */
      Application.setConfiguration(configuration);
   }

   @Test(enabled = true)
   public void testBasicFunctionality() {
      try {
         /*
          * create and save an object
          */
         final ExamplePOJO examplePOJO1 = new ExamplePOJO();
         examplePOJO1.setName("abc123");
         ExamplePOJO.dao.save(examplePOJO1);
         /*
          * check that the id was generated
          */
         Assert.assertNotNull(examplePOJO1.getId());
         /*
          * get the created object
          */
         ExamplePOJO retrievedPojo = ExamplePOJO.dao.findById(examplePOJO1.getId());
         Assert.assertNotNull(retrievedPojo);
         Assert.assertNotNull(retrievedPojo.getId());
         Assert.assertTrue(retrievedPojo.getId().longValue() == examplePOJO1.getId().longValue());
         Assert.assertTrue(retrievedPojo.getName().compareTo(examplePOJO1.getName()) == 0);
         /*
          * there is 1 object in the DB table
          */
         List<ExamplePOJO> allObjects = ExamplePOJO.dao.findAll();
         Assert.assertNotNull(allObjects);
         Assert.assertTrue(allObjects.size() == 1);
         /*
          * cant fetch object with wrong id
          */
         final ExamplePOJO noPojo = ExamplePOJO.dao.findById(new Long(909090));
         Assert.assertNull(noPojo);
         /*
          * delete it
          */
         ExamplePOJO.dao.delete(examplePOJO1);
         /*
          * make sure it's gone
          */
         retrievedPojo = ExamplePOJO.dao.findById(examplePOJO1.getId());
         Assert.assertNull(retrievedPojo);
         /*
          * no objects
          */
         allObjects = ExamplePOJO.dao.findAll();
         Assert.assertNotNull(allObjects);
         Assert.assertTrue(allObjects.size() == 0);
      } catch (final Exception e) {
         e.printStackTrace();
         Assert.fail();
      }
   }
}
