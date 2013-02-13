package com.khubla.pragmatach.framework.api;

import java.io.OutputStream;

/**
 * @author tome
 */
public interface Response {
   int getHTTPCode();

   void render(OutputStream outputStream) throws PragmatachException;
}
