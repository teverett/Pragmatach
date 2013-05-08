package com.khubla.pragmatach.framework.controller.impl.streaming;

import java.io.InputStream;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import com.khubla.pragmatach.framework.api.PragmatachException;
import com.khubla.pragmatach.framework.controller.impl.AbstractResponse;

/**
 * @author tome
 */
public class StreamingResponse extends AbstractResponse {
   /**
    * streaming controller
    */
   private final StreamingController streamingController;
   /**
    * input stream
    */
   private final InputStream inputStream;

   public StreamingResponse(Map<String, String> cacheHeaders, StreamingController streamingController, InputStream inputStream) {
      super(cacheHeaders);
      this.streamingController = streamingController;
      this.inputStream = inputStream;
   }

   @Override
   public String getContentType() throws PragmatachException {
      return null;
   }

   @Override
   public Map<String, String> getHeaders() throws PragmatachException {
      return null;
   }

   public StreamingController getStreamingController() {
      return streamingController;
   }

   @Override
   public void render(HttpServletResponse httpServletResponse) throws PragmatachException {
      try {
         streamingController.render(inputStream, httpServletResponse.getOutputStream());
      } catch (final Exception e) {
         throw new PragmatachException("Exception in render", e);
      }
   }
}
