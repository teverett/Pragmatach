package test.com.khubla.pragmatach.plugin.gson;

import com.khubla.pragmatach.framework.annotation.Controller;
import com.khubla.pragmatach.framework.annotation.Route;
import com.khubla.pragmatach.framework.api.PragmatachException;
import com.khubla.pragmatach.framework.api.Response;
import com.khubla.pragmatach.plugin.xstream.XStreamController;

@Controller
public class ExampleController2 extends XStreamController {
   /**
    * ctor
    */
   public ExampleController2() {
   }

   @Route(uri = "/abc", method = Route.HttpMethod.post)
   public Response doPost() throws PragmatachException {
      return super.render();
   }

   @Route(uri = "/333")
   public Response render() throws PragmatachException {
      return super.render();
   }
}