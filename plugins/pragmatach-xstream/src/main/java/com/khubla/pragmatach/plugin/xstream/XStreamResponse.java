package com.khubla.pragmatach.plugin.xstream;

import java.io.ByteArrayInputStream;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;

import com.khubla.pragmatach.framework.api.PragmatachException;
import com.khubla.pragmatach.framework.controller.PragmatachController;
import com.khubla.pragmatach.framework.controller.impl.AbstractController;
import com.khubla.pragmatach.framework.controller.impl.AbstractResponse;
import com.thoughtworks.xstream.XStream;

/**
 * @author tome
 */
public class XStreamResponse extends AbstractResponse {
   /**
    * the controller
    */
   private final PragmatachController pragmatachController;

   /**
    * ctor
    */
   public XStreamResponse(Map<String, String> cacheHeaders, PragmatachController pragmatachController) {
      super(cacheHeaders);
      this.pragmatachController = pragmatachController;
   }

   @Override
   public String getContentType() throws PragmatachException {
      return "text/xml";
   }

   @Override
   public Map<String, String> getHeaders() throws PragmatachException {
      return super.getCacheHeaders();
   }

   @Override
   public void render(HttpServletResponse httpServletResponse) throws PragmatachException {
      try {
         final XStream xstream = new XStream();
         xstream.omitField(AbstractController.class, "request");
         final String XML = xstream.toXML(pragmatachController);
         final ByteArrayInputStream bais = new ByteArrayInputStream(XML.getBytes());
         IOUtils.copy(bais, httpServletResponse.getOutputStream());
      } catch (final Exception e) {
         throw new PragmatachException("Exception in render", e);
      }
   }
}
