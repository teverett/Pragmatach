package com.khubla.pragmatach.examples.jcrexample.html;

import java.util.HashSet;
import java.util.Set;

import javax.jcr.Node;
import javax.jcr.NodeIterator;
import javax.jcr.Property;
import javax.jcr.PropertyIterator;
import javax.jcr.RepositoryException;
import javax.jcr.Session;

import com.khubla.pragmatach.framework.annotation.Controller;
import com.khubla.pragmatach.framework.annotation.Route;
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
public class HTMLNodeController extends FreemarkerController {
   /***
    * JCRSessionFactory
    */
   protected final JCRSessionFactory jcrSessionFactory = new JCRSessionFactory();
   /**
    * the node
    */
   private Node node;
   /**
    * subnodes
    */
   private Set<Node> subnodes;
   /**
    * current node path
    */
   private String jcrPath = "";

   public String getJcrPath() {
      return jcrPath;
   }

   public Node getNode() {
      return node;
   }

   public Set<Node> getSubnodes() {
      return subnodes;
   }

   public Set<Property> nodeProperties(Node node) throws RepositoryException {
      final Set<Property> ret = new HashSet<Property>();
      final PropertyIterator propertyIterator = node.getProperties();
      while (propertyIterator.hasNext()) {
         /*
          * property
          */
         ret.add(propertyIterator.nextProperty());
      }
      return ret;
   }

   private void readSubNodes(Node node) throws RepositoryException {
      subnodes = new HashSet<Node>();
      final NodeIterator nodeIterator = node.getNodes();
      while (nodeIterator.hasNext()) {
         /*
          * node
          */
         subnodes.add(nodeIterator.nextNode());
      }
   }

   @Route(uri = "example/html/")
   public Response render() throws PragmatachException {
      try {
         final Session session = jcrSessionFactory.getSession();
         node = session.getRootNode();
         readSubNodes(node);
         jcrPath = "";
         return super.render();
      } catch (final Exception e) {
         throw new PragmatachException("Exception in render", e);
      }
   }

   @Route(uri = "example/html/*")
   public Response render(String[] nodeName) throws PragmatachException {
      try {
         final Session session = jcrSessionFactory.getSession();
         /*
          * get the node path and remove the leading slashie
          */
         jcrPath = (getJcrPath() + buildWildcardResourceURI(nodeName)).substring(1) + "/";
         node = session.getRootNode().getNode(jcrPath);
         readSubNodes(node);
         return super.render();
      } catch (final Exception e) {
         throw new PragmatachException("Exception in render", e);
      }
   }

   public void setJcrPath(String jcrPath) {
      this.jcrPath = jcrPath;
   }

   public void setNode(Node node) {
      this.node = node;
   }

   public void setSubnodes(Set<Node> subnodes) {
      this.subnodes = subnodes;
   }
}