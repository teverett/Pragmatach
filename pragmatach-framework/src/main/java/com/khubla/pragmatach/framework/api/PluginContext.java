package com.khubla.pragmatach.framework.api;

import com.khubla.pragmatach.framework.resourceloader.*;

/**
 * The plug-in context is passed to every plug-in. It contains methods which enable the plug-in to
 * access framework services
 *
 * @author tome
 */
public interface PluginContext {
	/**
	 * gets the framework-provided resource loader
	 */
	ResourceLoader getResourceLoader() throws PragmatachException;
}
