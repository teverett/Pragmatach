package com.khubla.pragmatach.framework.api;

import java.io.*;

/**
 * @author tome
 */
public class PragmatachException extends Exception {
	private static final long serialVersionUID = 1L;

	public static String getExceptionTrace(Throwable throwable) {
		final Writer result = new StringWriter();
		final PrintWriter printWriter = new PrintWriter(result);
		throwable.printStackTrace(printWriter);
		return result.toString();
	}

	public PragmatachException(Exception e) {
		super(e);
	}

	public PragmatachException(String e) {
		super(e);
	}

	public PragmatachException(String s, Exception e) {
		super(s, e);
	}
}