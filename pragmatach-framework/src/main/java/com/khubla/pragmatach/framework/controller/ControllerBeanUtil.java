package com.khubla.pragmatach.framework.controller;

import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;

import com.khubla.pragmatach.framework.api.PragmatachException;

/**
 * @author tome
 */
public class ControllerBeanUtil {
   public static void populateController(PragmatachController pragmatachController, Map<String, String> fieldValues) throws PragmatachException {
      try {
         if (null != fieldValues) {
            for (final String fieldName : fieldValues.keySet()) {
               /*
                * set the fields
                */
               BeanUtils.setProperty(pragmatachController, fieldName, fieldValues.get(fieldName));
            }
         }
      } catch (final Exception e) {
         throw new PragmatachException("Exception in populateController", e);
      }
   }
}
