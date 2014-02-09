package com.khubla.pragmatach.examples.startupexample;

import java.util.Date;

import com.khubla.pragmatach.framework.annotation.Controller;
import com.khubla.pragmatach.framework.annotation.OnShutdown;
import com.khubla.pragmatach.framework.annotation.OnStartup;
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
   private String message = "OnStartup and OnShutdown Example";

   @OnStartup
   public static void onStartup() {
      System.out.println("startup");
   }

   @OnShutdown
   public static void onShutdown() {
      System.out.println("shutdown");
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