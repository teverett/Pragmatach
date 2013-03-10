package test.com.khubla.pragmatach.framework.scanner;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.khubla.pragmatach.framework.controller.ControllerClasses;
import com.khubla.pragmatach.framework.scanner.AnnotationScanner;

/**
 * @author tome
 */
public class TestAnnotationScanner {
   @Test(enabled = true)
   public void testScan() {
      try {
         AnnotationScanner.scan(null);
         ControllerClasses.buildDB();
         Assert.assertTrue(ControllerClasses.getControllers() != null);
         Assert.assertTrue(ControllerClasses.getRouterMethods() != null);
      } catch (final Exception e) {
         e.printStackTrace();
         Assert.fail();
      }
   }
}
