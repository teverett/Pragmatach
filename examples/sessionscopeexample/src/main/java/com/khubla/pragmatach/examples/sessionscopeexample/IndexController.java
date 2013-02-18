package com.khubla.pragmatach.examples.sessionscopeexample;

import com.khubla.pragmatach.framework.annotation.CacheControl;
import com.khubla.pragmatach.framework.annotation.Controller;
import com.khubla.pragmatach.framework.annotation.Route;
import com.khubla.pragmatach.framework.annotation.View;
import com.khubla.pragmatach.framework.api.PragmatachException;
import com.khubla.pragmatach.framework.api.Response;
import com.khubla.pragmatach.plugin.freemarker.FreemarkerController;

@Controller(scope = Controller.Scope.session)
@View(view = "index.ftl")
@CacheControl(maxAge = 10)
public class IndexController extends FreemarkerController {
   private int requestCount = 0;

   public int getRequestCount() {
      return requestCount;
   }

   @Route(uri = "/")
   public Response render() throws PragmatachException {
      requestCount++;
      return super.render();
   }

   public void setRequestCount(int requestCount) {
      this.requestCount = requestCount;
   }
}