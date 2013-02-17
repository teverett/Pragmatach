package com.khubla.pragmatach.plugin.gson;

import java.util.Map;

import com.khubla.pragmatach.framework.api.PragmatachException;
import com.khubla.pragmatach.framework.api.Response;
import com.khubla.pragmatach.framework.controller.AbstractController;
import com.khubla.pragmatach.framework.controller.BeanBoundController;

/**
 * @author tome
 */
public class GSONController extends AbstractController implements BeanBoundController {
   /**
    * ctor
    */
   public GSONController() {
   }

   @Override
   public Map<String, String> getPostFieldValues() throws PragmatachException {
      // TODO Auto-generated method stub
      return null;
   }

   /**
    * render
    */
   public Response render() throws PragmatachException {
      try {
         return new GSONResponse(this);
      } catch (final Exception e) {
         throw new PragmatachException("Exception in render", e);
      }
   }
}
