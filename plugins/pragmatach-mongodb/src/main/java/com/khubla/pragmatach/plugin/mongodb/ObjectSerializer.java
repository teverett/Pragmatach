package com.khubla.pragmatach.plugin.mongodb;

import com.khubla.pragmatach.framework.api.PragmatachException;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;

/**
 * @author tom
 */
public interface ObjectSerializer {
   void deserialize(DBObject dbObject, Object o) throws PragmatachException;

   BasicDBObject serialize(Object object) throws PragmatachException;
}
