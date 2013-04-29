package test.com.khubla.pragmatach.examples.ebeanexample;

import org.testng.annotations.Test;

import com.khubla.pragmatach.examples.cloudsqlexample.pojo.AccessLogPOJO;
import com.khubla.pragmatach.framework.dao.DAO;
import com.khubla.pragmatach.framework.test.AbstractDAOTest;

/**
 * @author tome
 */
@Test(enabled = true)
public class TestMyExamplePOJO extends AbstractDAOTest<AccessLogPOJO, Long> {
   @Override
   public DAO<AccessLogPOJO, Long> getDAO() {
      return AccessLogPOJO.dao;
   }

   @Override
   public Long getId(AccessLogPOJO myExamplePOJO) {
      return myExamplePOJO.getId();
   }

   @Override
   public AccessLogPOJO getInstance() {
      return new AccessLogPOJO();
   }
}
