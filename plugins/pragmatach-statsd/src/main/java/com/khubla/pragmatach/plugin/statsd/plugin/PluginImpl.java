package com.khubla.pragmatach.plugin.statsd.plugin;

import java.util.*;

import com.khubla.pragmatach.framework.annotation.*;
import com.khubla.pragmatach.framework.api.*;
import com.khubla.pragmatach.framework.application.*;
import com.khubla.pragmatach.plugin.statsd.*;

/**
 * @author tome
 */
@PluginExtension
public class PluginImpl implements Plugin {
	/**
	 * the context
	 */
	private PluginContext pluginContext;
	/**
	 * statsd
	 */
	StatsDClientImplementation statsDClientImplementation = new StatsDClientImplementation();

	@Override
	public I8NProvider getI8NProvider() {
		return null;
	}

	@Override
	public String getName() {
		return "Statsd";
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
		Application.getLifecyclelisteners().add(statsDClientImplementation);
	}
}
