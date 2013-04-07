package com.khubla.pragmatach.framework.controller.impl.streaming;

import java.io.InputStream;
import java.io.OutputStream;

import com.khubla.pragmatach.framework.api.PragmatachException;
import com.khubla.pragmatach.framework.api.Response;
import com.khubla.pragmatach.framework.controller.impl.AbstractController;

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
   public abstract void render(InputStream httpInputStream, OutputStream httpOutputStream) throws PragmatachException;
}
