package com.khubla.pragmatach.examples.jcrexample.json;

import com.khubla.pragmatach.framework.annotation.Controller;
import com.khubla.pragmatach.framework.annotation.Route;
import com.khubla.pragmatach.framework.api.PragmatachException;
import com.khubla.pragmatach.framework.api.Response;
import com.khubla.pragmatach.plugin.jcr.JCRJSONController;

/**
 * @author tome
 */
@Controller(name = "JSONNodePropertiesController")
public class JSONNodePropertiesController extends JCRJSONController {
   @Route(uri = "example/json/properties/")
   public Response render() throws PragmatachException {
      return super.renderProperties(null);
   }

   @Route(uri = "example/json/properties/*")
   public Response render(String[] nodeName) throws PragmatachException {
      return super.renderProperties(nodeName);
   }
}