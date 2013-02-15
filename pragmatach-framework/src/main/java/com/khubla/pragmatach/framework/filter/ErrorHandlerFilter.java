package com.khubla.pragmatach.framework.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;

/**
 * @author tome
 */
public class ErrorHandlerFilter implements Filter {
   public void destroy() {
   }

   public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
      try {
         HttpServletResponse response = (HttpServletResponse) servletResponse;
         if (!(response instanceof ErrorHandlerFilterRequestWrapper)) {
            response = new ErrorHandlerFilterRequestWrapper(response);
         }
         filterChain.doFilter(servletRequest, response);
      } catch (final Exception e) {
         throw new ServletException(e);
      }
   }

   public void init(FilterConfig filterConfig) throws ServletException {
   }
}
