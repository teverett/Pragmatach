package test.com.khubla.pragmatach.plugin.hibernate;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.khubla.pragmatach.framework.application.Application;
import com.khubla.pragmatach.framework.configuration.HashmapConfigurationImpl;
import com.khubla.pragmatach.framework.scanner.AnnotationScanner;

/**
 * @author tome
 */
@Test
public class TestHibernatePersistence {
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
      configuration.setParameter("hibernate.driver", "org.hsqldb.jdbcDriver");
      configuration.setParameter("hibernate.dialect", "org.hibernate.dialect.HSQLDialect");
      configuration.setParameter("hibernate.connection.url", "jdbc:hsqldb:mem:testdb");
      configuration.setParameter("hibernate.connection.username", "sa");
      configuration.setParameter("hibernate.connection.password", "");
      /*
       * set the application config
       */
      Application.setConfiguration(configuration);
   }

   public void test1() {
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
          * delete it
          */
         ExamplePOJO.dao.delete(examplePOJO1);
         /*
          * make sure it's gone
          */
         retrievedPojo = ExamplePOJO.dao.findById(examplePOJO1.getId());
         Assert.assertNull(retrievedPojo);
      } catch (final Exception e) {
         e.printStackTrace();
         Assert.fail();
      }
   }
}
