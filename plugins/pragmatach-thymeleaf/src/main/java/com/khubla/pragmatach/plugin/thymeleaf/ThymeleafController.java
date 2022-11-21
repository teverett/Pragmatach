package com.khubla.pragmatach.plugin.thymeleaf;

import org.thymeleaf.templateresolver.*;

import com.khubla.pragmatach.framework.api.*;
import com.khubla.pragmatach.framework.controller.impl.*;

/**
 * @author tome
 */
public class ThymeleafController extends FormPostBeanBoundController {
	/**
	 * ctor
	 */
	public ThymeleafController() {
	}

	/**
	 * render
	 */
	public Response render() throws PragmatachException {
		try {
			final ITemplateResolver templateResolver = new PragmatachTemplateResolver(getRequest().getServletContext());
			return new ThymeleafResponse(getCacheHeaders(), getTemplateName(), getTemplateContext(), templateResolver);
		} catch (final Exception e) {
			throw new PragmatachException("Exception in render", e);
		}
	}
}
