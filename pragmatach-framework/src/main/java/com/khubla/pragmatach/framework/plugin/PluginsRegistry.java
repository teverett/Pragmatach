package com.khubla.pragmatach.framework.plugin;

import java.util.*;

import javax.servlet.*;

import org.slf4j.*;

import com.khubla.pragmatach.framework.annotation.*;
import com.khubla.pragmatach.framework.api.*;
import com.khubla.pragmatach.framework.resourceloader.*;
import com.khubla.pragmatach.framework.scanner.*;

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
	public static void scan(ServletContext servletContext) throws PragmatachException {
		try {
			Set<Class<?>> pluginClasses = AnnotationScanner.getAllClasses(PluginExtension.class);
			if ((null != pluginClasses) && (pluginClasses.size() > 0)) {
				for (final Class<?> clazz : pluginClasses) {
					final Plugin plugin = (Plugin) clazz.getDeclaredConstructor().newInstance();
					if (null != plugin) {
						logger.info("Found plugin: " + plugin.getName());
						plugin.setPluginContext(new PluginContextImpl(new DefaultResourceLoaderImpl(servletContext)));
						plugins.put(plugin.getName(), plugin);
					}
				}
			}
		} catch (final Exception e) {
			throw new PragmatachException("Exception in scan", e);
		}
	}
}
