package com.khubla.pragmatach.framework.servlet;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.khubla.pragmatach.framework.api.Request;
import com.khubla.pragmatach.framework.api.Response;
import com.khubla.pragmatach.framework.router.Router;

/**
 * @author tome
 */
public class PragmatachServlet extends HttpServlet {
   /**
    * 
    */
   private static final long serialVersionUID = 1L;
   /**
    * the context path under which public assets are served
    */
   private String publicContextPath;
   /**
    * public assets
    */
   private static final String PUBLIC = "public";

   protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
      try {
         final Router requestRouter = new Router(publicContextPath);
         final Response response = requestRouter.routeGET(new Request(req, resp));
         if (null != response) {
            resp.setStatus(response.getHTTPCode());
            response.render(resp.getOutputStream());
         }
      } catch (final Exception e) {
         throw new ServletException("Exception in doGet", e);
      }
   }

   protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
      try {
         final Router requestRouter = new Router();
         final Response response = requestRouter.routePOST(new Request(req, resp));
         if (null != response) {
            resp.setStatus(response.getHTTPCode());
            response.render(resp.getOutputStream());
         }
      } catch (final Exception e) {
         throw new ServletException("Exception in doGet", e);
      }
   }

   /**
    * init
    */
   public void init(ServletConfig servletConfig) throws ServletException {
      super.init(servletConfig);
      publicContextPath = servletConfig.getInitParameter(PUBLIC);
   }
}
