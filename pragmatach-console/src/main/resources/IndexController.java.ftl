package ${namespace};

import java.util.Date;

import com.khubla.pragmatach.framework.annotation.Controller;
import com.khubla.pragmatach.framework.annotation.Route;
import com.khubla.pragmatach.framework.api.PragmatachException;
import com.khubla.pragmatach.framework.api.Request;
import com.khubla.pragmatach.framework.api.Response;
import com.khubla.pragmatach.plugin.freemarker.FreemarkerController;
import com.khubla.pragmatach.plugin.freemarker.FreemarkerTemplate;

@Controller
@FreemarkerTemplate(template="index.ftl")
public class IndexController extends FreemarkerController {
   
   /**
    * the message
    */
   private final String message = "hello world";

   public String getMessage() {
      return message;
   }
   
   public IndexController(Request request) {
      super(request);
   }

   @Route(uri = "/")
   public Response render() throws PragmatachException {
      return super.render();
   }
   
   public String getTime() {
      return new Date().toString(); 
   }
}