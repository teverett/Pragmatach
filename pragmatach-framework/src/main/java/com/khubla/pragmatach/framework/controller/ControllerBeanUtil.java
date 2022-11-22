package com.khubla.pragmatach.framework.controller;

import java.util.*;
import java.util.Map.*;

import org.apache.commons.beanutils.*;

import com.khubla.pragmatach.framework.api.*;

/**
 * @author tome
 */
public class ControllerBeanUtil {
	public static void populateController(PragmatachController pragmatachController, Map<String, String> fieldValues) throws PragmatachException {
		try {
			if (null != fieldValues) {
				for (final Entry<String, String> entry : fieldValues.entrySet()) {
					/*
					 * set the fields
					 */
					BeanUtils.setProperty(pragmatachController, entry.getKey(), entry.getValue());
				}
			}
		} catch (final Exception e) {
			throw new PragmatachException("Exception in populateController", e);
		}
	}
}
