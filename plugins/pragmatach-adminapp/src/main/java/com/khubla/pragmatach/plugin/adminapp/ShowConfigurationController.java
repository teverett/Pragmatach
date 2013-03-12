package com.khubla.pragmatach.plugin.adminapp;

import java.util.Map;

import com.khubla.pragmatach.framework.annotation.Controller;
import com.khubla.pragmatach.framework.annotation.Route;
import com.khubla.pragmatach.framework.annotation.View;
import com.khubla.pragmatach.framework.api.PragmatachException;
import com.khubla.pragmatach.framework.api.Response;
import com.khubla.pragmatach.framework.application.Application;

/**
 * @author tome
 */
@Controller(name = "pragmatachShowRoutesController")
@View(view = "pragmatach/admin/configuration.html")
public class ShowConfigurationController extends SecuredAdminController {
   private Map<String, String> configuration;

   public Map<String, String> getConfiguration() {
      return configuration;
   }

   @Route(uri = "/pragmatach/admin/configuration")
   public Response render() throws PragmatachException {
      configuration = Application.getConfiguration().getAll();
      return super.render();
   }

   public void setConfiguration(Map<String, String> configuration) {
      this.configuration = configuration;
   }
}
