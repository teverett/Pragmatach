package test.com.khubla.pragmatach.contrib.controllertester;

import java.io.InputStream;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.khubla.pragmatach.contrib.controllertester.RouteUrl;

/**
 * @author tome
 */
public class TestRouteUrl {
   /**
    * test read of output from Pragmatach Admin export
    */
   @Test
   public void testRead() {
      try {
         final InputStream is = TestRouteUrl.class.getResourceAsStream("/routes1.csv");
         final List<RouteUrl> urls = RouteUrl.readRoutes(is);
         Assert.assertNotNull(urls);
         Assert.assertTrue(urls.size() == 23);
      } catch (final Exception e) {
         e.printStackTrace();
         Assert.fail();
      }
   }
}
