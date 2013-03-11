package com.khubla.pragmatach.plugin.cluster.thread;

import org.apache.log4j.Logger;

import com.khubla.pragmatach.framework.api.PragmatachException;

/**
 * @author tome
 */
public abstract class PeriodicThread extends Thread {
   /**
    * logger
    */
   private final Logger logger = Logger.getLogger(this.getClass());
   /**
    * interval
    */
   private final long interval;
   /**
    * running
    */
   private volatile boolean running = true;

   /**
    * ctor
    */
   public PeriodicThread(long interval) {
      this.interval = interval;
   }

   public abstract void exec() throws PragmatachException;

   @Override
   public void run() {
      try {
         while (running) {
            /*
             * exec
             */
            exec();
            /*
             * sleep
             */
            Thread.sleep(interval);
         }
      } catch (final Exception e) {
         logger.error("Exception in run", e);
      }
   }

   public void terminate() {
      running = false;
   }
}
