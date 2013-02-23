package com.khubla.pragmatach.examples.routesexample;

import com.khubla.pragmatach.framework.annotation.Controller;
import com.khubla.pragmatach.framework.annotation.Route;
import com.khubla.pragmatach.framework.annotation.RouteParameter;
import com.khubla.pragmatach.framework.annotation.View;
import com.khubla.pragmatach.framework.api.PragmatachException;
import com.khubla.pragmatach.framework.api.Response;
import com.khubla.pragmatach.plugin.freemarker.FreemarkerController;

@Controller(name = "ExampleRoutesController1")
@View(view = "exampleRoutesController1.ftl")
public class ExampleRoutesController1 extends FreemarkerController {
   /**
    * the message
    */
   private String message = "hello world";
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

   @Route(uri = "/exampleRoutesController1/@num1/@num2/@message")
   public Response render(@RouteParameter(name = "num1") int number1, @RouteParameter(name = "num2") int number2, @RouteParameter(name = "message") String message) throws PragmatachException {
      sum = number1 * number2;
      this.message = message;
      return super.render();
   }

   public void setSum(int sum) {
      this.sum = sum;
   }
}