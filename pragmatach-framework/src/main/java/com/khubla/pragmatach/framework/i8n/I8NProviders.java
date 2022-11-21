package com.khubla.pragmatach.framework.i8n;

import java.util.*;

import org.slf4j.*;

import com.khubla.pragmatach.framework.api.*;
import com.khubla.pragmatach.framework.plugin.*;

/**
 * @author tome
 */
public class I8NProviders {
	/**
	 * singleton
	 */
	public static I8NProviders getInstance() {
		if (null == instance) {
			instance = new I8NProviders();
		}
		return instance;
	}

	/**
	 * instance
	 */
	private static I8NProviders instance = null;
	/**
	 * logger
	 */
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	/**
	 * providers
	 */
	public Set<I8NProvider> providers;

	/**
	 * ctor
	 */
	private I8NProviders() {
		final Map<String, Plugin> plugins = PluginsRegistry.getPlugins();
		if (null != plugins) {
			providers = new HashSet<I8NProvider>();
			for (final Plugin plugin : plugins.values()) {
				final I8NProvider i8NProvider = plugin.getI8NProvider();
				if (null != i8NProvider) {
					logger.info("Loaded i8N provider: " + i8NProvider.getName());
					providers.add(i8NProvider);
				}
			}
		}
	}
}
