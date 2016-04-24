package com.khubla.pragmatach.plugin.jackson;

import java.io.ByteArrayOutputStream;

import org.codehaus.jackson.JsonEncoding;
import org.codehaus.jackson.JsonFactory;
import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.node.ObjectNode;

import com.khubla.pragmatach.framework.api.PragmatachException;
import com.khubla.pragmatach.framework.api.Response;
import com.khubla.pragmatach.framework.controller.impl.AbstractController;
import com.khubla.pragmatach.framework.controller.impl.trivial.TrivialResponse;

/**
 * @author tome
 */
public class JacksonController extends AbstractController {
   /**
    * factory
    */
   private final JsonFactory jsonFactory;
   /**
    * mapper
    */
   private final ObjectMapper objectMapper;

   /**
    * ctor
    */
   public JacksonController() {
      jsonFactory = new JsonFactory();
      objectMapper = new ObjectMapper(jsonFactory);
   }

   public ObjectNode createNode() {
      return objectMapper.createObjectNode();
   }

   public JsonFactory getJsonFactory() {
      return jsonFactory;
   }

   public ObjectMapper getObjectMapper() {
      return objectMapper;
   }

   /**
    * render
    */
   public Response render(ObjectNode objectNode) throws PragmatachException {
      try {
         /*
          * render json to text
          */
         final ByteArrayOutputStream baos = new ByteArrayOutputStream();
         final JsonGenerator jsonGenerator = jsonFactory.createJsonGenerator(baos, JsonEncoding.UTF8);
         jsonGenerator.writeTree(objectNode);
         jsonGenerator.close();
         /*
          * done
          */
         return new TrivialResponse(null, baos.toString(), 200, "application/json");
      } catch (final Exception e) {
         throw new PragmatachException("Exception in render", e);
      }
   }
}