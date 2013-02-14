package com.khubla.pragmatach.framework.templater;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Map;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;

import com.khubla.pragmatach.framework.api.PragmatachException;

/**
 * @author tome
 */
public class TrivialTemplater {
   private static final String ESCAPE = "$";

   public static void template(InputStream templateInputStream, OutputStream outputStream, Map<String, String> substitutions) throws PragmatachException {
      try {
         /*
          * read the entire InputStream
          */
         final ByteArrayOutputStream baos = new ByteArrayOutputStream();
         IOUtils.copy(templateInputStream, baos);
         /*
          * do substitutions
          */
         final String renderedTemplate = baos.toString();
         if (null != substitutions) {
            for (final String key : substitutions.keySet()) {
               final String v = substitutions.get(key);
               StringUtils.replace(renderedTemplate, ESCAPE + key, v);
            }
         }
         /*
          * write out
          */
         final ByteArrayInputStream bais = new ByteArrayInputStream(renderedTemplate.getBytes());
         IOUtils.copy(bais, outputStream);
      } catch (final Exception e) {
         throw new PragmatachException("Exception in template", e);
      }
   }
}
