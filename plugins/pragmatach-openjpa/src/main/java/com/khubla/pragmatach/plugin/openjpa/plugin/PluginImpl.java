package com.khubla.pragmatach.plugin.openjpa.plugin;

import java.util.*;

import com.khubla.pragmatach.framework.annotation.*;
import com.khubla.pragmatach.framework.api.*;

/**
 * @author tome
 */
@PluginExtension
public class PluginImpl implements Plugin {
	private PluginContext pluginContext;

	@Override
	public I8NProvider getI8NProvider() {
		return null;
	}

	@Override
	public String getName() {
		return "OpenJPA";
	}

	public PluginContext getPluginContext() {
		return pluginContext;
	}

	@Override
	public Map<String, Object> getTemplateVariables() {
		return null;
	}

	@Override
	public void setPluginContext(PluginContext pluginContext) {
		this.pluginContext = pluginContext;
	}

	@Override
	public void startup() throws PragmatachException {
	}
}
