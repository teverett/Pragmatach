package com.khubla.pragmatach.framework.controller.impl.system;

import java.util.HashMap;
import java.util.Map;

import com.khubla.pragmatach.framework.annotation.Controller;
import com.khubla.pragmatach.framework.annotation.Route;
import com.khubla.pragmatach.framework.api.PragmatachException;
import com.khubla.pragmatach.framework.api.Response;
import com.khubla.pragmatach.framework.controller.Controllers;
import com.khubla.pragmatach.framework.controller.impl.SimpleTemplateController;

/**
 * @author tome
 */
@Controller(name = "pragmatachShowControllersController")
public class ShowControllersController extends SimpleTemplateController {
   private String getRequestControllers() throws PragmatachException {
      String ret = "";
      for (final Class<?> clazz : Controllers.getInstance().getControllers()) {
         final Controller controller = clazz.getAnnotation(Controller.class);
         if (controller.scope() == Controller.Scope.request) {
            ret += controller.name() + "  (" + clazz.getName() + ")\n";
         }
      }
      return ret;
   }

   private String getSessionControllers() throws PragmatachException {
      String ret = "";
      for (final Class<?> clazz : Controllers.getInstance().getControllers()) {
         final Controller controller = clazz.getAnnotation(Controller.class);
         if (controller.scope() == Controller.Scope.session) {
            ret += controller.name() + "  (" + clazz.getName() + ")\n";
         }
      }
      return ret;
   }

   @Route(uri = "/pragmatach/controllers")
   public Response render() throws PragmatachException {
      final Map<String, String> parameters = new HashMap<String, String>();
      parameters.put("requestscoped", getRequestControllers());
      parameters.put("sessionscoped", getSessionControllers());
      return template("system/controllers.html", parameters);
   }
}
