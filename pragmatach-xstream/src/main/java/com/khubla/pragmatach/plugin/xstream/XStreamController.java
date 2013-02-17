package com.khubla.pragmatach.plugin.xstream;

import com.khubla.pragmatach.framework.api.PragmatachException;
import com.khubla.pragmatach.framework.api.Response;
import com.khubla.pragmatach.framework.controller.AbstractController;

/**
 * @author tome
 */
public class XStreamController extends AbstractController {
   /**
    * ctor
    */
   public XStreamController() {
   }

   /**
    * render
    */
   public Response render() throws PragmatachException {
      try {
         return new XStreamResponse(this);
      } catch (final Exception e) {
         throw new PragmatachException("Exception in render", e);
      }
   }
}
