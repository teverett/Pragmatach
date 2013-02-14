package com.khubla.pragmatach.framework.controller.impl;

import com.khubla.pragmatach.framework.api.PragmatachException;
import com.khubla.pragmatach.framework.api.Request;
import com.khubla.pragmatach.framework.api.Response;
import com.khubla.pragmatach.framework.controller.AbstractController;

/**
 * @author tome
 */
public class StaticResourceController extends AbstractController {
   @Override
   public Response render(Request request) throws PragmatachException {
      try {
         return new StaticResourceResponse("");
      } catch (final Exception e) {
         throw new PragmatachException("Exception in StaticResourceController");
      }
   }
}