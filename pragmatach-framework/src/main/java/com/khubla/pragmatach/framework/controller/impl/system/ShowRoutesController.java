package com.khubla.pragmatach.framework.controller.impl.system;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.khubla.pragmatach.framework.annotation.Route;
import com.khubla.pragmatach.framework.api.PragmatachException;
import com.khubla.pragmatach.framework.api.Response;
import com.khubla.pragmatach.framework.controller.impl.SimpleTemplateController;
import com.khubla.pragmatach.framework.router.PragmatachRoute;
import com.khubla.pragmatach.framework.router.PragmatachRoutes;

/**
 * @author tome
 */
public class ShowRoutesController extends SimpleTemplateController {
   @Route(uri = "/pragmatach/routes")
   public Response render() throws PragmatachException {
      Map<String, String> parameters = new HashMap<String, String>();
      parameters.put("getroutes", getGETRoutes());
      parameters.put("postroutes", getPOSTRoutes());
      return template("system/routes.html", parameters);
   }

   private String getGETRoutes() throws PragmatachException {
      String ret = "";
      List<PragmatachRoute> routes = PragmatachRoutes.getInstance().getGETRoutes();
      for (PragmatachRoute route : routes) {
         ret += route.getRoute().uri() + " " + route.getMethod().getDeclaringClass().getName() + ":" + route.getMethod().getName() + "(" + getParameterTypes(route.getMethod()) + ")\n";
      }
      return ret;
   }

   private String getPOSTRoutes() throws PragmatachException {
      String ret = "";
      List<PragmatachRoute> routes = PragmatachRoutes.getInstance().getPOSTRoutes();
      for (PragmatachRoute route : routes) {
         ret += route.getRoute().uri() + " " + route.getMethod().getDeclaringClass().getName() + ":" + route.getMethod().getName() + "(" + getParameterTypes(route.getMethod()) + ")\n";
      }
      return ret;
   }

   private String getParameterTypes(Method method) {
      String ret = "";
      int i = 0;
      Class<?>[] types = method.getParameterTypes();
      if (null != types) {
         for (Class<?> type : types) {
            if (i != 0) {
               ret += ",";
            }
            i++;
            ret += type.getName();
         }
      }
      return ret;
   }
}
