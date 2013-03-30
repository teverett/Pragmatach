package test.com.khubla.pragmatach.plugin.hibernate;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.khubla.pragmatach.framework.application.Application;
import com.khubla.pragmatach.framework.configuration.HashmapConfigurationImpl;

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
      } catch (final Exception e) {
         e.printStackTrace();
         Assert.fail();
      }
   }
}
