package com.khubla.pragmatach.framework.controller.impl.template;

import java.io.*;
import java.util.*;
import java.util.Map.*;

import javax.servlet.http.*;

import org.apache.commons.io.*;
import org.apache.commons.lang.*;

import com.khubla.pragmatach.framework.api.*;
import com.khubla.pragmatach.framework.controller.impl.*;

/**
 * @author tome
 */
public class SimpleTemplateResponse extends AbstractResponse {
	private static final String ESCAPE = "$";

	public static void template(InputStream templateInputStream, OutputStream outputStream, Map<String, String> substitutions) throws PragmatachException {
		try {
			/*
			 * read the entire InputStream
			 */
			final ByteArrayOutputStream baos = new ByteArrayOutputStream();
			IOUtils.copy(templateInputStream, baos);
			/*
			 * do substitutions
			 */
			String renderedTemplate = baos.toString("UTF-8");
			if (null != substitutions) {
				for (final Entry<String, String> entry : substitutions.entrySet()) {
					renderedTemplate = StringUtils.replace(renderedTemplate, ESCAPE + entry.getKey(), entry.getValue());
				}
			}
			/*
			 * write out
			 */
			final ByteArrayInputStream bais = new ByteArrayInputStream(renderedTemplate.getBytes("UTF-8"));
			IOUtils.copy(bais, outputStream);
		} catch (final Exception e) {
			throw new PragmatachException("Exception in template", e);
		}
	}

	/**
	 * InputStream
	 */
	private final InputStream resourceInputStream;
	/**
	 * parameters
	 */
	private final Map<String, String> parameters;

	/**
	 * ctor
	 */
	public SimpleTemplateResponse(Map<String, String> cacheHeaders, String template, Map<String, String> parameters) {
		super(cacheHeaders);
		this.parameters = parameters;
		if (null != template) {
			/*
			 * ok, go for it
			 */
			resourceInputStream = getClass().getResourceAsStream("/" + template);
		} else {
			resourceInputStream = null;
		}
	}

	@Override
	public String getContentType() throws PragmatachException {
		return CONTENT_TYPE_HTML;
	}

	@Override
	public Map<String, String> getHeaders() throws PragmatachException {
		return getCacheHeaders();
	}

	@Override
	public void render(HttpServletResponse httpServletResponse) throws PragmatachException {
		try {
			template(resourceInputStream, httpServletResponse.getOutputStream(), parameters);
		} catch (final Exception e) {
			throw new PragmatachException("Exception in render", e);
		}
	}
}
