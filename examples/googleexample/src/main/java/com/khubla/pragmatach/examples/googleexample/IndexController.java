package com.khubla.pragmatach.examples.googleexample;

import com.khubla.pragmatach.framework.annotation.Controller;
import com.khubla.pragmatach.framework.annotation.Route;
import com.khubla.pragmatach.framework.annotation.Route.HttpMethod;
import com.khubla.pragmatach.framework.annotation.View;
import com.khubla.pragmatach.framework.api.PragmatachException;
import com.khubla.pragmatach.framework.api.Response;
import com.khubla.pragmatach.plugin.google.GoogleLoginController;

@Controller(name = "IndexController")
@View(view = "index.html")
public class IndexController extends GoogleLoginController {
   /**
    * ctor
    */
   public IndexController() throws PragmatachException {
      super("/");
   }

   /**
    * the FB callback
    */
   @Route(uri = "plugins/google/dologin/", method = HttpMethod.get)
   public Response doLogin() throws PragmatachException {
      return super.doLogin();
   }

   /**
    * the main view
    */
   @Route(uri = "/")
   public Response render() throws PragmatachException {
      return super.render();
   }
}