package com.khubla.pragmatach.plugin.adminapp;

import java.util.Map;

import com.khubla.pragmatach.framework.annotation.Controller;
import com.khubla.pragmatach.framework.annotation.Route;
import com.khubla.pragmatach.framework.annotation.View;
import com.khubla.pragmatach.framework.api.PragmatachException;
import com.khubla.pragmatach.framework.api.Response;
import com.khubla.pragmatach.framework.plugin.Plugin;
import com.khubla.pragmatach.framework.plugin.Plugins;

/**
 * @author tome
 */
@Controller(name = "pragmatachShowPluginsController")
@View(view = "pragmatach/admin/plugins.html")
public class ShowPluginsController extends AbstractAdminController {
   /**
    * plugins
    */
   private Map<String, Plugin> plugins;

   public Map<String, Plugin> getPlugins() {
      return plugins;
   }

   @Route(uri = "/pragmatach/admin/plugins")
   public Response render() throws PragmatachException {
      plugins = Plugins.getPlugins();
      return super.render();
   }

   public void setPlugins(Map<String, Plugin> plugins) {
      this.plugins = plugins;
   }
}
