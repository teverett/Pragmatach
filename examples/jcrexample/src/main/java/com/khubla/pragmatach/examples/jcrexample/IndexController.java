package com.khubla.pragmatach.examples.jcrexample;

import java.util.Date;

import com.khubla.pragmatach.framework.annotation.Controller;
import com.khubla.pragmatach.framework.annotation.Route;
import com.khubla.pragmatach.framework.annotation.RouteParameter;
import com.khubla.pragmatach.framework.api.PragmatachException;
import com.khubla.pragmatach.framework.api.Response;
import com.khubla.pragmatach.plugin.jcr.JCRController;

@Controller(name = "IndexController")
public class IndexController extends JCRController {
   @Route(uri = "/@propertyName")
   public Response render(@RouteParameter(name = "propertyName") String propertyName) throws PragmatachException {
      return super.render(propertyName);
   }

   public String getTime() {
      return new Date().toString();
   }
}