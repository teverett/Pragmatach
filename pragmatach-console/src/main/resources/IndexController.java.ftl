package ${namespace};

import com.khubla.pragmatach.framework.annotation.Controller;
import com.khubla.pragmatach.framework.annotation.Route;
import com.khubla.pragmatach.framework.api.PragmatachException;
import com.khubla.pragmatach.framework.api.Request;
import com.khubla.pragmatach.framework.api.Response;
import com.khubla.pragmatach.framework.freemarker.FreemarkerController;
import com.khubla.pragmatach.framework.freemarker.FreemarkerTemplate;

@Controller
@FreemarkerTemplate(template="index.ftl")
public class IndexController extends FreemarkerController {
   
   @Route(uri = "/")
   public Response render(Request request) throws PragmatachException {
      return super.render(request);
   }
}