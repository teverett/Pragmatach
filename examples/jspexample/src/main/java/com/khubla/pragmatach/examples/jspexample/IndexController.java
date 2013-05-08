package com.khubla.pragmatach.examples.jspexample;

import java.util.Date;

import com.khubla.pragmatach.framework.annotation.Controller;
import com.khubla.pragmatach.framework.annotation.Route;
import com.khubla.pragmatach.framework.annotation.View;
import com.khubla.pragmatach.framework.api.PragmatachException;
import com.khubla.pragmatach.framework.api.Response;
import com.khubla.pragmatach.plugin.jsp.JSPController;

@Controller(name = "IndexController")
@View(view = "/index.jsp")
public class IndexController extends JSPController {
   /**
    * the message
    */
   private final String message = "hello world";

   public String getMessage() {
      return message;
   }

   public String getTime() {
      return new Date().toString();
   }

   @Override
   @Route(uri = "/")
   public Response render() throws PragmatachException {
      return super.render();
   }
}