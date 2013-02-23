package com.khubla.pragmatach.plugin.jcr;

import javax.jcr.Node;
import javax.jcr.Property;
import javax.jcr.PropertyIterator;

import org.json.JSONObject;

import com.khubla.pragmatach.framework.api.PragmatachException;

/**
 * @author tome
 */
public class PragmatachJCRToJSON {
   /**
    * JCR to JSON
    * <p>
    * Pretty simple; render all the properties to JSON
    * </p>
    */
   public static String render(Node node) throws PragmatachException {
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
            final Property property = propertyIterator.nextProperty();
            jSONObject.put(property.getName(), property.getValue().toString());
         }
         return jSONObject.toString();
      } catch (final Exception e) {
         throw new PragmatachException("Exception in render", e);
      }
   }
}
