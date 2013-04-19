package com.khubla.pragmatach.plugin.freemarker;

import java.io.Writer;

import freemarker.core.Environment;
import freemarker.template.TemplateException;
import freemarker.template.TemplateExceptionHandler;
/**
 * @author tome
 */
public class TemplateExceptionHandlerImpl implements TemplateExceptionHandler{

	@Override
	public void handleTemplateException(TemplateException templateException,
			Environment environment, Writer writer) throws TemplateException {
	throw templateException;
		
	}

}
