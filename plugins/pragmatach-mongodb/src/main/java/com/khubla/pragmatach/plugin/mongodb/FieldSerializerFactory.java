package com.khubla.pragmatach.plugin.mongodb;

import java.lang.reflect.Field;
import java.util.Set;

import com.khubla.pragmatach.framework.api.PragmatachException;

/**
 * @author tom
 */
public class FieldSerializerFactory {
   public static FieldSerializer getFieldSerializer(Class<?> clazz, Field field) throws PragmatachException {
      if (AtomicTypeUtil.isSimpleType(field.getType())) {
         return new AtomicFieldSerializer(clazz);
      } else if (field.getType() == Set.class) {
         return new SetFieldSerializer(clazz);
      } else {
         throw new PragmatachException("Invalid serialization type '" + field.getName() + "'");
      }
   }
}
