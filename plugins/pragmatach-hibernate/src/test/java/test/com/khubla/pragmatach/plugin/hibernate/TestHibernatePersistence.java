package test.com.khubla.pragmatach.plugin.hibernate;

import java.io.File;
import java.util.HashSet;
import java.util.Set;

import org.testng.annotations.Test;

import com.khubla.pragmatach.dbtestsuite.AbstractPersistenceTest;
import com.khubla.pragmatach.dbtestsuite.pojo.ExamplePOJO;
import com.khubla.pragmatach.framework.api.Configuration;
import com.khubla.pragmatach.framework.configuration.HashmapConfigurationImpl;
import com.khubla.pragmatach.framework.dao.DAO;
import com.khubla.pragmatach.plugin.hibernate.HibernateDAO;

/**
 * @author tome
 */
@Test
public class TestHibernatePersistence extends AbstractPersistenceTest {
   @Override
   public DAO<ExamplePOJO, Long> getDAO() {
      return new HibernateDAO<ExamplePOJO, Long>(ExamplePOJO.class, Long.class);
   }

   public Set<Configuration> getConfigurations() {
      final Set<Configuration> ret = new HashSet<Configuration>();
      /*
       * h2
       */
      final HashmapConfigurationImpl h2Configuration = new HashmapConfigurationImpl();
      h2Configuration.setParameter("hibernate.driver", "org.h2.Driver");
      h2Configuration.setParameter("hibernate.dialect", "org.hibernate.dialect.H2Dialect");
      h2Configuration.setParameter("hibernate.connection.url", "jdbc:h2:mem:h2testdb");
      h2Configuration.setParameter("hibernate.connection.username", "sa");
      h2Configuration.setParameter("hibernate.connection.password", "");
      h2Configuration.setParameter("hibernate.hbm2ddl.auto", "create-drop");
      ret.add(h2Configuration);
      /*
       * hsql
       */
      final HashmapConfigurationImpl hsqlConfiguration = new HashmapConfigurationImpl();
      hsqlConfiguration.setParameter("hibernate.driver", "org.hsqldb.jdbcDriver");
      hsqlConfiguration.setParameter("hibernate.dialect", "org.hibernate.dialect.HSQLDialect");
      hsqlConfiguration.setParameter("hibernate.connection.url", "jdbc:hsqldb:mem:hsqltestdb");
      hsqlConfiguration.setParameter("hibernate.connection.username", "sa");
      hsqlConfiguration.setParameter("hibernate.connection.password", "");
      hsqlConfiguration.setParameter("hibernate.hbm2ddl.auto", "create-drop");
      ret.add(hsqlConfiguration);
      /*
       * derby
       */
      final HashmapConfigurationImpl derbyConfiguration = new HashmapConfigurationImpl();
      derbyConfiguration.setParameter("hibernate.driver", "org.apache.derby.jdbc.EmbeddedDriver");
      derbyConfiguration.setParameter("hibernate.dialect", "org.hibernate.dialect.DerbyDialect");
      derbyConfiguration.setParameter("hibernate.connection.url", "jdbc:derby:memory:derbytestDB;create=true");
      derbyConfiguration.setParameter("hibernate.connection.username", "");
      derbyConfiguration.setParameter("hibernate.connection.password", "");
      derbyConfiguration.setParameter("hibernate.hbm2ddl.auto", "create-drop");
      ret.add(derbyConfiguration);
      /*
       * mysql (embedded)
       */
      File ourAppDir = new File(System.getProperty("java.io.tmpdir"));
      File databaseDir = new File(ourAppDir, "test-mxj");
      final HashmapConfigurationImpl mysqlConfiguration = new HashmapConfigurationImpl();
      mysqlConfiguration.setParameter("hibernate.driver", "com.mysql.jdbc.Driver");
      mysqlConfiguration.setParameter("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
      mysqlConfiguration.setParameter("hibernate.connection.url", "jdbc:mysql:mxj://localhost:3336/sm?server.basedir=" + databaseDir + "&createDatabaseIfNotExist=true&server.initialize-user=true");
      mysqlConfiguration.setParameter("hibernate.connection.username", "");
      mysqlConfiguration.setParameter("hibernate.connection.password", "");
      mysqlConfiguration.setParameter("hibernate.hbm2ddl.auto", "create-drop");
      ret.add(mysqlConfiguration);
      /*
       * done
       */
      return ret;
   }
}
