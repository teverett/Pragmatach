package com.khubla.pragmatach.examples.jcrexample.html;

import com.khubla.pragmatach.framework.annotation.Controller;
import com.khubla.pragmatach.framework.annotation.Route;
import com.khubla.pragmatach.framework.annotation.RouteParameter;
import com.khubla.pragmatach.framework.annotation.View;
import com.khubla.pragmatach.framework.api.PragmatachException;
import com.khubla.pragmatach.framework.api.Response;
import com.khubla.pragmatach.plugin.freemarker.FreemarkerController;
import com.khubla.pragmatach.plugin.jcr.JCRSessionFactory;

/**
 * @author tome
 */
@Controller(name = "HTMLNodePropertiesController")
@View(view = "jcrproperties.html")
public class HTMLNodePropertiesController extends FreemarkerController {
   /***
    * JCRSessionFactory
    */
   protected final JCRSessionFactory jcrSessionFactory = new JCRSessionFactory();

   @Route(uri = "example/html/properties/@nodeName")
   public Response render(@RouteParameter(name = "nodeName") String nodeName) throws PragmatachException {
      return super.render();
   }
}