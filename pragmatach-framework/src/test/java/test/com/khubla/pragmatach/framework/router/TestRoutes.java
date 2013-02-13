package test.com.khubla.pragmatach.framework.router;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.khubla.pragmatach.framework.router.Routes;

/**
 * @author tome
 */
public class TestRoutes {
   @Test
   public void test1() {
      try {
         Routes routes = Routes.getInstance();
         Assert.assertNotNull(routes);
         Assert.assertTrue(routes.getGETRoutes().size() == 1);
         Assert.assertTrue(routes.getPOSTRoutes().size() == 1);
      } catch (Exception e) {
         e.printStackTrace();
         Assert.fail();
      }
   }
}
