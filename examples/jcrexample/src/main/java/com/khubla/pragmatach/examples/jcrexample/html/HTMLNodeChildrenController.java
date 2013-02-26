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
@Controller(name = "HTMLNodeChildrenController")
@View(view = "jcrnode.html")
public class HTMLNodeChildrenController extends FreemarkerController {
   /***
    * JCRSessionFactory
    */
   protected final JCRSessionFactory jcrSessionFactory = new JCRSessionFactory();

   @Route(uri = "example/html/children/")
   public Response render() throws PragmatachException {
      return super.render();
   }

   @Route(uri = "example/html/children/@nodeName")
   public Response render(@RouteParameter(name = "nodeName") String nodeName) throws PragmatachException {
      return super.render();
   }
}