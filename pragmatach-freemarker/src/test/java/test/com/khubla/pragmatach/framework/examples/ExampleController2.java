package test.com.khubla.pragmatach.framework.examples;

import com.khubla.pragmatach.framework.annotation.Controller;
import com.khubla.pragmatach.framework.annotation.Route;
import com.khubla.pragmatach.framework.api.PragmatachException;
import com.khubla.pragmatach.framework.api.Request;
import com.khubla.pragmatach.framework.api.Response;
import com.khubla.pragmatach.plugin.freemarker.FreemarkerController;
import com.khubla.pragmatach.plugin.freemarker.FreemarkerTemplate;

@Controller
@FreemarkerTemplate(template = "index.ftl")
public class ExampleController2 extends FreemarkerController {
   /**
    * ctor
    */
   public ExampleController2(Request request) {
      super(request);
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