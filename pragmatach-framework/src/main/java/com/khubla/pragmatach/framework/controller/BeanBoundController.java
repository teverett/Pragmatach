package com.khubla.pragmatach.framework.controller;

import com.khubla.pragmatach.framework.api.PragmatachException;

/**
 * @author tome
 */
public interface BeanBoundController extends PragmatachController {
   void populateController() throws PragmatachException;
}
