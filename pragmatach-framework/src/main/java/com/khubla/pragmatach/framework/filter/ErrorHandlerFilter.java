package com.khubla.pragmatach.framework.filter;

import java.io.*;

import javax.servlet.*;
import javax.servlet.http.*;

import com.khubla.pragmatach.framework.annotation.*;
import com.khubla.pragmatach.framework.api.*;
import com.khubla.pragmatach.framework.controller.*;
import com.khubla.pragmatach.framework.controller.impl.system.*;

/**
 * @author tome
 */
public class ErrorHandlerFilter implements Filter {
	@Override
	public void destroy() {
	}

	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
		HttpServletResponse response = (HttpServletResponse) servletResponse;
		final HttpServletRequest request = (HttpServletRequest) servletRequest;
		try {
			if (!(response instanceof ErrorHandlerFilterRequestWrapper)) {
				response = new ErrorHandlerFilterRequestWrapper(response);
			}
			filterChain.doFilter(servletRequest, response);
		} catch (final Exception e) {
			final HttpErrorController httpErrorController = PragmatachControllerFactory.getHttpErrorController(new Request(request, response, Route.HttpMethod.get, null), e);
			try {
				httpErrorController.render().render(response);
			} catch (final Exception e2) {
				throw new ServletException(e2);
			}
		}
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
	}
}
