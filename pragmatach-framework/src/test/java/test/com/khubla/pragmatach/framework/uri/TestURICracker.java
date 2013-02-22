package test.com.khubla.pragmatach.framework.uri;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.khubla.pragmatach.framework.url.URICracker;

/**
 * @author tome
 */
public class TestURICracker {
   @Test
   public void testRootURI() {
      try {
         String[] cracked = URICracker.crackURI("/");
         Assert.assertNotNull(cracked);
         Assert.assertTrue(cracked.length == 1);
         Assert.assertTrue(cracked[0].compareTo("/") == 0);
      } catch (final Exception e) {
         e.printStackTrace();
         Assert.fail();
      }
   }

   @Test
   public void testSimpleURI() {
      try {
         String[] cracked = URICracker.crackURI("/a/f/r");
         Assert.assertNotNull(cracked);
         Assert.assertTrue(cracked.length == 3);
         Assert.assertTrue(cracked[0].compareTo("a") == 0);
      } catch (final Exception e) {
         e.printStackTrace();
         Assert.fail();
      }
   }
}
