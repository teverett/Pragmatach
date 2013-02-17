package com.khubla.com.pragmatach.examples.jacksonexample;

import java.util.Date;

import com.khubla.pragmatach.framework.annotation.Controller;
import com.khubla.pragmatach.framework.annotation.Route;
import com.khubla.pragmatach.framework.api.PragmatachException;
import com.khubla.pragmatach.framework.api.Response;
import com.khubla.pragmatach.plugin.jackson.JacksonController;

@Controller
public class IndexController extends JacksonController {
   /**
    * the message
    */
   private final String message = "hello world";
   /**
    * the time
    */
   private String time;

   public String getTime() {
      return time;
   }

   public void setTime(String time) {
      this.time = time;
   }

   public String getMessage() {
      return message;
   }

   @Route(uri = "/")
   public Response render() throws PragmatachException {
      this.time = new Date().toString();
      return super.render();
   }
}