package test.com.khubla.pragmatach.framework.scanner;

import org.testng.*;
import org.testng.annotations.*;

import com.khubla.pragmatach.framework.controller.*;
import com.khubla.pragmatach.framework.scanner.*;

/**
 * @author tome
 */
public class TestAnnotationScanner {
	@Test(enabled = true)
	public void testScan() {
		try {
			AnnotationScanner.scan();
			ControllerClasses.buildDB();
			Assert.assertTrue(ControllerClasses.getControllers() != null);
			Assert.assertTrue(ControllerClasses.getControllers().size() > 0);
			Assert.assertTrue(ControllerClasses.getRouterMethods() != null);
			Assert.assertTrue(ControllerClasses.getRouterMethods().size() > 0);
		} catch (final Exception e) {
			e.printStackTrace();
			Assert.fail();
		}
	}
}
