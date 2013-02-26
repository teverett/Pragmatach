package com.khubla.pragmatach.plugin.jcr;

import javax.jcr.Node;
import javax.jcr.NodeIterator;
import javax.jcr.Property;
import javax.jcr.PropertyIterator;
import javax.jcr.Value;

import org.json.JSONObject;

import com.khubla.pragmatach.framework.api.PragmatachException;

/**
 * @author tome
 */
public class PragmatachJCRToJSON {
   /**
    * Pretty simple; render all the properties to JSON
    */
   public static String renderNodeProperties(Node node) throws PragmatachException {
      try {
         /*
          * json
          */
         final JSONObject jSONObject = new JSONObject();
         /*
          * walk the properties
          */
         final PropertyIterator propertyIterator = node.getProperties();
         while (propertyIterator.hasNext()) {
            /*
             * property
             */
            final Property property = propertyIterator.nextProperty();
            /*
             * name
             */
            final String propertyName = property.getName();
            if (false == property.isMultiple()) {
               jSONObject.put(propertyName, property.getValue().getString());
            } else {
               final JSONObject containedObject = new JSONObject();
               int i = 0;
               for (final Value v : property.getValues()) {
                  containedObject.put(Integer.toString(i++), v.getString());
               }
               jSONObject.put(propertyName, containedObject);
            }
         }
         return jSONObject.toString();
      } catch (final Exception e) {
         throw new PragmatachException("Exception in render", e);
      }
   }

   /**
    * Pretty simple; render all the subnode names to JSON
    */
   public static String renderSubnodeNames(Node node) throws PragmatachException {
      try {
         /*
          * json
          */
         final JSONObject jSONObject = new JSONObject();
         /*
          * walk the nodes
          */
         final NodeIterator nodeIterator = node.getNodes();
         while (nodeIterator.hasNext()) {
            /*
             * node
             */
            final Node subNode = nodeIterator.nextNode();
            /*
             * add
             */
            jSONObject.put(subNode.getName(), subNode.getPath());
         }
         return jSONObject.toString();
      } catch (final Exception e) {
         throw new PragmatachException("Exception in render", e);
      }
   }
}
