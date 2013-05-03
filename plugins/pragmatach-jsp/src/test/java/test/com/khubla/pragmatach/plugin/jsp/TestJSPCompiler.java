package test.com.khubla.pragmatach.plugin.jsp;

import javax.servlet.Servlet;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.khubla.pragmatach.plugin.jsp.JSPCompiler;

/**
 * @author tome
 */
public class TestJSPCompiler {
   @Test
   public void test1() {
      try {
         JSPCompiler jspCompiler = new JSPCompiler();
         Servlet servlet = jspCompiler.getServlet("src/test/resources/helloworld.jsp");
         Assert.assertNotNull(servlet);
      } catch (Exception e) {
         e.printStackTrace();
         Assert.fail();
      }
   }
}
