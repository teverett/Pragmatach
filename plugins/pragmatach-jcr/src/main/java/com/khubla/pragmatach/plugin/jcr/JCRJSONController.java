package com.khubla.pragmatach.plugin.jcr;

import javax.jcr.Node;
import javax.jcr.Session;

import com.khubla.pragmatach.framework.api.PragmatachException;
import com.khubla.pragmatach.framework.api.Response;

/**
 * @author tome
 */
public class JCRJSONController extends JCRController {
   private Node getNode(String[] nodeName) throws PragmatachException {
      try {
         /*
          * session
          */
         final Session session = jcrSessionFactory.getSession();
         /*
          * check
          */
         if (null != session) {
            final Node root = session.getRootNode();
            if (null == nodeName) {
               return root;
            } else {
               return root.getNode(buildWildcardResourceURI(nodeName));
            }
         } else {
            throw new PragmatachException("Unable to get session");
         }
      } catch (final Exception e) {
         throw new PragmatachException("Exception in getNode", e);
      }
   }

   /**
    * render children
    */
   public Response renderChildren(String[] nodeName) throws PragmatachException {
      try {
         final Node node = getNode(nodeName);
         if (null != node) {
            return new JCRResponse(getCacheHeaders(), PragmatachJCRToJSON.renderSubnodeNames(node));
         } else {
            throw new Exception("Unable to get node");
         }
      } catch (final Exception e) {
         throw new PragmatachException("Exception in render", e);
      }
   }

   /**
    * render properties
    */
   public Response renderProperties(String[] nodeName) throws PragmatachException {
      try {
         final Node node = getNode(nodeName);
         if (null != node) {
            return new JCRResponse(getCacheHeaders(), PragmatachJCRToJSON.renderNodeProperties(node));
         } else {
            throw new Exception("Unable to get node");
         }
      } catch (final Exception e) {
         throw new PragmatachException("Exception in render", e);
      }
   }
}
