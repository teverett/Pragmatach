package com.khubla.pragmatach.plugin.mongodb.serializer;

import com.khubla.pragmatach.framework.api.PragmatachException;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;

/**
 * @author tom
 */
public interface ObjectSerializer {
   Object deserialize(DBObject dbObject) throws PragmatachException;

   BasicDBObject serialize(Object object) throws PragmatachException;
}
