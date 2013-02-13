package test.com.khubla.pragmatach.framework.router;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.khubla.pragmatach.framework.annotation.AnnotationsScanner;
import com.khubla.pragmatach.framework.router.Routes;

/**
 * @author tome
 */
public class TestRoutes {
   @BeforeTest
   public void setup() {
      try {
         AnnotationsScanner.scan(null);
      } catch (final Exception e) {
         e.printStackTrace();
         Assert.fail();
      }
   }

   @Test
   public void test1() {
      try {
         final Routes routes = Routes.getInstance();
         Assert.assertNotNull(routes);
         Assert.assertTrue(routes.getGETRoutes().size() == 1);
         Assert.assertTrue(routes.getPOSTRoutes().size() == 1);
      } catch (final Exception e) {
         e.printStackTrace();
         Assert.fail();
      }
   }
}
