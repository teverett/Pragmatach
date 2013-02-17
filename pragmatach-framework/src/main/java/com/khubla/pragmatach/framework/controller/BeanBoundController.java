package com.khubla.pragmatach.framework.controller;

import java.util.Map;

import com.khubla.pragmatach.framework.api.PragmatachException;

/**
 * @author tome
 */
public interface BeanBoundController extends PragmatachController {
   Map<String, String> getPostFieldValues() throws PragmatachException;
}
