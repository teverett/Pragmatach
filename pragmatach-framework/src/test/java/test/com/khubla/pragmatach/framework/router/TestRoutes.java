package test.com.khubla.pragmatach.framework.router;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.khubla.pragmatach.framework.controller.ControllerClasses;
import com.khubla.pragmatach.framework.router.PragmatachRoute;
import com.khubla.pragmatach.framework.router.PragmatachRoutes;
import com.khubla.pragmatach.framework.scanner.AnnotationScanner;

/**
 * @author tome
 */
public class TestRoutes {
   private void reportRoutes(List<PragmatachRoute> routes) {
      for (final PragmatachRoute pragmatachRoute : routes) {
         System.out.println(pragmatachRoute.getRoute().uri());
      }
   }

   @BeforeTest
   public void setup() {
      try {
         AnnotationScanner.scan(null);
         ControllerClasses.buildDB();
      } catch (final Exception e) {
         e.printStackTrace();
         Assert.fail();
      }
   }

   @Test(enabled = true)
   public void testGETScoping() {
      try {
         final PragmatachRoutes routes = PragmatachRoutes.getInstance();
         Assert.assertNotNull(routes);
         reportRoutes(routes.getGETRoutes());
         Assert.assertTrue(routes.getGETRoutes().size() == 4);
         Assert.assertTrue(routes.getGETRoutes().get(0).getRoute().uri().compareTo("/") == 0);
         Assert.assertTrue(routes.getGETRoutes().get(3).getRoute().uri().compareTo("/*") == 0);
      } catch (final Exception e) {
         e.printStackTrace();
         Assert.fail();
      }
   }

   @Test(enabled = true)
   public void testPOSTScoping() {
      try {
         final PragmatachRoutes routes = PragmatachRoutes.getInstance();
         Assert.assertNotNull(routes);
         reportRoutes(routes.getPOSTRoutes());
         Assert.assertTrue(routes.getPOSTRoutes().size() == 3);
         Assert.assertTrue(routes.getPOSTRoutes().get(2).getRoute().uri().compareTo("/a") == 0);
      } catch (final Exception e) {
         e.printStackTrace();
         Assert.fail();
      }
   }
}
