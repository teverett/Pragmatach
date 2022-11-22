package com.khubla.pragmatach.framework.lifecycle;

import com.khubla.pragmatach.framework.api.*;
import com.khubla.pragmatach.framework.router.*;

/**
 * @author tome
 */
public interface LifecycleListener {
	/**
	 * request start
	 */
	void beginRequest(PragmatachRoute pragmatachRoute, Request request);

	/**
	 * request end
	 */
	void endRequest(PragmatachRoute pragmatachRoute, Request request);
}
