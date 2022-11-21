package test.com.khubla.pragmatach.plugin.hibernate;

import org.hibernate.criterion.*;
import org.testng.*;
import org.testng.annotations.*;

import com.khubla.pragmatach.dbtestsuite.pojo.*;
import com.khubla.pragmatach.framework.api.*;
import com.khubla.pragmatach.framework.application.*;
import com.khubla.pragmatach.framework.configuration.*;
import com.khubla.pragmatach.framework.dao.*;
import com.khubla.pragmatach.framework.scanner.*;
import com.khubla.pragmatach.plugin.hibernate.*;

/**
 * @author tome
 */
@Test(enabled = true)
public class TestHibernateQuery {
	DAO<ExamplePOJO, Long> dao = null;

	private Configuration getConfiguration() {
		/*
		 * create a config object
		 */
		final HashmapConfigurationImpl configuration = new HashmapConfigurationImpl();
		configuration.setParameter("hibernate.driver", "org.h2.Driver");
		configuration.setParameter("hibernate.dialect", "org.hibernate.dialect.H2Dialect");
		configuration.setParameter("hibernate.connection.url", "jdbc:h2:mem:h2testdb");
		configuration.setParameter("hibernate.connection.username", "sa");
		configuration.setParameter("hibernate.connection.password", "");
		configuration.setParameter("hibernate.hbm2ddl.auto", "create-drop");
		return configuration;
	}

	@BeforeClass
	public void setupClass() {
		/*
		 * run the scanner
		 */
		try {
			AnnotationScanner.scan();
		} catch (final Exception e) {
			e.printStackTrace();
			Assert.fail();
		}
		/*
		 * set the application config
		 */
		Application.setConfiguration(getConfiguration());
		/*
		 * create dao
		 */
		dao = new HibernateDAO<ExamplePOJO, Long>(ExamplePOJO.class, Long.class);
	}

	@Test(enabled = true)
	public void testCriteriaQuery() {
		try {
			/*
			 * create and save an object
			 */
			final ExamplePOJO examplePOJO1 = new ExamplePOJO();
			examplePOJO1.setName("abc123");
			dao.save(examplePOJO1);
			/*
			 * find by the name
			 */
			final HibernateDAO<ExamplePOJO, Long> hibernateDAO = (HibernateDAO<ExamplePOJO, Long>) dao;
			final ExamplePOJO retrievedPojo = (ExamplePOJO) hibernateDAO.find().add(Restrictions.eq("name", "abc123")).uniqueResult();
			Assert.assertNotNull(retrievedPojo);
			Assert.assertNotNull(retrievedPojo.getId());
		} catch (final Exception e) {
			e.printStackTrace();
			Assert.fail();
		}
	}
}
