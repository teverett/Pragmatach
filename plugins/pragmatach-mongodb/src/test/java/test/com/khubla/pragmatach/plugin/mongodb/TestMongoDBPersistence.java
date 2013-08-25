package test.com.khubla.pragmatach.plugin.mongodb;

import java.util.HashSet;
import java.util.Set;

import org.testng.annotations.Test;

import com.khubla.pragmatach.dbtestsuite.AbstractPersistenceTest;
import com.khubla.pragmatach.dbtestsuite.pojo.ExamplePOJO;
import com.khubla.pragmatach.framework.api.Configuration;
import com.khubla.pragmatach.framework.configuration.HashmapConfigurationImpl;
import com.khubla.pragmatach.framework.dao.DAO;
import com.khubla.pragmatach.plugin.mongodb.MongoDBDAO;

/**
 * @author tome
 */
@Test(enabled = true)
public class TestMongoDBPersistence extends AbstractPersistenceTest {
   @Override
   public DAO<ExamplePOJO, Long> getDAO() {
      return new MongoDBDAO<ExamplePOJO, Long>(ExamplePOJO.class, Long.class);
   }

   public Set<Configuration> getConfigurations() {
      final Set<Configuration> ret = new HashSet<Configuration>();
      /*
       * mongo
       */
      final HashmapConfigurationImpl mongoConfiguration = new HashmapConfigurationImpl();
      mongoConfiguration.setParameter("mongodb.Hostname", "192.168.77.79");
      mongoConfiguration.setParameter("mongodb.Database", "test");
      mongoConfiguration.setParameter("mongodb.ConnectionUserName", "");
      mongoConfiguration.setParameter("mongodb.ConnectionPassword", "");
      mongoConfiguration.setParameter("mongodb.AutoCreate", "true");
      ret.add(mongoConfiguration);
      /*
       * done
       */
      return ret;
   }
}
