package com.khubla.pragmatach.plugin.adminapp;

import java.util.Map;

import com.khubla.pragmatach.framework.annotation.Controller;
import com.khubla.pragmatach.framework.annotation.Route;
import com.khubla.pragmatach.framework.annotation.View;
import com.khubla.pragmatach.framework.api.PragmatachException;
import com.khubla.pragmatach.framework.api.Response;
import com.khubla.pragmatach.framework.servlet.PragmatachServlet;
import com.khubla.pragmatach.plugin.freemarker.FreemarkerController;

/**
 * @author tome
 */
@Controller(name = "pragmatachShowRoutesController")
@View(view = "configuration.html")
public class ShowConfigurationController extends FreemarkerController {
   private Map<String, String> configuration;

   public Map<String, String> getConfiguration() {
      return configuration;
   }

   @Route(uri = "/pragmatach/admin/configuration")
   public Response render() throws PragmatachException {
      configuration = PragmatachServlet.getConfiguration().getAll();
      return super.render();
   }

   public void setConfiguration(Map<String, String> configuration) {
      this.configuration = configuration;
   }
}
