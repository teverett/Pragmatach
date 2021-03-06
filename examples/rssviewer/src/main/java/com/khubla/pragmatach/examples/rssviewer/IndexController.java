package com.khubla.pragmatach.examples.rssviewer;

import com.khubla.pragmatach.framework.annotation.Controller;
import com.khubla.pragmatach.framework.annotation.Route;
import com.khubla.pragmatach.framework.annotation.View;
import com.khubla.pragmatach.framework.api.PragmatachException;
import com.khubla.pragmatach.framework.api.Response;
import com.khubla.pragmatach.plugin.freemarker.FreemarkerController;

@Controller()
public class IndexController extends FreemarkerController {
   @Override
   @View(view = "index.html")
   @Route(uri = "/")
   public Response render() throws PragmatachException {
      return super.render();
   }
}