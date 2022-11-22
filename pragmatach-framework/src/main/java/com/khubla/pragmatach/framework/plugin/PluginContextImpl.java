package com.khubla.pragmatach.framework.plugin;

import com.khubla.pragmatach.framework.api.*;
import com.khubla.pragmatach.framework.resourceloader.*;

/**
 * @author tome
 */
public class PluginContextImpl implements PluginContext {
	/**
	 * resource loader
	 */
	private final ResourceLoader resourceLoader;

	/**
	 * ctor
	 */
	public PluginContextImpl(ResourceLoader resourceLoader) {
		this.resourceLoader = resourceLoader;
	}

	@Override
	public ResourceLoader getResourceLoader() throws PragmatachException {
		return resourceLoader;
	}
}
