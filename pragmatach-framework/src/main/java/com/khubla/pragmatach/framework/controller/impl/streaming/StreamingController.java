package com.khubla.pragmatach.framework.controller.impl.streaming;

import java.io.InputStream;
import java.io.OutputStream;

import com.khubla.pragmatach.framework.api.PragmatachException;

/**
 * @author tome
 */
public interface StreamingController {
   void render(InputStream httpInputStream, OutputStream httpOutputStream) throws PragmatachException;
}
