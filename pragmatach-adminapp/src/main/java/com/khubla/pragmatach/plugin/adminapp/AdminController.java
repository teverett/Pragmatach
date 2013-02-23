package com.khubla.pragmatach.plugin.adminapp;

import com.khubla.pragmatach.framework.annotation.Controller;
import com.khubla.pragmatach.framework.annotation.Route;
import com.khubla.pragmatach.framework.annotation.View;
import com.khubla.pragmatach.framework.api.PragmatachException;
import com.khubla.pragmatach.framework.api.Response;

/**
 * @author tome
 */
@Controller(name = "pragmatachAdminController")
@View(view = "pragmatach/admin/admin.html")
public class AdminController extends AbstractAdminController {
   /**
    * ctor
    */
   public AdminController() {
   }

   @Route(uri = "/pragmatach/admin")
   public Response render() throws PragmatachException {
      return super.render();
   }
}
