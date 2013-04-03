package com.khubla.pragmatach.plugin.statsd;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.khubla.pragmatach.framework.api.Request;
import com.khubla.pragmatach.framework.application.Application;
import com.khubla.pragmatach.framework.lifecycle.LifecycleListener;
import com.khubla.pragmatach.framework.router.PragmatachRoute;
import com.timgroup.statsd.NonBlockingStatsDClient;
import com.timgroup.statsd.StatsDClient;

/**
 * @author tome
 */
public class StatsDClientImplementation implements LifecycleListener {
   /**
    * logger
    */
   private final Logger logger = LoggerFactory.getLogger(this.getClass());
   /**
    * the client
    */
   private static final StatsDClient statsDClient = getStatsDClient();

   public static StatsDClient getStatsdclient() {
      return statsDClient;
   }

   /**
    * get the client
    */
   private static StatsDClient getStatsDClient() {
      try {
         final String prefix = Application.getConfiguration().getParameter("statsd.prefix");
         final String host = Application.getConfiguration().getParameter("statsd.host");
         final String port = Application.getConfiguration().getParameter("statsd.port");
         if ((null != prefix) && (null != host) && (null != port)) {
            return new NonBlockingStatsDClient(prefix, host, Integer.parseInt(port));
         } else {
            return null;
         }
      } catch (final Exception e) {
         throw new ExceptionInInitializerError(e);
      }
   }

   @Override
   public void beginRequest(PragmatachRoute pragmatachRoute, Request request) {
      if (null != statsDClient) {
         /*
          * request count
          */
         statsDClient.incrementCounter("TotalRequests");
      } else {
         logger.info("started: " + request.getMethod() + " request for: " + request.getURI() + " routed to " + pragmatachRoute.getDescription());
      }
   }

   @Override
   public void endRequest(PragmatachRoute pragmatachRoute, Request request) {
      if (null != statsDClient) {
      } else {
         logger.info("completed: " + request.getMethod() + " request for: " + request.getURI() + " routed to " + pragmatachRoute.getDescription());
      }
   }
}
