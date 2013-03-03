package com.khubla.pragmatach.framework.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.log4j.Logger;

import com.khubla.pragmatach.framework.annotation.AnnotationsScanner;
import com.khubla.pragmatach.framework.plugin.Plugins;
import com.khubla.pragmatach.framework.router.PragmatachRoute;
import com.khubla.pragmatach.framework.router.PragmatachRoutes;

/**
 * @author tome
 */
public class StartupListener implements ServletContextListener {
   /**
    * logger
    */
   private final Logger logger = Logger.getLogger(this.getClass());

   @Override
   public void contextDestroyed(ServletContextEvent servletContextEvent) {
      // TODO Auto-generated method stub
   }

   @Override
   public void contextInitialized(ServletContextEvent servletContextEvent) {
      try {
         /*
          * scan the annotations (@Route and @Controller)
          */
         AnnotationsScanner.scan(servletContextEvent.getServletContext());
         /*
          * scan the plugins
          */
         Plugins.scan(servletContextEvent.getServletContext());
         /*
          * this loads and validates the routes
          */
         PragmatachRoutes.getInstance();
         /*
          * report the routes
          */
         reportRoutes();
      } catch (final Exception e) {
         logger.fatal("Exception in contextInitialized", e);
      }
   }

   private void reportRoutes() {
      try {
         /*
          * get routes
          */
         PragmatachRoutes pragmatachRoutes = PragmatachRoutes.getInstance();
         /*
          * GET routes
          */
         logger.info("Ordered GET routes");
         for (PragmatachRoute pragmatachRoute : pragmatachRoutes.getGETRoutes()) {
            logger.info("GET " + pragmatachRoute.getDescription());
         }
         /*
          * POST routes
          */
         logger.info("Ordered POST routes");
         for (PragmatachRoute pragmatachRoute : pragmatachRoutes.getPOSTRoutes()) {
            logger.info("POST " + pragmatachRoute.getDescription());
         }
      } catch (Exception e) {
         logger.fatal("Exceptioin in reportRoutes", e);
         e.printStackTrace();
      }
   }
}