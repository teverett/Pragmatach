package test.com.khubla.pragmatach.plugin.jsp;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;

import org.testng.Assert;
import org.testng.annotations.Test;

import antlr.collections.impl.Vector;

import com.khubla.pragmatach.plugin.jsp.JSPCompiler;

/**
 * @author tome
 */
public class TestJSPCompiler {
   @Test(enabled = true)
   public void test1() {
      try {
         /*
          * mock up a servlet context
          */
         ServletConfig servletConfig = mock(ServletConfig.class);
         when(servletConfig.getInitParameterNames()).thenReturn(new Vector().elements());
         ServletContext servletContext = mock(ServletContext.class);
         TestServlet testServlet = new TestServlet();
         testServlet.init(servletConfig);
         when(servletConfig.getServletContext()).thenReturn(servletContext);
         /*
          * compile
          */
         JSPCompiler jspCompiler = new JSPCompiler(servletConfig, servletContext);
         Servlet servlet = jspCompiler.getServlet("src/test/resources/helloworld.jsp");
         Assert.assertNotNull(servlet);
      } catch (Exception e) {
         e.printStackTrace();
         Assert.fail();
      }
   }
}
