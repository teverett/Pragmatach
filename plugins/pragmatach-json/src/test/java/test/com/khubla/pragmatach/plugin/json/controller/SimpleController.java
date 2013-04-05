package test.com.khubla.pragmatach.plugin.json.controller;

import com.khubla.pragmatach.framework.annotation.Controller;
import com.khubla.pragmatach.framework.annotation.Route;
import com.khubla.pragmatach.framework.api.PragmatachException;
import com.khubla.pragmatach.framework.api.Response;
import com.khubla.pragmatach.plugin.json.JSONController;

@Controller()
public class SimpleController extends JSONController {
   private String str1;
   private long l1;

   /**
    * ctor
    */
   public SimpleController() {
   }

   @Route(uri = "/abc/123", method = Route.HttpMethod.post)
   public Response doPost() throws PragmatachException {
      return super.render();
   }

   public long getL1() {
      return l1;
   }

   public String getStr1() {
      return str1;
   }

   @Route(uri = "/")
   public Response render() throws PragmatachException {
      return super.render();
   }

   @Route(uri = "/a", method = Route.HttpMethod.post)
   public Response renderRoot() throws PragmatachException {
      return super.render();
   }

   public void setL1(long l1) {
      this.l1 = l1;
   }

   public void setStr1(String str1) {
      this.str1 = str1;
   }
}