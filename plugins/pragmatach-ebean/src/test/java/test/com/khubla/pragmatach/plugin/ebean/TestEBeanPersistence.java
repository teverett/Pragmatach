package test.com.khubla.pragmatach.plugin.ebean;

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
public class TestEBeanPersistence {
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
      configuration.setParameter("ebean.driver", "org.hsqldb.jdbcDriver");
      configuration.setParameter("ebean.url", "jdbc:hsqldb:mem:testdb");
      configuration.setParameter("ebean.username", "sa");
      configuration.setParameter("ebean.password", "");
      configuration.setParameter("ebean.autocreate", "true");
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
      } catch (final Exception e) {
         e.printStackTrace();
         Assert.fail();
      }
   }
}
