package com.khubla.pragmatach.plugin.adminapp;

import java.util.List;

import com.khubla.pragmatach.framework.annotation.Controller;
import com.khubla.pragmatach.framework.annotation.Route;
import com.khubla.pragmatach.framework.annotation.View;
import com.khubla.pragmatach.framework.api.PragmatachException;
import com.khubla.pragmatach.framework.api.Response;
import com.khubla.pragmatach.framework.router.PragmatachRoute;
import com.khubla.pragmatach.framework.router.PragmatachRoutes;
import com.khubla.pragmatach.plugin.freemarker.FreemarkerController;

/**
 * @author tome
 */
@Controller(name = "pragmatachShowRoutesController")
@View(view = "routes.html")
public class ShowRoutesController extends FreemarkerController {
   private List<PragmatachRoute> POSTRoutes;
   private List<PragmatachRoute> GETROUTES;

   public List<PragmatachRoute> getGETROUTES() {
      return GETROUTES;
   }

   public List<PragmatachRoute> getPOSTRoutes() {
      return POSTRoutes;
   }

   @Route(uri = "/pragmatach/admin/routes")
   public Response render() throws PragmatachException {
      POSTRoutes = PragmatachRoutes.getInstance().getPOSTRoutes();
      GETROUTES = PragmatachRoutes.getInstance().getGETRoutes();
      return super.render();
   }

   public void setGETROUTES(List<PragmatachRoute> gETROUTES) {
      GETROUTES = gETROUTES;
   }

   public void setPOSTRoutes(List<PragmatachRoute> pOSTRoutes) {
      POSTRoutes = pOSTRoutes;
   }
}
