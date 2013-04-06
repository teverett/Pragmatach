package com.khubla.pragmatach.plugin.adminapp;

import java.util.List;

import com.khubla.pragmatach.framework.annotation.Controller;
import com.khubla.pragmatach.framework.annotation.Route;
import com.khubla.pragmatach.framework.annotation.View;
import com.khubla.pragmatach.framework.api.PragmatachException;
import com.khubla.pragmatach.framework.api.Response;
import com.khubla.pragmatach.framework.application.Application;
import com.khubla.pragmatach.framework.router.PragmatachRoute;

/**
 * @author tome
 */
@Controller()
@View(view = "pragmatach/admin/exportroutes.html")
public class ExportRoutesController extends SecuredAdminController {
   private List<PragmatachRoute> routes;

   public Controller getControllerAnnotation(Class<?> clazz) {
      return clazz.getAnnotation(Controller.class);
   }

   public List<PragmatachRoute> getRoutes() {
      return routes;
   }

   @Route(uri = "/pragmatach/admin/exportroutes")
   public Response render() throws PragmatachException {
      routes = Application.getRoutes().getAllRoutes();
      return super.render();
   }

   public void setRoutes(List<PragmatachRoute> routes) {
      this.routes = routes;
   }
}
