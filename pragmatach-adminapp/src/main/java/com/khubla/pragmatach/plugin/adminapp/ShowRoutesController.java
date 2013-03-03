package com.khubla.pragmatach.plugin.adminapp;

import java.util.List;

import com.khubla.pragmatach.framework.annotation.Controller;
import com.khubla.pragmatach.framework.annotation.Route;
import com.khubla.pragmatach.framework.annotation.View;
import com.khubla.pragmatach.framework.api.PragmatachException;
import com.khubla.pragmatach.framework.api.Response;
import com.khubla.pragmatach.framework.router.PragmatachRoute;
import com.khubla.pragmatach.framework.router.PragmatachRoutes;

/**
 * @author tome
 */
@Controller(name = "pragmatachShowRoutesController")
@View(view = "pragmatach/admin/routes.html")
public class ShowRoutesController extends SecuredAdminController {
   /*
    * the POST routes
    */
   private List<PragmatachRoute> POSTRoutes;
   /*
    * the GET routes
    */
   private List<PragmatachRoute> GETRoutes;

   public List<PragmatachRoute> getGETRoutes() {
      return GETRoutes;
   }

   public List<PragmatachRoute> getPOSTRoutes() {
      return POSTRoutes;
   }

   public String getRouteParameters(PragmatachRoute pragmatachRoute) {
      String ret = "";
      int i = 0;
      final Class<?>[] types = pragmatachRoute.getMethod().getParameterTypes();
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

   @Route(uri = "/pragmatach/admin/routes")
   public Response render() throws PragmatachException {
      POSTRoutes = PragmatachRoutes.getInstance().getPOSTRoutes();
      GETRoutes = PragmatachRoutes.getInstance().getGETRoutes();
      return super.render();
   }

   public void setGETRoutes(List<PragmatachRoute> gETRoutes) {
      GETRoutes = gETRoutes;
   }

   public void setPOSTRoutes(List<PragmatachRoute> pOSTRoutes) {
      POSTRoutes = pOSTRoutes;
   }
}
