package com.khubla.pragmatach.framework.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.khubla.pragmatach.framework.api.Request;
import com.khubla.pragmatach.framework.api.Response;
import com.khubla.pragmatach.framework.router.Router;
import com.khubla.pragmatach.framework.router.impl.DefaultRouterImpl;

/**
 * @author tome
 */
public class PragmatachServlet extends HttpServlet {
   /**
    * 
    */
   private static final long serialVersionUID = 1L;
   /**
    * router
    */
   private final Router requestRouter = new DefaultRouterImpl();

   protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
      try {
         final Response response = requestRouter.routeGET(new Request(req, resp));
         if (null != response) {
            response.render(resp.getOutputStream());
         }
      } catch (final Exception e) {
         throw new ServletException("Exception in doGet", e);
      }
   }

   protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
      try {
         final Response response = requestRouter.routePOST(new Request(req, resp));
         if (null != response) {
            response.render(resp.getOutputStream());
         }
      } catch (final Exception e) {
         throw new ServletException("Exception in doGet", e);
      }
   }
}
