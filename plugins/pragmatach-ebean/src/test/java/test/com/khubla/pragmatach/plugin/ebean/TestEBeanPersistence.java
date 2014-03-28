package test.com.khubla.pragmatach.plugin.ebean;

import java.util.HashSet;
import java.util.Set;

import org.testng.annotations.Test;

import com.khubla.pragmatach.dbtestsuite.AbstractPersistenceTest;
import com.khubla.pragmatach.dbtestsuite.pojo.ExamplePOJO;
import com.khubla.pragmatach.framework.api.Configuration;
import com.khubla.pragmatach.framework.configuration.HashmapConfigurationImpl;
import com.khubla.pragmatach.framework.dao.DAO;
import com.khubla.pragmatach.plugin.ebean.EBeanDAO;

/**
 * @author tome
 */
@Test
public class TestEBeanPersistence extends AbstractPersistenceTest {
   @Override
   public DAO<ExamplePOJO, Long> getDAO() {
      return new EBeanDAO<ExamplePOJO, Long>(ExamplePOJO.class, Long.class);
   }

   public Set<Configuration> getConfigurations() {
      final Set<Configuration> ret = new HashSet<Configuration>();
      /*
       * derby is not supported by EBean, so its not tested
       */
      /*
       * h2
       */
      final HashmapConfigurationImpl h2Configuration = new HashmapConfigurationImpl();
      h2Configuration.setParameter("ebean.driver", "org.h2.Driver");
      h2Configuration.setParameter("ebean.url", "jdbc:h2:mem:h2testdb");
      h2Configuration.setParameter("ebean.username", "sa");
      h2Configuration.setParameter("ebean.password", "");
      h2Configuration.setParameter("ebean.autocreate", "true");
      ret.add(h2Configuration);
      /*
       * hsql
       */
      final HashmapConfigurationImpl hsqlConfiguration = new HashmapConfigurationImpl();
      hsqlConfiguration.setParameter("ebean.driver", "org.hsqldb.jdbcDriver");
      hsqlConfiguration.setParameter("ebean.url", "jdbc:hsqldb:mem:testdb");
      hsqlConfiguration.setParameter("ebean.username", "sa");
      hsqlConfiguration.setParameter("ebean.password", "");
      hsqlConfiguration.setParameter("ebean.autocreate", "true");
      ret.add(hsqlConfiguration);
      /*
       * mysql (embedded)
       */
      // File ourAppDir = new File(System.getProperty("java.io.tmpdir"));
      // File databaseDir = new File(ourAppDir, "test-mxj");
      // final HashmapConfigurationImpl mysqlConfiguration = new HashmapConfigurationImpl();
      // mysqlConfiguration.setParameter("ebean.driver", "com.mysql.jdbc.Driver");
      // mysqlConfiguration.setParameter("ebean.url", "jdbc:mysql:mxj://localhost:3336/sm?server.basedir=" + databaseDir + "&createDatabaseIfNotExist=true&server.initialize-user=true");
      // mysqlConfiguration.setParameter("ebean.username", "");
      // mysqlConfiguration.setParameter("ebean.password", "");
      // mysqlConfiguration.setParameter("ebean.autocreate", "true");
      // ret.add(mysqlConfiguration);
      /*
       * done
       */
      return ret;
   }
}
