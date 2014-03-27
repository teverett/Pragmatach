package com.khubla.pragmatach.plugin.freemarker;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

import javax.servlet.ServletContext;

import com.khubla.pragmatach.framework.resourceloader.DefaultResourceLoaderImpl;
import com.khubla.pragmatach.framework.resourceloader.ResourceLoader;

import freemarker.cache.TemplateLoader;

/**
 * @author tome
 */
public class PragmatachTemplateLoader implements TemplateLoader {
	/**
	 * ServletContext
	 */
	private final ServletContext servletContext;

	public PragmatachTemplateLoader(ServletContext servletContext) {
		this.servletContext = servletContext;
	}

	@Override
	public void closeTemplateSource(Object object) throws IOException {
		final InputStream inputStream = (InputStream) object;
		inputStream.close();
	}

	@Override
	public Object findTemplateSource(String template) throws IOException {
		try {
			final ResourceLoader resourceLoader = new DefaultResourceLoaderImpl(
					servletContext);
			final InputStream is = resourceLoader.getResource(template);
			return is;
		} catch (final Exception e) {
			throw new IOException(e);
		}
	}

	@Override
	public long getLastModified(Object object) {
		return 0;
	}

	@Override
	public Reader getReader(Object object, String string) throws IOException {
		final InputStream inputStream = (InputStream) object;
		return new InputStreamReader(inputStream, "UTF-8");
	}
}
