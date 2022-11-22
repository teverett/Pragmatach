package com.khubla.pragmatach.framework.resourceloader;

import java.io.*;
import java.util.*;

import javax.servlet.*;

import org.slf4j.*;

import com.khubla.pragmatach.framework.api.*;
import com.khubla.pragmatach.framework.plugin.*;

/**
 * @author tome
 */
public class DefaultResourceLoaderImpl implements ResourceLoader {
	/**
	 * logger
	 */
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	/**
	 * servlet context
	 */
	private final ServletContext servletContext;

	/**
	 * ctor
	 */
	public DefaultResourceLoaderImpl(ServletContext servletContext) {
		this.servletContext = servletContext;
	}

	/**
	 * get a resource using the servlet's class loader
	 */
	@Override
	public InputStream getResource(String resource) throws PragmatachException {
		try {
			if ((resource.contains("WEB-INF")) || (resource.contains("META-INF")) || (resource.startsWith("."))) {
				return null;
			} else {
				String resourcePath = resource;
				if (!resourcePath.startsWith("/")) {
					resourcePath = "/" + resourcePath;
				}
				/*
				 * try loading from the web app
				 */
				InputStream ret = servletContext.getResourceAsStream(resourcePath);
				/*
				 * try loading locally
				 */
				if (null == ret) {
					ret = DefaultResourceLoaderImpl.class.getResourceAsStream(resourcePath);
				}
				/*
				 * ok, can't find, try the plugins
				 */
				if (null == ret) {
					/*
					 * get the plugins
					 */
					final Map<String, Plugin> plugins = PluginsRegistry.getPlugins();
					if (null != plugins) {
						for (final Plugin plugin : plugins.values()) {
							// ret = plugin.getResource(resource);
							if (null != ret) {
								/*
								 * found it!
								 */
								break;
							}
						}
					}
				}
				/*
				 * unable to find, log that
				 */
				if (null == ret) {
					logger.info("Unable to load resource '" + resource + "'");
				}
				/*
				 * done
				 */
				return ret;
			}
		} catch (final Exception e) {
			throw new PragmatachException("Exception in getResource", e);
		}
	}
}
