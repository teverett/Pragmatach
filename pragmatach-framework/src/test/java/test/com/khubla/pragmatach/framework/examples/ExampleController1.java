package test.com.khubla.pragmatach.framework.examples;

import com.khubla.pragmatach.framework.annotation.Controller;
import com.khubla.pragmatach.framework.annotation.Route;
import com.khubla.pragmatach.framework.api.PragmatachException;
import com.khubla.pragmatach.framework.api.Request;
import com.khubla.pragmatach.framework.api.Response;
import com.khubla.pragmatach.framework.controller.TrivialController;

@Controller
public class ExampleController1 extends TrivialController {
   public ExampleController1() {
      super("ExampleController1", 200);
   }

   @Route(uri = "/abc/123", method = Route.HttpMethod.post)
   public Response doPost(Request request) throws PragmatachException {
      return super.render(request);
   }

   @Route(uri = "/")
   public Response render(Request request) throws PragmatachException {
      return super.render(request);
   }

   @Route(uri = "/a", method = Route.HttpMethod.post)
   public Response renderRoot(Request request) throws PragmatachException {
      return super.render(request);
   }
}