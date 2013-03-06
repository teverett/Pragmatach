package com.khubla.pragmatach.plugin.yaml;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.io.IOUtils;
import org.yaml.snakeyaml.Yaml;

import com.khubla.pragmatach.framework.api.PragmatachException;

/**
 * @author tome
 */
public class PragmatachYAML {
   /**
    * convert object from YAML
    */
   public static void fromYAML(Object object, InputStream inputStream) throws PragmatachException {
      try {
         final ByteArrayOutputStream baos = new ByteArrayOutputStream();
         IOUtils.copy(inputStream, baos);
         fromYAML(object, baos.toString());
      } catch (final Exception e) {
         throw new PragmatachException("Exception in fromYAML", e);
      }
   }

   /**
    * convert object from YAML
    */
   public static void fromYAML(Object object, String YAML) throws PragmatachException {
      try {
         final Map<String, String> values = parseYAML(YAML);
         if ((null != values) && (values.size() > 0)) {
            for (final Field field : object.getClass().getDeclaredFields()) {
               final String value = values.get(field.getName());
               BeanUtils.setProperty(object, field.getName(), value);
            }
         }
      } catch (final Exception e) {
         throw new PragmatachException("Exception in fromYAML", e);
      }
   }

   /**
    * parse YAML to map
    */
   public static Map<String, String> parseYAML(InputStream inputStream) throws PragmatachException {
      try {
         final ByteArrayOutputStream baos = new ByteArrayOutputStream();
         IOUtils.copy(inputStream, baos);
         return parseYAML(baos.toString());
      } catch (final Exception e) {
         throw new PragmatachException("Exception in parseYAML", e);
      }
   }

   /**
    * parse YAML to map
    */
   @SuppressWarnings("unchecked")
   public static Map<String, String> parseYAML(String YAML) throws PragmatachException {
      try {
         final Yaml yaml = new Yaml();
         return (Map<String, String>) yaml.load(YAML);
      } catch (final Exception e) {
         throw new PragmatachException("Exception in parseYAML", e);
      }
   }

   /**
    * convert object to YAML
    */
   public static String toYAML(Object object) throws PragmatachException {
      try {
         final Map<String, String> map = new HashMap<String, String>();
         for (final Field field : object.getClass().getDeclaredFields()) {
            map.put(field.getName(), BeanUtils.getSimpleProperty(object, field.getName()));
         }
         final Yaml yaml = new Yaml();
         return yaml.dump(map);
      } catch (final Exception e) {
         throw new PragmatachException("Exception in toYAML", e);
      }
   }

   /**
    * convert object to YAML
    */
   public static void toYAML(Object object, OutputStream outputStream) throws PragmatachException {
      try {
         final String YAML = toYAML(object);
         final ByteArrayInputStream bais = new ByteArrayInputStream(YAML.getBytes());
         IOUtils.copy(bais, outputStream);
      } catch (final Exception e) {
         throw new PragmatachException("Exception in toYAML", e);
      }
   }
}
