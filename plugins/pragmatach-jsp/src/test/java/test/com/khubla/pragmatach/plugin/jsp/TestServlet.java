package test.com.khubla.pragmatach.plugin.jsp;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author tome
 */
public class TestServlet extends HttpServlet {
   /**
    * 
    */
   private static final long serialVersionUID = 1L;

   public void service(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
      ServletContext context = getServletContext();
   }
}
