package com.khubla.pragmatach.examples.jcrexample.html;

import javax.jcr.Node;
import javax.jcr.Session;

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
   /**
    * the node
    */
   private Node node;

   public Node getNode() {
      return node;
   }

   @Route(uri = "example/html/properties/")
   public Response render() throws PragmatachException {
      try {
         final Session session = jcrSessionFactory.getSession();
         node = session.getRootNode();
         return super.render();
      } catch (final Exception e) {
         throw new PragmatachException("Exception in render", e);
      }
   }

   @Route(uri = "example/html/properties/@nodeName")
   public Response render(@RouteParameter(name = "nodeName") String nodeName) throws PragmatachException {
      try {
         final Session session = jcrSessionFactory.getSession();
         node = session.getRootNode().getNode(nodeName);
         return super.render();
      } catch (final Exception e) {
         throw new PragmatachException("Exception in render", e);
      }
   }

   public void setNode(Node node) {
      this.node = node;
   }
}