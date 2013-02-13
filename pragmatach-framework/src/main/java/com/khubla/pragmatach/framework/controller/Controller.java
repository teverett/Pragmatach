package com.khubla.pragmatach.framework.controller;

import com.khubla.pragmatach.framework.api.PragmatachException;
import com.khubla.pragmatach.framework.api.Request;
import com.khubla.pragmatach.framework.api.Response;

/**
 * @author tome
 */
public interface Controller {
   Response render(Request request) throws PragmatachException;
}
