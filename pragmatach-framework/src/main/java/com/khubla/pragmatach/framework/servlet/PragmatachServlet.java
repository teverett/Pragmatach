package com.khubla.pragmatach.framework.servlet;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.khubla.pragmatach.framework.annotation.Route;
import com.khubla.pragmatach.framework.api.Request;
import com.khubla.pragmatach.framework.api.Response;
import com.khubla.pragmatach.framework.jmx.impl.PerformanceStatistics;
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
    * render time header
    */
   private static final String RENDERTIME_HEADER = "X-Pragmatach-RenderTime";
   /**
    * performance stats.
    */
   private static final PerformanceStatistics performanceStatistics = new PerformanceStatistics();

   public synchronized static PerformanceStatistics getPerformancestatistics() {
      return performanceStatistics;
   }

   /**
    * add custom response headers
    */
   private void addCustomResponseHeaders(Request request, HttpServletResponse httpServletResponse) {
      /*
       * add the render time
       */
      final long rendertime = System.currentTimeMillis() - request.getCreationTime();
      /*
       * add
       */
      httpServletResponse.addHeader(RENDERTIME_HEADER, Long.toString(rendertime));
      /*
       * record it too
       */
      getPerformancestatistics().setLastRenderTime(rendertime);
      /*
       * record a request
       */
      getPerformancestatistics().incrementTotalRequests();
   }

   protected void doGet(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
      try {
         final Router requestRouter = new Router();
         final Request request = new Request(httpServletRequest, httpServletResponse, Route.HttpMethod.get, this.getServletConfig());
         final Response response = requestRouter.route(request);
         addCustomResponseHeaders(request, httpServletResponse);
         processResponse(response, httpServletResponse);
      } catch (final Exception e) {
         throw new ServletException("Exception in doGet", e);
      }
   }

   protected void doPost(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
      try {
         final Router requestRouter = new Router();
         final Request request = new Request(httpServletRequest, httpServletResponse, Route.HttpMethod.post, this.getServletConfig());
         final Response response = requestRouter.route(request);
         addCustomResponseHeaders(request, httpServletResponse);
         processResponse(response, httpServletResponse);
      } catch (final Exception e) {
         throw new ServletException("Exception in doGet", e);
      }
   }

   /**
    * init
    */
   public void init(ServletConfig servletConfig) throws ServletException {
      try {
         super.init(servletConfig);
      } catch (final Exception e) {
         throw new ServletException("Exception in init", e);
      }
   }

   /**
    * process the response
    */
   private void processResponse(Response response, HttpServletResponse httpServletResponse) throws ServletException {
      try {
         if (null != response) {
            final Map<String, String> headers = response.getHeaders();
            if (null != headers) {
               for (final String key : headers.keySet()) {
                  httpServletResponse.setHeader(key, headers.get(key));
               }
            }
            response.render(httpServletResponse.getOutputStream());
            final String contentType = response.getContentType();
            if (null != contentType) {
               httpServletResponse.setContentType(contentType);
            }
            httpServletResponse.setStatus(response.getHTTPCode());
         }
      } catch (final Exception e) {
         throw new ServletException("Exception in processResponse", e);
      }
   }
}
