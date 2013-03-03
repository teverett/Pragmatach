package com.khubla.pragmatach.plugin.adminapp;

import java.util.Map;

import com.khubla.pragmatach.framework.annotation.Controller;
import com.khubla.pragmatach.framework.annotation.Route;
import com.khubla.pragmatach.framework.annotation.View;
import com.khubla.pragmatach.framework.api.PragmatachException;
import com.khubla.pragmatach.framework.api.Response;
import com.khubla.pragmatach.framework.plugin.PluginDescriptor;
import com.khubla.pragmatach.framework.plugin.PluginDescriptors;

/**
 * @author tome
 */
@Controller(name = "pragmatachShowPluginsController")
@View(view = "pragmatach/admin/plugins.html")
public class ShowPluginsController extends SecuredAdminController {
   /**
    * plugins
    */
   private Map<String, PluginDescriptor> plugins;

   public Map<String, PluginDescriptor> getPlugins() {
      return plugins;
   }

   @Route(uri = "/pragmatach/admin/plugins")
   public Response render() throws PragmatachException {
      plugins = PluginDescriptors.getPlugins();
      return super.render();
   }

   public void setPlugins(Map<String, PluginDescriptor> plugins) {
      this.plugins = plugins;
   }
}
