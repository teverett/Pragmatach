package com.khubla.pragmatach.plugin.cluster.plugin;

import java.util.*;

import com.khubla.pragmatach.framework.annotation.*;
import com.khubla.pragmatach.framework.api.*;
import com.khubla.pragmatach.framework.controller.*;
import com.khubla.pragmatach.plugin.cluster.*;

/**
 * @author tome
 */
@PluginExtension
public class PluginImpl implements Plugin {
	/**
	 * the plugin context
	 */
	private PluginContext pluginContext;

	@Override
	public I8NProvider getI8NProvider() {
		return null;
	}

	@Override
	public String getName() {
		return "Cluster";
	}

	public PluginContext getPluginContext() {
		return pluginContext;
	}

	@Override
	public Map<String, Object> getTemplateVariables() {
		try {
			final Map<String, Object> ret = new HashMap<String, Object>();
			/*
			 * add all the cluster controllers by name
			 */
			final Map<String, PragmatachController> controllerInstances = ClusteredControllers.getInstance().getControllerInstances();
			if (null != controllerInstances) {
				/*
				 * walk clustered controllers
				 */
				for (final String controllerName : controllerInstances.keySet()) {
					/*
					 * add
					 */
					ret.put(controllerName, controllerInstances.get(controllerName));
				}
			}
			return ret;
		} catch (final Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public void setPluginContext(PluginContext pluginContext) {
		this.pluginContext = pluginContext;
	}

	@Override
	public void startup() throws PragmatachException {
		try {
			/*
			 * this will instantiate all the controllers
			 */
			ClusteredControllers.getInstance();
		} catch (final Exception e) {
			throw new PragmatachException("Exception in startup", e);
		}
	}
}
