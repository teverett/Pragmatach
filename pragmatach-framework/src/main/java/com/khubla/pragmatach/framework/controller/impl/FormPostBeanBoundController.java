package com.khubla.pragmatach.framework.controller.impl;

import java.util.*;

import com.khubla.pragmatach.framework.api.*;
import com.khubla.pragmatach.framework.controller.*;
import com.khubla.pragmatach.framework.controller.impl.template.*;
import com.khubla.pragmatach.framework.form.*;

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
