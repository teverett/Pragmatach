package com.khubla.pragmatach.examples.amazonrdsexample;

import com.khubla.pragmatach.examples.amazonrdsexample.pojo.AccessLogPOJO;
import com.khubla.pragmatach.framework.annotation.Controller;
import com.khubla.pragmatach.framework.annotation.Route;
import com.khubla.pragmatach.framework.annotation.View;
import com.khubla.pragmatach.framework.api.PragmatachException;
import com.khubla.pragmatach.framework.api.Response;
import com.khubla.pragmatach.plugin.freemarker.FreemarkerController;

@Controller(name = "IndexController")
@View(view = "index.html")
public class IndexController extends FreemarkerController {
   @Route(uri = "/")
   public Response render() throws PragmatachException {
      AccessLogPOJO accessLogPOJO = new AccessLogPOJO(this.getRequest().getHttpServletRequest().getRemoteAddr());
      AccessLogPOJO.dao.save(accessLogPOJO);
      return super.render();
   }
}