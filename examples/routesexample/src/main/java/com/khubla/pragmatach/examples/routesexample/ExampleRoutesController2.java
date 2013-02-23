package com.khubla.pragmatach.examples.routesexample;

import com.khubla.pragmatach.framework.annotation.Controller;
import com.khubla.pragmatach.framework.annotation.Route;
import com.khubla.pragmatach.framework.annotation.RouteParameter;
import com.khubla.pragmatach.framework.annotation.View;
import com.khubla.pragmatach.framework.api.PragmatachException;
import com.khubla.pragmatach.framework.api.Response;
import com.khubla.pragmatach.plugin.freemarker.FreemarkerController;

@Controller(name = "ExampleRoutesController2")
@View(view = "exampleRoutesController2.ftl")
public class ExampleRoutesController2 extends FreemarkerController {
   /**
    * the message
    */
   private String message = "hello world";

   public String getMessage() {
      return message;
   }

   @Route(uri = "/exampleRoutesController2/@str1/ststst/@str2")
   public Response render(@RouteParameter(name = "str1") String string1, @RouteParameter(name = "str2") String string2) throws PragmatachException {
      message = string1 + string2;
      return super.render();
   }
}