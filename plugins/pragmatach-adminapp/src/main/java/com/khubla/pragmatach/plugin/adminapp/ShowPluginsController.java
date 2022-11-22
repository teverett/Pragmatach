package com.khubla.pragmatach.plugin.adminapp;

import java.util.*;

import com.khubla.pragmatach.framework.annotation.*;
import com.khubla.pragmatach.framework.api.*;
import com.khubla.pragmatach.framework.api.Plugin;
import com.khubla.pragmatach.framework.plugin.*;

/**
 * @author tome
 */
@Controller(name = "pragmatachShowPluginsController")
@View(view = "pragmatach/admin/plugins.html")
public class ShowPluginsController extends SecuredAdminController {
	/**
	 * plugins
	 */
	private Map<String, Plugin> plugins;

	public Map<String, Plugin> getPlugins() {
		return plugins;
	}

	@Route(uri = "/pragmatach/admin/plugins")
	public Response render() throws PragmatachException {
		plugins = PluginsRegistry.getPlugins();
		return super.render();
	}

	public void setPlugins(Map<String, Plugin> plugins) {
		this.plugins = plugins;
	}
}
