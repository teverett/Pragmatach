package com.khubla.pragmatach.examples.cacheexample;

import java.util.Date;

import com.khubla.pragmatach.framework.annotation.CacheControl;
import com.khubla.pragmatach.framework.annotation.Controller;
import com.khubla.pragmatach.framework.annotation.Route;
import com.khubla.pragmatach.framework.annotation.View;
import com.khubla.pragmatach.framework.api.PragmatachException;
import com.khubla.pragmatach.framework.api.Response;
import com.khubla.pragmatach.plugin.freemarker.FreemarkerController;

@Controller
@View(view = "index.ftl")
@CacheControl(maxAge = 10)
public class IndexController extends FreemarkerController {
   public String getTime() {
      return new Date().toString();
   }

   @Route(uri = "/")
   public Response render() throws PragmatachException {
      return super.render();
   }
}