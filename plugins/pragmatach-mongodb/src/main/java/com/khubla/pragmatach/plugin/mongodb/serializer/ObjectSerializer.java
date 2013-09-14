package com.khubla.pragmatach.plugin.mongodb.serializer;

import com.khubla.pragmatach.framework.api.PragmatachException;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;

/**
 * @author tom
 */
public interface ObjectSerializer<T> {
   T deserialize(DBObject dbObject) throws PragmatachException;

   BasicDBObject serialize(T t) throws PragmatachException;
}
