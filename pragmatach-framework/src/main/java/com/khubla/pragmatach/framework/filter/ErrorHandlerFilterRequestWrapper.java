package com.khubla.pragmatach.framework.filter;

import java.io.*;

import javax.servlet.http.*;

/**
 * @author tome
 */
public class ErrorHandlerFilterRequestWrapper extends HttpServletResponseWrapper {
	public ErrorHandlerFilterRequestWrapper(HttpServletResponse httpServletResponse) {
		super(httpServletResponse);
	}

	@Override
	public void sendError(int errorCode) throws IOException {
		if (errorCode == HttpServletResponse.SC_NOT_FOUND) {
			throw new PageNotFoundException();
		}
		super.sendError(errorCode);
	}

	@Override
	public void setStatus(int sc) {
		super.setStatus(sc);
	}
}