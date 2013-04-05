package com.khubla.pragmatach.plugin.xstream;

import java.util.Map;

import com.khubla.pragmatach.framework.api.PragmatachException;
import com.khubla.pragmatach.framework.api.Response;
import com.khubla.pragmatach.framework.controller.AbstractController;
import com.khubla.pragmatach.framework.controller.BeanBoundController;
import com.khubla.pragmatach.framework.controller.ControllerBeanUtil;

/**
 * @author tome
 */
public class XStreamController extends AbstractController implements BeanBoundController {
   /**
    * ctor
    */
   public XStreamController() {
   }

   private Map<String, String> getPostFieldValues() throws PragmatachException {
      // TODO Auto-generated method stub
      return null;
   }

   /**
    * render
    */
   public Response render() throws PragmatachException {
      try {
         return new XStreamResponse(getCacheHeaders(), this);
      } catch (final Exception e) {
         throw new PragmatachException("Exception in render", e);
      }
   }

   @Override
   public void populateController() throws PragmatachException {
      ControllerBeanUtil.populateController(this, this.getPostFieldValues());
   }
}
