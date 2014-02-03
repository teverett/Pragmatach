package com.khubla.pragmatach.examples.beforeexample;

import java.util.Date;

import com.khubla.pragmatach.framework.annotation.AfterInvoke;
import com.khubla.pragmatach.framework.annotation.BeforeInvoke;
import com.khubla.pragmatach.framework.annotation.Controller;
import com.khubla.pragmatach.framework.annotation.Route;
import com.khubla.pragmatach.framework.annotation.View;
import com.khubla.pragmatach.framework.api.PragmatachException;
import com.khubla.pragmatach.framework.api.Response;
import com.khubla.pragmatach.plugin.freemarker.FreemarkerController;

@Controller()
@View(view = "index.ftl")
public class IndexController extends FreemarkerController {
   /**
    * the message
    */
   private String message;

   @BeforeInvoke
   public void createTheMessage() {
      message = "hello from a before invoke method";
   }

   @AfterInvoke
   public void dropMessage() {
      System.out.println("after invoke");
   }

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