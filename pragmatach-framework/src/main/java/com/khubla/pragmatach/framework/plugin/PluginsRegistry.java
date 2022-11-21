package com.khubla.pragmatach.framework.plugin;

import java.util.*;

import org.reflections.*;
import org.slf4j.*;

import com.khubla.pragmatach.framework.api.*;

/**
 * @author tome
 */
public class PluginsRegistry {
	/**
	 * logger
	 */
	private static final Logger logger = LoggerFactory.getLogger(PluginsRegistry.class);
	/**
	 * plugin jars
	 */
	private static Map<String, Plugin> plugins = new HashMap<String, Plugin>();

	public static Map<String, Plugin> getPlugins() {
		return plugins;
	}

	/**
	 * do the scan
	 */
	public static void scan() throws PragmatachException {
		try {
			final Reflections reflections = new Reflections("com.khubla.pragmatach");
			final Set<Class<? extends Plugin>> pluginClasses = reflections.getSubTypesOf(Plugin.class);
			if ((null != pluginClasses) && (pluginClasses.size() > 0)) {
				for (final Class<? extends Plugin> clazz : pluginClasses) {
					final Plugin plugin = clazz.getDeclaredConstructor().newInstance();
					if (null != plugin) {
						logger.info("Found plugin: " + plugin.getName());
						plugins.put(plugin.getName(), plugin);
					}
				}
			}
		} catch (final Exception e) {
			throw new PragmatachException("Exception in scan", e);
		}
	}
}
