package com.khubla.pragmatach.framework.controller.impl.streaming;

import java.io.*;

import com.khubla.pragmatach.framework.api.*;
import com.khubla.pragmatach.framework.controller.impl.*;

/**
 * @author tome
 */
public abstract class AbstractStreamingController extends AbstractController implements StreamingController {
	/**
	 * render the response
	 */
	public Response render() throws PragmatachException {
		try {
			return new StreamingResponse(getCacheHeaders(), this, getRequest().getInputStream());
		} catch (final Exception e) {
			throw new PragmatachException("Exception in render", e);
		}
	}

	/**
	 * the abstract method
	 */
	@Override
	public abstract void render(InputStream httpInputStream, OutputStream httpOutputStream) throws PragmatachException;
}
