package com.khubla.pragmatach.framework.controller.impl.system;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.khubla.pragmatach.framework.annotation.Controller;
import com.khubla.pragmatach.framework.annotation.Route;
import com.khubla.pragmatach.framework.api.PragmatachException;
import com.khubla.pragmatach.framework.api.Response;
import com.khubla.pragmatach.framework.controller.impl.SimpleTemplateController;
import com.khubla.pragmatach.framework.router.PragmatachRoute;
import com.khubla.pragmatach.framework.router.PragmatachRoutes;

/**
 * @author tome
 */
@Controller(name = "pragmatachShowRoutesController")
public class ShowRoutesController extends SimpleTemplateController {
   private String getGETRoutes() throws PragmatachException {
      String ret = "";
      final List<PragmatachRoute> routes = PragmatachRoutes.getInstance().getGETRoutes();
      for (final PragmatachRoute route : routes) {
         ret += route.getRoute().uri() + " " + route.getMethod().getDeclaringClass().getName() + ":" + route.getMethod().getName() + "(" + getParameterTypes(route.getMethod()) + ")\n";
      }
      return ret;
   }

   private String getParameterTypes(Method method) {
      String ret = "";
      int i = 0;
      final Class<?>[] types = method.getParameterTypes();
      if (null != types) {
         for (final Class<?> type : types) {
            if (i != 0) {
               ret += ",";
            }
            i++;
            ret += type.getName();
         }
      }
      return ret;
   }

   private String getPOSTRoutes() throws PragmatachException {
      String ret = "";
      final List<PragmatachRoute> routes = PragmatachRoutes.getInstance().getPOSTRoutes();
      for (final PragmatachRoute route : routes) {
         ret += route.getRoute().uri() + " " + route.getMethod().getDeclaringClass().getName() + ":" + route.getMethod().getName() + "(" + getParameterTypes(route.getMethod()) + ")\n";
      }
      return ret;
   }

   @Route(uri = "/pragmatach/routes")
   public Response render() throws PragmatachException {
      final Map<String, String> parameters = new HashMap<String, String>();
      parameters.put("getroutes", getGETRoutes());
      parameters.put("postroutes", getPOSTRoutes());
      return template("system/routes.html", parameters);
   }
}
