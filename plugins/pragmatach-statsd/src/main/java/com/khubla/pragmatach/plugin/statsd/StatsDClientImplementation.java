package com.khubla.pragmatach.plugin.statsd;

import com.khubla.pragmatach.framework.application.Application;
import com.timgroup.statsd.NonBlockingStatsDClient;
import com.timgroup.statsd.StatsDClient;

/**
 * @author tome
 */
public class StatsDClientImplementation {
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
         return new NonBlockingStatsDClient(prefix, host, Integer.parseInt(port));
      } catch (final Exception e) {
         throw new ExceptionInInitializerError(e);
      }
   }
}
