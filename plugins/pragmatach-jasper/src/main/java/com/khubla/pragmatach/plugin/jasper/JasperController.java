package com.khubla.pragmatach.plugin.jasper;

import java.util.HashMap;
import java.util.Map;

import com.khubla.pragmatach.framework.api.PragmatachException;
import com.khubla.pragmatach.framework.api.Response;
import com.khubla.pragmatach.framework.controller.BeanBoundController;
import com.khubla.pragmatach.framework.controller.ControllerBeanUtil;
import com.khubla.pragmatach.framework.controller.impl.template.AbstractTemplateEngineController;
import com.khubla.pragmatach.framework.form.Form;
import com.khubla.pragmatach.framework.form.FormItem;

/**
 * @author tome
 */
public class JasperController extends AbstractTemplateEngineController implements BeanBoundController {
   /**
    * ctor
    */
   public JasperController() {
   }

   private Map<String, String> getPostFieldValues() throws PragmatachException {
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

   /**
    * render
    */
   public Response render() throws PragmatachException {
      try {
         final String template = getTemplate();
         return new JasperResponse(getCacheHeaders(), getTemplateName(), template, getTemplateContext());
      } catch (final Exception e) {
         throw new PragmatachException("Exception in render", e);
      }
   }

   @Override
   public void populateController() throws PragmatachException {
      ControllerBeanUtil.populateController(this, this.getPostFieldValues());
   }
}
