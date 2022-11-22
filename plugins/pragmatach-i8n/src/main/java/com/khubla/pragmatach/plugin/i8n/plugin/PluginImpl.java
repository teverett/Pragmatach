package com.khubla.pragmatach.plugin.i8n.plugin;

import java.util.*;

import com.khubla.pragmatach.framework.annotation.*;
import com.khubla.pragmatach.framework.api.*;
import com.khubla.pragmatach.plugin.i8n.*;

/**
 * @author tome
 */
@PluginExtension
public class PluginImpl implements Plugin {
	private PluginContext pluginContext;

	@Override
	public I8NProvider getI8NProvider() {
		return new I8NImpl(this);
	}

	@Override
	public String getName() {
		return "i8n";
	}

	public PluginContext getPluginContext() {
		return pluginContext;
	}

	@Override
	public void setPluginContext(PluginContext pluginContext) {
		this.pluginContext = pluginContext;
	}

	@Override
	public void startup() throws PragmatachException {
	}

	@Override
	public Map<String, Object> getTemplateVariables() {
		return null;
	}
}
