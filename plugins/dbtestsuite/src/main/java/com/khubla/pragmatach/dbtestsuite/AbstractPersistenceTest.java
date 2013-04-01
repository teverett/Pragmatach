package com.khubla.pragmatach.dbtestsuite;

import java.util.Set;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.khubla.pragmatach.dbtestsuite.pojo.ExamplePOJO;
import com.khubla.pragmatach.framework.api.Configuration;
import com.khubla.pragmatach.framework.api.DAO;
import com.khubla.pragmatach.framework.application.Application;
import com.khubla.pragmatach.framework.scanner.AnnotationScanner;

/**
 * @author tome
 */
public abstract class AbstractPersistenceTest {
   /**
    * get the configs
    */
   public abstract Set<Configuration> getConfigurations();

   /**
    * get the DAO implementation
    */
   public abstract DAO<ExamplePOJO, Long> getDAO();

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
   }

   @Test
   public void testAllDatabaseConfigurations() {
      try {
         /*
          * config
          */
         final Set<Configuration> configurations = getConfigurations();
         if ((null != configurations) && (configurations.size() > 0)) {
            /*
             * set an initial config
             */
            Application.setConfiguration(configurations.iterator().next());
            /*
             * the dao
             */
            final DAO<ExamplePOJO, Long> dao = getDAO();
            /*
             * walk the config
             */
            for (final Configuration configuration : configurations) {
               /*
                * dump the config
                */
               for (String key : configuration.getAll().keySet()) {
                  System.out.println(key + " : " + configuration.getParameter(key));
               }
               /*
                * set the application config
                */
               Application.setConfiguration(configuration);
               /*
                * force a session factory reload
                */
               dao.reloadConfig();
               /*
                * run test
                */
               BasicTests.testBasicFunctionality(dao);
            }
         }
      } catch (final Exception e) {
         e.printStackTrace();
         Assert.fail();
      }
   }
}
