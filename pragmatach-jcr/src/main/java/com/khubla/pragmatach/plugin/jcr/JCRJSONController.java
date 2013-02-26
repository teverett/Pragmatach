package com.khubla.pragmatach.plugin.jcr;

import javax.jcr.Node;
import javax.jcr.Session;

import com.khubla.pragmatach.framework.api.PragmatachException;
import com.khubla.pragmatach.framework.api.Response;

/**
 * @author tome
 */
public class JCRJSONController extends JCRController {
   /**
    * render children
    */
   public Response renderChildren(String nodeName) throws PragmatachException {
      try {
         /*
          * session
          */
         final Session session = jcrSessionFactory.getSession();
         /*
          * check
          */
         if (null != session) {
            /*
             * get the property
             */
            final Node root = session.getRootNode();
            if (null != root) {
               return new JCRResponse(getCacheHeaders(), PragmatachJCRToJSON.renderSubnodeNames(root));
            }
            // final Node node = root.getNode(propertyName);
            // final String json = renderNodeToJSON(node);
            // return new JCRResponse(getCacheHeaders(), json);
            return null;
         } else {
            throw new PragmatachException("Unable to get session");
         }
      } catch (final Exception e) {
         throw new PragmatachException("Exception in render", e);
      }
   }

   /**
    * render properties
    */
   public Response renderProperties(String nodeName) throws PragmatachException {
      try {
         /*
          * session
          */
         final Session session = jcrSessionFactory.getSession();
         /*
          * check
          */
         if (null != session) {
            /*
             * get the property
             */
            final Node root = session.getRootNode();
            if (null != root) {
               return new JCRResponse(getCacheHeaders(), PragmatachJCRToJSON.renderNodeProperties(root));
            }
            // final Node node = root.getNode(propertyName);
            // final String json = renderNodeToJSON(node);
            // return new JCRResponse(getCacheHeaders(), json);
            return null;
         } else {
            throw new PragmatachException("Unable to get session");
         }
      } catch (final Exception e) {
         throw new PragmatachException("Exception in render", e);
      }
   }
}
