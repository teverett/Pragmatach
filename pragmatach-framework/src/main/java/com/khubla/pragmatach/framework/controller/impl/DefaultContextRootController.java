package com.khubla.pragmatach.framework.controller.impl;

import com.khubla.pragmatach.framework.annotation.*;
import com.khubla.pragmatach.framework.api.*;

/**
 * <p>
 * Controller to redirect root requests to /public/. Such as favicon.ico.
 * </p>
 *
 * @author tome
 */
@Controller(name = "pragmatachDefaultContextRootController")
public class DefaultContextRootController extends AbstractController {
	/**
	 * ctor
	 */
	public DefaultContextRootController() {
	}

	@Route(uri = "/*")
	public Response render(String[] resource) throws PragmatachException {
		try {
			return redirect("/public" + buildWildcardResourceURI(resource));
		} catch (final Exception e) {
			throw new PragmatachException("Exception in render", e);
		}
	}
}