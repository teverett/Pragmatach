package com.khubla.pragmatach.plugin.gson;

import java.util.Map;

import org.json.JSONArray;

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
      try {
         final String JSON = getRequest().getPostBody();
         if ((null != JSON) && (JSON.length() > 0)) {
            new JSONArray(JSON);
            return null;
         } else {
            return null;
         }
      } catch (final Exception e) {
         throw new PragmatachException("Exception in getPostFieldValues", e);
      }
   }

   /**
    * render
    */
   public Response render() throws PragmatachException {
      try {
         return new GSONResponse(getCacheHeaders(), this);
      } catch (final Exception e) {
         throw new PragmatachException("Exception in render", e);
      }
   }
}
