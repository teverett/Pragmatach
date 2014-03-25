package com.khubla.pragmatach.framework.plugin;

import java.io.InputStream;
import java.net.JarURLConnection;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Properties;
import java.util.jar.Manifest;

import javax.servlet.ServletContext;

import com.khubla.pragmatach.framework.api.Plugin;
import com.khubla.pragmatach.framework.api.PragmatachException;
import com.khubla.pragmatach.framework.resourceloader.DefaultResourceLoaderImpl;

/**
 * @author tome
 */
public class PluginDescriptor {
	/**
	 * name
	 */
	public static final String ACTIVATOR = "activator";
	/**
	 * the jar
	 */
	private final URL url;
	/**
	 * the properties
	 */
	private final Properties properties;
	/**
	 * plugin
	 */
	private final Plugin plugin;

	public PluginDescriptor(URL url, InputStream inputStream,
			ServletContext servletContext) throws PragmatachException {
		try {
			this.url = url;
			properties = new Properties();
			properties.load(inputStream);
			plugin = findPlugin(servletContext);
		} catch (final Exception e) {
			throw new PragmatachException(e);
		}
	}

	private Plugin findPlugin(ServletContext servletContext)
			throws PragmatachException {
		try {
			final String activatorClassName = properties.getProperty(ACTIVATOR);
			if (null != activatorClassName) {
				final Class<?> clazz = Class.forName(activatorClassName);
				if (null != clazz) {
					final Plugin ret = (Plugin) clazz.newInstance();
					ret.setPluginContext(new PluginContextImpl(
							new DefaultResourceLoaderImpl(servletContext)));
					return ret;
				}
			}
			return null;
		} catch (final Exception e) {
			throw new PragmatachException(e);
		}
	}

	/**
	 * get the plugin jar manifest
	 */
	public Manifest getManifest() throws PragmatachException {
		try {
			final JarURLConnection jarURLConnection = (JarURLConnection) url
					.openConnection();
			if (null != jarURLConnection) {
				return jarURLConnection.getManifest();
			}
			return null;
		} catch (final Exception e) {
			throw new PragmatachException("Exception in getManifest", e);
		}
	}

	public String getName() {
		return plugin.getName();
	}

	public Plugin getPlugin() {
		return plugin;
	}

	public Properties getProperties() {
		return properties;
	}

	/**
	 * get a resource from this plugin's jar
	 */
	public InputStream getResource(String name) throws PragmatachException {
		try {
			final ClassLoader classLoader = new URLClassLoader(
					new URL[] { url });
			return classLoader.getResourceAsStream(name);
		} catch (final Exception e) {
			throw new PragmatachException("Exception in getResource", e);
		}
	}

	public URL getUrl() {
		return url;
	}
}
