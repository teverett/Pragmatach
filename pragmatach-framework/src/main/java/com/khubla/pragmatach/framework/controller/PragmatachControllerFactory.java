package com.khubla.pragmatach.framework.controller;

import com.khubla.pragmatach.framework.api.*;
import com.khubla.pragmatach.framework.controller.impl.system.*;

/**
 * @author tome
 */
public class PragmatachControllerFactory {
	public static HttpErrorController getHttpErrorController(Request request, Exception e) {
		final HttpErrorController ret = new HttpErrorController(e);
		ret.setRequest(request);
		return ret;
	}
}
