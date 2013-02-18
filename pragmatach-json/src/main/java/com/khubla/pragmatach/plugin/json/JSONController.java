package com.khubla.pragmatach.plugin.json;

import java.util.Map;

import com.khubla.pragmatach.framework.api.PragmatachException;
import com.khubla.pragmatach.framework.api.Response;
import com.khubla.pragmatach.framework.controller.AbstractController;
import com.khubla.pragmatach.framework.controller.BeanBoundController;

/**
 * @author tome
 */
public class JSONController extends AbstractController implements BeanBoundController {
   /**
    * ctor
    */
   public JSONController() {
   }

   @Override
   public Map<String, String> getPostFieldValues() throws PragmatachException {
      try {
         return PragmatachJSON.parseJSON(getRequest().getInputStream());
      } catch (final Exception e) {
         throw new PragmatachException("Exception in getPostFieldValues", e);
      }
   }

   /**
    * render
    */
   public Response render() throws PragmatachException {
      try {
         return new JSONResponse(getCacheHeaders(), this);
      } catch (final Exception e) {
         throw new PragmatachException("Exception in render", e);
      }
   }
}
