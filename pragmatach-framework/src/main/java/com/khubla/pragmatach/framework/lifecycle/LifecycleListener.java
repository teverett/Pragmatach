package com.khubla.pragmatach.framework.lifecycle;

import com.khubla.pragmatach.framework.api.Request;
import com.khubla.pragmatach.framework.router.PragmatachRoute;

/**
 * @author tome
 */
public interface LifecycleListener {
   /**
    * request start
    */
   void beginRequest(PragmatachRoute pragmatachRoute, Request request);

   /**
    * request end
    */
   void endRequest(PragmatachRoute pragmatachRoute, Request request);
}
