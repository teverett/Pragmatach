package test.com.khubla.pragmatach.framework.uri;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.khubla.pragmatach.framework.url.RouteSpecification;
import com.khubla.pragmatach.framework.url.RouteSpecificationSegment;

/**
 * @author tome
 */
public class TestPragmatachURIParser {
   @Test
   public void testSimpleURI() {
      try {
         List<RouteSpecificationSegment> parts = RouteSpecification.parse("/a/b/c/d").getSegments();
         Assert.assertNotNull(parts);
         Assert.assertTrue(parts.size() == 4);
      } catch (Exception e) {
         e.printStackTrace();
         Assert.fail();
      }
   }

   @Test
   public void testRegexURI() {
      try {
         List<RouteSpecificationSegment> parts = RouteSpecification.parse("/a/b/c/d/{sdsdsd}").getSegments();
         Assert.assertNotNull(parts);
         Assert.assertTrue(parts.size() == 5);
         Assert.assertTrue(parts.get(4).getRegex().compareTo("sdsdsd") == 0);
      } catch (Exception e) {
         e.printStackTrace();
         Assert.fail();
      }
   }
}
