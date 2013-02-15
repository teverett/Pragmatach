package com.khubla.pragmatach.framework.api;

import java.io.OutputStream;
import java.util.Map;

/**
 * @author tome
 */
public interface Response {
   Map<String, String> getHeaders() throws PragmatachException;

   int getHTTPCode();

   void render(OutputStream outputStream) throws PragmatachException;
}
