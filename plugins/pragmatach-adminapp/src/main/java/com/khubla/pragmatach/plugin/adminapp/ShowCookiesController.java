package com.khubla.pragmatach.plugin.adminapp;

import java.util.Hashtable;

import com.khubla.pragmatach.framework.annotation.Controller;
import com.khubla.pragmatach.framework.annotation.Route;
import com.khubla.pragmatach.framework.annotation.Route.HttpMethod;
import com.khubla.pragmatach.framework.annotation.View;
import com.khubla.pragmatach.framework.api.PragmatachException;
import com.khubla.pragmatach.framework.api.Response;

/**
 * @author tome
 */
@Controller(name = "pragmatachShowCookiesController")
@View(view = "pragmatach/admin/cookies.html")
public class ShowCookiesController extends SecuredAdminController {
   /**
    * cookies
    */
   private Hashtable<String, String> cookies;

   @Route(uri = "/pragmatach/admin/cookies/clearCookies", method = HttpMethod.post)
   public Response clearCookies() throws PragmatachException {
      getRequest().getCookies().clearAll();
      return super.render();
   }

   public Hashtable<String, String> getCookies() {
      return cookies;
   }

   @Route(uri = "/pragmatach/admin/cookies")
   public Response render() throws PragmatachException {
      cookies = getRequest().getCookies().getCookies();
      return super.render();
   }

   public void setCookies(Hashtable<String, String> cookies) {
      this.cookies = cookies;
   }
}
