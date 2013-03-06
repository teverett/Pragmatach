package com.khubla.pragmatach.plugin.yaml;

import java.util.Map;

import com.khubla.pragmatach.framework.api.PragmatachException;
import com.khubla.pragmatach.framework.api.Response;
import com.khubla.pragmatach.framework.controller.AbstractController;
import com.khubla.pragmatach.framework.controller.BeanBoundController;

/**
 * @author tome
 */
public class YAMLController extends AbstractController implements BeanBoundController {
   /**
    * ctor
    */
   public YAMLController() {
   }

   @Override
   public Map<String, String> getPostFieldValues() throws PragmatachException {
      try {
         return PragmatachYAML.parseYAML(getRequest().getInputStream());
      } catch (final Exception e) {
         throw new PragmatachException("Exception in getPostFieldValues", e);
      }
   }

   /**
    * render
    */
   public Response render() throws PragmatachException {
      try {
         return new YAMLResponse(getCacheHeaders(), this);
      } catch (final Exception e) {
         throw new PragmatachException("Exception in render", e);
      }
   }
}
