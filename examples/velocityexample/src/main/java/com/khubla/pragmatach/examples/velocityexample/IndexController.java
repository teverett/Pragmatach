package com.khubla.pragmatach.examples.velocityexample;

import java.util.Date;

import com.khubla.pragmatach.framework.annotation.Controller;
import com.khubla.pragmatach.framework.annotation.Route;
import com.khubla.pragmatach.framework.annotation.RouteParameter;
import com.khubla.pragmatach.framework.annotation.View;
import com.khubla.pragmatach.framework.api.PragmatachException;
import com.khubla.pragmatach.framework.api.Response;
import com.khubla.pragmatach.plugin.velocity.VelocityController;

@Controller(name = "IndexController")
@View(view = "index.vtl")
public class IndexController extends VelocityController {
   /**
    * the message
    */
   private final String message = "hello world";
   /**
    * the sum
    */
   private int sum = 0;

   public String getMessage() {
      return message;
   }

   public int getSum() {
      return sum;
   }

   public String getTime() {
      return new Date().toString();
   }

   @Override
   @Route(uri = "/")
   public Response render() throws PragmatachException {
      return super.render();
   }

   @Route(uri = "/@num")
   public Response render(@RouteParameter(name = "num") int number) throws PragmatachException {
      sum = number * 2;
      return super.render();
   }

   public void setSum(int sum) {
      this.sum = sum;
   }
}