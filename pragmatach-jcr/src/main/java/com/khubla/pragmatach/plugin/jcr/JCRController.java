package com.khubla.pragmatach.plugin.jcr;

import javax.jcr.Node;
import javax.jcr.Session;

import com.khubla.pragmatach.framework.api.PragmatachException;
import com.khubla.pragmatach.framework.api.Response;
import com.khubla.pragmatach.framework.controller.AbstractTemplateEngineController;

/**
 * @author tome
 */
public class JCRController extends AbstractTemplateEngineController {
   /***
    * JCRSessionFactory
    */
   private final JCRSessionFactory jcrSessionFactory = new JCRSessionFactory();

   /**
    * ctor
    */
   public JCRController() {
   }

   /**
    * render
    */
   public Response render(String propertyName) throws PragmatachException {
      try {
         /*
          * session
          */
         final Session session = jcrSessionFactory.getSession();
         /*
          * get the property
          */
         final Node root = session.getRootNode();
         final Node node = root.getNode(propertyName);
         final String json = renderNodeToJSON(node);
         return new JCRResponse(getCacheHeaders(), json);
      } catch (final Exception e) {
         throw new PragmatachException("Exception in render", e);
      }
   }

   /**
    * render JCR node to JSON
    */
   private String renderNodeToJSON(Node node) throws PragmatachException {
      try {
         return PragmatachJCRToJSON.render(node);
      } catch (final Exception e) {
         throw new PragmatachException("Exception in renderNodeToJSON", e);
      }
   }
}
