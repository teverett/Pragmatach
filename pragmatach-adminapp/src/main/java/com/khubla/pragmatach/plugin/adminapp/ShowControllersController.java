package com.khubla.pragmatach.plugin.adminapp;

import java.util.Set;

import com.khubla.pragmatach.framework.annotation.Controller;
import com.khubla.pragmatach.framework.annotation.Route;
import com.khubla.pragmatach.framework.annotation.View;
import com.khubla.pragmatach.framework.api.PragmatachException;
import com.khubla.pragmatach.framework.api.Response;
import com.khubla.pragmatach.framework.controller.Controllers;
import com.khubla.pragmatach.plugin.freemarker.FreemarkerController;

/**
 * @author tome
 */
@Controller(name = "pragmatachShowControllersController")
@View(view = "pragmatach/admin/controllers.html")
public class ShowControllersController extends FreemarkerController {
   private Set<Class<?>> controllers;

   public Controller getControllerAnnotation(Class<?> clazz) {
      return clazz.getAnnotation(Controller.class);
   }

   public Set<Class<?>> getControllers() {
      return controllers;
   }

   @Route(uri = "/pragmatach/admin/controllers")
   public Response render() throws PragmatachException {
      controllers = Controllers.getInstance().getControllers();
      return super.render();
   }

   public void setControllers(Set<Class<?>> controllers) {
      this.controllers = controllers;
   }
}
