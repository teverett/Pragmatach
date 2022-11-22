package com.khubla.pragmatach.framework.controller.impl.template;

import java.util.*;

import com.khubla.pragmatach.framework.api.*;
import com.khubla.pragmatach.framework.controller.impl.*;

/**
 * @author tome
 */
public abstract class SimpleTemplateController extends AbstractController {
	public Response template(String templateName, Map<String, String> parameters) throws PragmatachException {
		try {
			return new SimpleTemplateResponse(getCacheHeaders(), templateName, parameters);
		} catch (final Exception e) {
			throw new PragmatachException("Exception in template", e);
		}
	}
}
