package com.khubla.pragmatach.framework.controller.impl;

import java.util.HashMap;
import java.util.Map;

import com.khubla.pragmatach.framework.api.PragmatachException;
import com.khubla.pragmatach.framework.controller.BeanBoundController;
import com.khubla.pragmatach.framework.controller.ControllerBeanUtil;
import com.khubla.pragmatach.framework.controller.impl.template.AbstractTemplateEngineController;
import com.khubla.pragmatach.framework.form.Form;
import com.khubla.pragmatach.framework.form.FormItem;

/**
 * @author tome
 */
public class FormPostBeanBoundController extends AbstractTemplateEngineController implements BeanBoundController {
   protected Map<String, String> getPostFieldValues() throws PragmatachException {
      try {
         final Form form = Form.parse(getRequest().getHttpServletRequest());
         if (null != form) {
            final Map<String, String> ret = new HashMap<String, String>();
            for (final FormItem formItem : form.getItems().values()) {
               ret.put(formItem.getName(), formItem.getValue());
            }
            return ret;
         } else {
            return null;
         }
      } catch (final Exception e) {
         throw new PragmatachException("Exception in getPostFieldValues", e);
      }
   }

   @Override
   public void populateController() throws PragmatachException {
      try {
         ControllerBeanUtil.populateController(this, getPostFieldValues());
      } catch (final Exception e) {
         throw new PragmatachException("Exception in populateController", e);
      }
   }
}
