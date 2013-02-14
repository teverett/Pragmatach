package com.khubla.pragmatach.plugin.adminapp;

import com.khubla.pragmatach.framework.annotation.Controller;
import com.khubla.pragmatach.framework.annotation.Route;
import com.khubla.pragmatach.framework.api.PragmatachException;
import com.khubla.pragmatach.framework.api.Request;
import com.khubla.pragmatach.framework.api.Response;
import com.khubla.pragmatach.plugin.freemarker.FreemarkerController;
import com.khubla.pragmatach.plugin.freemarker.FreemarkerTemplate;

/**
 * @author tome
 */
@Controller
@FreemarkerTemplate(template = "admin.ftl")
public class AdminController extends FreemarkerController {
   /**
    * ctor
    */
   public AdminController(Request request) {
      super(request);
   }

   @Route(uri = "/pragmatach")
   public Response render() throws PragmatachException {
      return super.render();
   }
}