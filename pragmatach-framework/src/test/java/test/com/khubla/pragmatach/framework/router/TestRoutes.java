package test.com.khubla.pragmatach.framework.router;

import java.util.*;

import org.testng.*;
import org.testng.annotations.*;

import com.khubla.pragmatach.framework.controller.*;
import com.khubla.pragmatach.framework.router.*;
import com.khubla.pragmatach.framework.scanner.*;

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
			AnnotationScanner.scan();
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
			Assert.assertTrue(routes.getGETRoutes().get(0).getRoute().uri().compareTo("/333") == 0);
			Assert.assertTrue(routes.getGETRoutes().get(1).getRoute().uri().compareTo("/") == 0);
			Assert.assertTrue(routes.getGETRoutes().get(2).getRoute().uri().compareTo("/public/*") == 0);
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

	@Test(enabled = true)
	public void testRouteScoping() {
		try {
			/*
			 * root route
			 */
			Assert.assertTrue(PragmatachRoute.scopes("/*", "asas"));
			/*
			 * both static
			 */
			Assert.assertTrue(PragmatachRoute.scopes("/", "/asas"));
			Assert.assertTrue(PragmatachRoute.scopes("/aas", "/aas/s"));
			/*
			 * first wildcard, second static
			 */
			Assert.assertTrue(PragmatachRoute.scopes("/*", "/asas"));
			Assert.assertTrue(PragmatachRoute.scopes("/aas/*", "/aas/"));
			/*
			 * first static, second wildcard
			 */
			Assert.assertFalse(PragmatachRoute.scopes("/asas", "/a/b/*"));
			/*
			 * both wildcard
			 */
			Assert.assertTrue(PragmatachRoute.scopes("/a/*", "/a/b/*"));
		} catch (final Exception e) {
			e.printStackTrace();
			Assert.fail();
		}
	}
}
