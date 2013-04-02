package test.com.khubla.pragmatach.plugin.openjpa;

import java.io.File;
import java.util.HashSet;
import java.util.Set;

import org.testng.annotations.Test;

import com.khubla.pragmatach.dbtestsuite.AbstractPersistenceTest;
import com.khubla.pragmatach.dbtestsuite.pojo.ExamplePOJO;
import com.khubla.pragmatach.framework.api.Configuration;
import com.khubla.pragmatach.framework.configuration.HashmapConfigurationImpl;
import com.khubla.pragmatach.framework.dao.DAO;
import com.khubla.pragmatach.plugin.openjpa.OpenJPADAO;

/**
 * @author tome
 */
@Test
public class TestOpenJPAPersistence extends AbstractPersistenceTest {
   @Override
   public DAO<ExamplePOJO, Long> getDAO() {
      return new OpenJPADAO<ExamplePOJO, Long>(ExamplePOJO.class, Long.class);
   }

   public Set<Configuration> getConfigurations() {
      final Set<Configuration> ret = new HashSet<Configuration>();
      /*
       * h2
       */
      final HashmapConfigurationImpl h2Configuration = new HashmapConfigurationImpl();
      h2Configuration.setParameter("openjpa.ConnectionDriverName", "org.h2.Driver");
      h2Configuration.setParameter("openjpa.ConnectionURL", "jdbc:h2:mem:h2testdb");
      h2Configuration.setParameter("openjpa.ConnectionUserName", "sa");
      h2Configuration.setParameter("openjpa.ConnectionPassword", "");
      h2Configuration.setParameter("openjpa.jdbc.SynchronizeMappings", "buildSchema(ForeignKeys=true)");
      // ret.add(h2Configuration);
      /*
       * hsql
       */
      final HashmapConfigurationImpl hsqlConfiguration = new HashmapConfigurationImpl();
      hsqlConfiguration.setParameter("openjpa.ConnectionDriverName", "org.hsqldb.jdbcDriver");
      hsqlConfiguration.setParameter("openjpa.ConnectionURL", "jdbc:hsqldb:mem:testdb");
      hsqlConfiguration.setParameter("openjpa.ConnectionUserName", "sa");
      hsqlConfiguration.setParameter("openjpa.ConnectionPassword", "");
      hsqlConfiguration.setParameter("openjpa.jdbc.SynchronizeMappings", "buildSchema(ForeignKeys=true)");
      ret.add(hsqlConfiguration);
      /*
       * derby
       */
      final HashmapConfigurationImpl derbyConfiguration = new HashmapConfigurationImpl();
      derbyConfiguration.setParameter("openjpa.ConnectionDriverName", "org.apache.derby.jdbc.EmbeddedDriver");
      derbyConfiguration.setParameter("openjpa.ConnectionURL", "jdbc:derby:memory:derbytestDB;create=true");
      derbyConfiguration.setParameter("openjpa.ConnectionUserName", "");
      derbyConfiguration.setParameter("openjpa.ConnectionPassword", "");
      derbyConfiguration.setParameter("openjpa.jdbc.SynchronizeMappings", "buildSchema(ForeignKeys=true)");
      ret.add(derbyConfiguration);
      /*
       * mysql (embedded)
       */
      File ourAppDir = new File(System.getProperty("java.io.tmpdir"));
      File databaseDir = new File(ourAppDir, "test-mxj");
      final HashmapConfigurationImpl mysqlConfiguration = new HashmapConfigurationImpl();
      mysqlConfiguration.setParameter("openjpa.ConnectionDriverName", "com.mysql.jdbc.Driver");
      mysqlConfiguration.setParameter("openjpa.ConnectionURL", "jdbc:mysql:mxj://localhost:3336/sm?server.basedir=" + databaseDir + "&createDatabaseIfNotExist=true&server.initialize-user=true");
      mysqlConfiguration.setParameter("openjpa.ConnectionUserName", "");
      mysqlConfiguration.setParameter("openjpa.ConnectionPassword", "");
      mysqlConfiguration.setParameter("openjpa.jdbc.SynchronizeMappings", "buildSchema(SchemaAction='add,deleteTableContents')");
      ret.add(mysqlConfiguration);
      /*
       * done
       */
      return ret;
   }
}
