package com.khubla.pragmatach.plugin.thymeleaf;

import javax.servlet.*;

import org.thymeleaf.templateresolver.TemplateResolver;

/**
 * @author tome
 */
public class PragmatachTemplateResolver extends TemplateResolver {
	public PragmatachTemplateResolver(ServletContext servletContext) {
		super();
		super.setResourceResolver(new PragmatachResourceResolver(servletContext));
	}
}
