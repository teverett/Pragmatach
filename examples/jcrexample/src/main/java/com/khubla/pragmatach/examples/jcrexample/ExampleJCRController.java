package com.khubla.pragmatach.examples.jcrexample;

import com.khubla.pragmatach.framework.annotation.Controller;
import com.khubla.pragmatach.framework.annotation.Route;
import com.khubla.pragmatach.framework.annotation.RouteParameter;
import com.khubla.pragmatach.framework.api.PragmatachException;
import com.khubla.pragmatach.framework.api.Response;
import com.khubla.pragmatach.plugin.jcr.JCRController;

/**
 * @author tome
 */
@Controller(name = "ExampleJCRController")
public class ExampleJCRController extends JCRController {
   @Route(uri = "example/@propertyName")
   public Response render(@RouteParameter(name = "propertyName") String propertyName) throws PragmatachException {
      return super.render(propertyName);
   }
}