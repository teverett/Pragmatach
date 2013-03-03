package com.khubla.pragmatach.plugin.adminapp;

import java.util.Map;

import com.khubla.pragmatach.framework.annotation.Controller;
import com.khubla.pragmatach.framework.annotation.Route;
import com.khubla.pragmatach.framework.annotation.View;
import com.khubla.pragmatach.framework.api.PragmatachException;
import com.khubla.pragmatach.framework.api.Response;

/**
 * @author tome
 */
@Controller(name = "pragmatachEnvironmentController")
@View(view = "pragmatach/admin/environment.html")
public class ShowEnvironmentController extends SecuredAdminController {
   /**
    * env
    */
   private Map<String, String> environment;

   public Map<String, String> getEnvironment() {
      return environment;
   }

   @Route(uri = "/pragmatach/admin/environment")
   public Response render() throws PragmatachException {
      environment = System.getenv();
      return super.render();
   }

   public void setEnvironment(Map<String, String> environment) {
      this.environment = environment;
   }
}
