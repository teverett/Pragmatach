package com.khubla.pragmatach.framework.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.khubla.pragmatach.framework.api.Request;
import com.khubla.pragmatach.framework.controller.PragmatachControllerFactory;
import com.khubla.pragmatach.framework.controller.impl.system.HttpErrorController;

/**
 * @author tome
 */
public class ErrorHandlerFilter implements Filter {
   public void destroy() {
   }

   public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
      HttpServletResponse response = (HttpServletResponse) servletResponse;
      final HttpServletRequest request = (HttpServletRequest) servletRequest;
      try {
         if (!(response instanceof ErrorHandlerFilterRequestWrapper)) {
            response = new ErrorHandlerFilterRequestWrapper(response);
         }
         filterChain.doFilter(servletRequest, response);
      } catch (final Exception e) {
         final HttpErrorController httpErrorController = PragmatachControllerFactory.getHttpErrorController(new Request(request), e);
         try {
            httpErrorController.render().render(response.getOutputStream());
         } catch (final Exception e2) {
            throw new ServletException(e2);
         }
      }
   }

   public void init(FilterConfig filterConfig) throws ServletException {
   }
}
