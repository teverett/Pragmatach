package test.com.khubla.pragmatach.examples.ebeanexample;

import org.testng.annotations.Test;

import com.khubla.pragmatach.examples.cloudsqlexample.pojo.MyExamplePOJO;
import com.khubla.pragmatach.framework.dao.DAO;
import com.khubla.pragmatach.framework.test.AbstractDAOTest;

/**
 * @author tome
 */
@Test(enabled = true)
public class TestMyExamplePOJO extends AbstractDAOTest<MyExamplePOJO, Long> {
   @Override
   public DAO<MyExamplePOJO, Long> getDAO() {
      return MyExamplePOJO.dao;
   }

   @Override
   public Long getId(MyExamplePOJO myExamplePOJO) {
      return myExamplePOJO.getId();
   }

   @Override
   public MyExamplePOJO getInstance() {
      return new MyExamplePOJO();
   }
}
