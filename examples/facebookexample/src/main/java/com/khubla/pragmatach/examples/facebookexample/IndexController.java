package com.khubla.pragmatach.examples.facebookexample;

import com.khubla.pragmatach.framework.annotation.Controller;
import com.khubla.pragmatach.framework.annotation.Route;
import com.khubla.pragmatach.framework.annotation.View;
import com.khubla.pragmatach.framework.api.PragmatachException;
import com.khubla.pragmatach.framework.api.Response;
import com.khubla.pragmatach.plugin.facebook.FacebookLoginController;

@Controller(name = "IndexController")
@View(view = "index.html")
public class IndexController extends FacebookLoginController {
   @Route(uri = "/")
   public Response render() throws PragmatachException {
      return super.render();
   }
}