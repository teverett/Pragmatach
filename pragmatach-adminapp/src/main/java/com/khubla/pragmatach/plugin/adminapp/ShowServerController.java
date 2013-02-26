package com.khubla.pragmatach.plugin.adminapp;

import com.khubla.pragmatach.framework.annotation.Controller;
import com.khubla.pragmatach.framework.annotation.Route;
import com.khubla.pragmatach.framework.annotation.View;
import com.khubla.pragmatach.framework.api.PragmatachException;
import com.khubla.pragmatach.framework.api.Response;

/**
 * @author tome
 */
@Controller(name = "pragmatachShowServerController")
@View(view = "pragmatach/admin/server.html")
public class ShowServerController extends AbstractAdminController {
   /**
    * server info
    */
   private String serverinfo;

   public String getServerinfo() {
      return serverinfo;
   }

   @Route(uri = "/pragmatach/admin/server")
   public Response render() throws PragmatachException {
      serverinfo = getRequest().getHttpServletRequest().getSession().getServletContext().getServerInfo();
      return super.render();
   }

   public void setServerinfo(String serverinfo) {
      this.serverinfo = serverinfo;
   }
}
