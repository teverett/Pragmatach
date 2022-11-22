package com.khubla.pragmatach.framework.controller;

import com.khubla.pragmatach.framework.api.*;
import com.khubla.pragmatach.framework.router.*;

/**
 * @author tome
 */
public interface PragmatachController {
	/**
	 * set the Route this is being invoked on
	 */
	void setPragmatachRoute(PragmatachRoute pragmatachRoute);

	/**
	 * set the request this is being invoked on
	 */
	void setRequest(Request request);
}
