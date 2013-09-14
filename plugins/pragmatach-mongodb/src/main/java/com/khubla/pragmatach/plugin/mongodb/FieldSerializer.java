package com.khubla.pragmatach.plugin.mongodb;

import java.lang.reflect.Field;

import com.khubla.pragmatach.framework.api.PragmatachException;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;

/**
 * @author tom
 */
public interface FieldSerializer {
   void deserializeField(Object object, Field field, DBObject dbObject) throws PragmatachException;

   void serializeField(BasicDBObject parentDBObject, Object object, Field field) throws PragmatachException;
}
