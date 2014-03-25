package com.khubla.pragmatach.plugin.adminapp;

import java.io.InputStream;
import java.util.Properties;

import com.khubla.pragmatach.framework.annotation.Controller;
import com.khubla.pragmatach.framework.annotation.Route;
import com.khubla.pragmatach.framework.annotation.View;
import com.khubla.pragmatach.framework.api.PragmatachException;
import com.khubla.pragmatach.framework.api.Response;

/**
 * @author tome
 */
@Controller(name = "pragmatachShowApplicationController")
@View(view = "pragmatach/admin/application.html")
public class ShowApplicationController extends SecuredAdminController {
	/**
	 * application version from maven pom
	 */
	private String applicationVersion;
	/**
	 * build date from maven pom
	 */
	private String buildDate;

	private String findApplicationBuildDate() throws PragmatachException {
		try {
			InputStream is = null;
			try {
				is = ShowApplicationController.class
						.getResourceAsStream("/version.properties");
				if (null != is) {
					final Properties properties = new Properties();
					properties.load(is);
					return properties.getProperty("maven.builddate");
				}
				return null;
			} finally {
				if (null != is) {
					is.close();
				}
			}
		} catch (final Exception e) {
			throw new PragmatachException(
					"Exception in findApplicationBuildDate", e);
		}
	}

	private String findApplicationVersion() throws PragmatachException {
		try {
			InputStream is = null;
			try {
				is = ShowApplicationController.class
						.getResourceAsStream("/version.properties");
				if (null != is) {
					final Properties properties = new Properties();
					properties.load(is);
					return properties.getProperty("maven.version");
				}
				return null;
			} finally {
				if (null != is) {
					is.close();
				}
			}
		} catch (final Exception e) {
			throw new PragmatachException(
					"Exception in findApplicationVersion", e);
		}
	}

	public String getApplicationVersion() {
		return applicationVersion;
	}

	public String getBuildDate() {
		return buildDate;
	}

	@Route(uri = "/pragmatach/admin/application")
	public Response render() throws PragmatachException {
		applicationVersion = findApplicationVersion();
		buildDate = findApplicationBuildDate();
		return super.render();
	}

	public void setApplicationVersion(String applicationVersion) {
		this.applicationVersion = applicationVersion;
	}

	public void setBuildDate(String buildDate) {
		this.buildDate = buildDate;
	}
}
