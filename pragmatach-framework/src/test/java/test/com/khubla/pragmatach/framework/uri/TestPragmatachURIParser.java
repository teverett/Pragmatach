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
   public void testBoundURI() {
      try {
         final List<RouteSpecificationSegment> parts = new RouteSpecification("/a/b/c/d/@3").getSegments();
         Assert.assertNotNull(parts);
         Assert.assertTrue(parts.size() == 5);
         Assert.assertTrue(parts.get(4).getVariableId().compareTo("3") == 0);
      } catch (final Exception e) {
         e.printStackTrace();
         Assert.fail();
      }
   }

   @Test
   public void testNestedRoute() {
      try {
         final List<RouteSpecificationSegment> parts = new RouteSpecification("/exampleRoutesController1/@num1/@num2/@message").getSegments();
         Assert.assertNotNull(parts);
         Assert.assertTrue(parts.size() == 4);
         Assert.assertTrue(parts.get(0).getPath().compareTo("exampleRoutesController1") == 0);
      } catch (final Exception e) {
         e.printStackTrace();
         Assert.fail();
      }
   }

   @Test
   public void testSimpleId() {
      try {
         final List<RouteSpecificationSegment> parts = new RouteSpecification("/@showfeed").getSegments();
         Assert.assertNotNull(parts);
         Assert.assertTrue(parts.size() == 1);
         Assert.assertTrue(parts.get(0).getPath() == null);
         Assert.assertTrue(parts.get(0).getVariableId().compareTo("showfeed") == 0);
      } catch (final Exception e) {
         e.printStackTrace();
         Assert.fail();
      }
   }

   @Test
   public void testSimpleURI1() {
      try {
         final List<RouteSpecificationSegment> parts = new RouteSpecification("/a/b/c/d").getSegments();
         Assert.assertNotNull(parts);
         Assert.assertTrue(parts.size() == 4);
      } catch (final Exception e) {
         e.printStackTrace();
         Assert.fail();
      }
   }

   @Test
   public void testSimpleURI2() {
      try {
         final List<RouteSpecificationSegment> parts = new RouteSpecification("/").getSegments();
         Assert.assertNotNull(parts);
         Assert.assertTrue(parts.size() == 1);
      } catch (final Exception e) {
         e.printStackTrace();
         Assert.fail();
      }
   }

   @Test
   public void testSimpleURI3() {
      try {
         final List<RouteSpecificationSegment> parts = new RouteSpecification("/showfeed").getSegments();
         Assert.assertNotNull(parts);
         Assert.assertTrue(parts.size() == 1);
      } catch (final Exception e) {
         e.printStackTrace();
         Assert.fail();
      }
   }
}
