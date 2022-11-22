package com.khubla.pragmatach.framework.controller.impl.trivial;

import com.khubla.pragmatach.framework.annotation.*;
import com.khubla.pragmatach.framework.api.*;
import com.khubla.pragmatach.framework.controller.impl.*;

/**
 * @author tome
 */
@Controller(name = "pragmatachTrivialController")
public class TrivialController extends AbstractController {
	/**
	 * message
	 */
	private final String message;
	/**
	 * code
	 */
	private final int httpCode;

	public TrivialController(int httpCode) {
		message = null;
		this.httpCode = httpCode;
	}

	public TrivialController(String message, int httpCode) {
		this.message = message;
		this.httpCode = httpCode;
	}

	public Response render() throws PragmatachException {
		return new TrivialResponse(getCacheHeaders(), message, httpCode, null);
	}
}
