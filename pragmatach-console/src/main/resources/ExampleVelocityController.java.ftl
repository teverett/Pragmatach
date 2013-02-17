package ${namespace};

import com.khubla.pragmatach.framework.annotation.Controller;
import com.khubla.pragmatach.framework.annotation.Route;
import com.khubla.pragmatach.framework.annotation.View;
import com.khubla.pragmatach.framework.api.PragmatachException;
import com.khubla.pragmatach.framework.api.Response;
import com.khubla.pragmatach.plugin.velocity.VelocityController;

@Controller
@View(view="example.vtl")
public class ExampleVelocityController extends VelocityController {
   
   /**
    * the message
    */
   private final String message = "hello world";
 

   public String getMessage() {
      return message;
   }

   @Route(uri = "/velocity")
   public Response render() throws PragmatachException {
      return super.render();
   }
}