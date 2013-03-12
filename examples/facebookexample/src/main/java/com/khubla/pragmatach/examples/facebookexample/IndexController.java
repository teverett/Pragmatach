package com.khubla.pragmatach.examples.facebookexample;

import com.khubla.pragmatach.framework.annotation.Controller;
import com.khubla.pragmatach.framework.annotation.Route;
import com.khubla.pragmatach.framework.annotation.Route.HttpMethod;
import com.khubla.pragmatach.framework.annotation.View;
import com.khubla.pragmatach.framework.api.PragmatachException;
import com.khubla.pragmatach.framework.api.Response;
import com.khubla.pragmatach.plugin.facebook.FacebookLoginController;

@Controller(name = "IndexController")
@View(view = "index.html")
public class IndexController extends FacebookLoginController {
   /**
    * ctor
    */
   public IndexController() {
      super("/");
   }

   /**
    * the main view
    */
   @Route(uri = "/")
   public Response render() throws PragmatachException {
      return super.render();
   }

   /**
    * the FB callback
    */
   @Route(uri = "plugins/facebook/dologin/", method = HttpMethod.get)
   public Response doLogin() throws PragmatachException {
      return super.doLogin();
   }
}