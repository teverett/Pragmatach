package com.khubla.pragmatach.framework.controller.impl.system;

import java.util.HashMap;
import java.util.Map;

import com.khubla.pragmatach.framework.annotation.Controller;
import com.khubla.pragmatach.framework.annotation.Route;
import com.khubla.pragmatach.framework.api.PragmatachException;
import com.khubla.pragmatach.framework.api.Response;
import com.khubla.pragmatach.framework.controller.impl.SimpleTemplateController;
import com.khubla.pragmatach.framework.servlet.PragmatachServlet;

/**
 * @author tome
 */
@Controller(name = "pragmatachShowRoutesController")
public class ShowConfigurationController extends SimpleTemplateController {
   private String getConfiguration() throws PragmatachException {
      final Map<String, String> config = PragmatachServlet.getConfiguration().getAll();
      if (null != config) {
         String ret = "";
         for (final String key : config.keySet()) {
            ret += key + " : " + config.get(key) + "\n";
         }
         return ret;
      } else {
         return null;
      }
   }

   @Route(uri = "/pragmatach/configuration")
   public Response render() throws PragmatachException {
      final Map<String, String> parameters = new HashMap<String, String>();
      parameters.put("configuration", getConfiguration());
      return template("system/configuration.html", parameters);
   }
}
