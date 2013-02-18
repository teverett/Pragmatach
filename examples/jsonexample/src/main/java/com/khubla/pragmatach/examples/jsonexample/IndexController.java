package com.khubla.pragmatach.examples.jsonexample;

import java.util.Date;

import com.khubla.pragmatach.framework.annotation.Controller;
import com.khubla.pragmatach.framework.annotation.Route;
import com.khubla.pragmatach.framework.api.PragmatachException;
import com.khubla.pragmatach.framework.api.Response;
import com.khubla.pragmatach.plugin.json.JSONController;

@Controller(name = "IndexController")
public class IndexController extends JSONController {
   /**
    * the message
    */
   private final String message = "hello world";
   /**
    * the time
    */
   private String time;

   public String getMessage() {
      return message;
   }

   public String getTime() {
      return time;
   }

   @Route(uri = "/")
   public Response render() throws PragmatachException {
      time = new Date().toString();
      return super.render();
   }

   public void setTime(String time) {
      this.time = time;
   }
}