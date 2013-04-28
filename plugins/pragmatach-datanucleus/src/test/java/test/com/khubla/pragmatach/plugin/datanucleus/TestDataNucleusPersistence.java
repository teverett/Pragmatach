package test.com.khubla.pragmatach.plugin.datanucleus;

import java.io.File;
import java.util.HashSet;
import java.util.Set;

import org.testng.annotations.Test;

import com.khubla.pragmatach.dbtestsuite.AbstractPersistenceTest;
import com.khubla.pragmatach.dbtestsuite.pojo.ExamplePOJO;
import com.khubla.pragmatach.framework.api.Configuration;
import com.khubla.pragmatach.framework.configuration.HashmapConfigurationImpl;
import com.khubla.pragmatach.framework.dao.DAO;
import com.khubla.pragmatach.plugin.datanucleus.DataNucleusDAO;

/**
 * @author tome
 */
@Test
public class TestDataNucleusPersistence extends AbstractPersistenceTest {
   @Override
   public Set<Configuration> getConfigurations() {
      final Set<Configuration> ret = new HashSet<Configuration>();
      /*
       * h2
       */
      final HashmapConfigurationImpl h2Configuration = new HashmapConfigurationImpl();
      h2Configuration.setParameter("datanucleus.driver", "org.h2.Driver");
      h2Configuration.setParameter("datanucleus.url", "jdbc:h2:mem:h2testdb");
      h2Configuration.setParameter("datanucleus.username", "sa");
      h2Configuration.setParameter("datanucleus.password", "");
      h2Configuration.setParameter("datanucleus.autocreate", "true");
      ret.add(h2Configuration);
      /*
       * hsql
       */
      final HashmapConfigurationImpl hsqlConfiguration = new HashmapConfigurationImpl();
      hsqlConfiguration.setParameter("datanucleus.driver", "org.hsqldb.jdbcDriver");
      hsqlConfiguration.setParameter("datanucleus.url", "jdbc:hsqldb:mem:testdb");
      hsqlConfiguration.setParameter("datanucleus.username", "sa");
      hsqlConfiguration.setParameter("datanucleus.password", "");
      hsqlConfiguration.setParameter("datanucleus.autocreate", "true");
      ret.add(hsqlConfiguration);
      /*
       * mysql (embedded)
       */
      final File ourAppDir = new File(System.getProperty("java.io.tmpdir"));
      final File databaseDir = new File(ourAppDir, "test-mxj");
      final HashmapConfigurationImpl mysqlConfiguration = new HashmapConfigurationImpl();
      mysqlConfiguration.setParameter("datanucleus.driver", "com.mysql.jdbc.Driver");
      mysqlConfiguration.setParameter("datanucleus.url", "jdbc:mysql:mxj://localhost:3336/sm?server.basedir=" + databaseDir + "&createDatabaseIfNotExist=true&server.initialize-user=true");
      mysqlConfiguration.setParameter("datanucleus.username", "");
      mysqlConfiguration.setParameter("datanucleus.password", "");
      mysqlConfiguration.setParameter("datanucleus.autocreate", "true");
      ret.add(mysqlConfiguration);
      /*
       * done
       */
      return ret;
   }

   @Override
   public DAO<ExamplePOJO, Long> getDAO() {
      return new DataNucleusDAO<ExamplePOJO, Long>(ExamplePOJO.class, Long.class);
   }
}