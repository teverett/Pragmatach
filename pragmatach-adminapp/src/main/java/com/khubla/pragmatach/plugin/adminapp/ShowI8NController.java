package com.khubla.pragmatach.plugin.adminapp;

import java.util.Set;

import com.khubla.pragmatach.framework.annotation.Controller;
import com.khubla.pragmatach.framework.annotation.Route;
import com.khubla.pragmatach.framework.annotation.View;
import com.khubla.pragmatach.framework.api.I8NProvider;
import com.khubla.pragmatach.framework.api.PragmatachException;
import com.khubla.pragmatach.framework.api.Response;
import com.khubla.pragmatach.framework.i8n.I8NProviders;

/**
 * @author tome
 */
@Controller(name = "pragmatachShowI8NController")
@View(view = "pragmatach/admin/i8n.html")
public class ShowI8NController extends SecuredAdminController {
   /**
    * i8n providers
    */
   private Set<I8NProvider> providers;

   public Set<I8NProvider> getProviders() {
      return providers;
   }

   @Route(uri = "/pragmatach/admin/i8n")
   public Response render() throws PragmatachException {
      providers = I8NProviders.getInstance().providers;
      return super.render();
   }

   public void setProviders(Set<I8NProvider> providers) {
      this.providers = providers;
   }
}
