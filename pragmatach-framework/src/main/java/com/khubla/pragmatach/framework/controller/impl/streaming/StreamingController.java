package com.khubla.pragmatach.framework.controller.impl.streaming;

import java.io.*;

import com.khubla.pragmatach.framework.api.*;

/**
 * @author tome
 */
public interface StreamingController {
	void render(InputStream httpInputStream, OutputStream httpOutputStream) throws PragmatachException;
}
