package ${packageName};

import java.util.Date;

import com.khubla.pragmatach.framework.annotation.Controller;
import com.khubla.pragmatach.framework.annotation.Route;
import com.khubla.pragmatach.framework.annotation.View;
import com.khubla.pragmatach.framework.api.PragmatachException;
import com.khubla.pragmatach.framework.api.Response;
import com.khubla.pragmatach.plugin.freemarker.FreemarkerController;

@Controller(name="IndexController")
@View(view="index.ftl")
public class IndexController extends FreemarkerController {
   
   /**
    * the message
    */
   private final String message = "Welcome to Pragmatach";


   public String getMessage() {
      return message;
   }

   @Route(uri = "/")
   public Response render() throws PragmatachException {
      return super.render();
   }

   public String getTime() {
      return new Date().toString();
   }
}