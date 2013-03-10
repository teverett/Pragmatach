package com.khubla.pragmatach.framework.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.log4j.Logger;

import com.khubla.pragmatach.framework.controller.ControllerClasses;
import com.khubla.pragmatach.framework.i8n.I8NProviders;
import com.khubla.pragmatach.framework.plugin.PluginDescriptor;
import com.khubla.pragmatach.framework.plugin.PluginDescriptors;
import com.khubla.pragmatach.framework.router.PragmatachRoute;
import com.khubla.pragmatach.framework.router.PragmatachRoutes;
import com.khubla.pragmatach.framework.scanner.AnnotationScanner;

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
          * scan the annotations (@Route and @Controller, and everything else)
          */
         AnnotationScanner.scan(servletContextEvent.getServletContext());
         /*
          * find the controllers and routes
          */
         ControllerClasses.buildDB();
         /*
          * scan the plugins
          */
         PluginDescriptors.scan(servletContextEvent.getServletContext());
         /*
          * start all the plugins
          */
         for (final PluginDescriptor pluginDescriptor : PluginDescriptors.getPlugins().values()) {
            pluginDescriptor.getPlugin().startup();
         }
         /*
          * this loads and validates the routes
          */
         PragmatachRoutes.getInstance();
         /*
          * report the routes
          */
         reportRoutes();
         /*
          * load the i8N providers
          */
         I8NProviders.getInstance();
      } catch (final Exception e) {
         logger.fatal("Exception in contextInitialized", e);
      }
   }

   private void reportRoutes() {
      try {
         /*
          * get routes
          */
         final PragmatachRoutes pragmatachRoutes = PragmatachRoutes.getInstance();
         /*
          * GET routes
          */
         logger.info("Ordered GET routes");
         for (final PragmatachRoute pragmatachRoute : pragmatachRoutes.getGETRoutes()) {
            logger.info("GET " + pragmatachRoute.getDescription());
         }
         /*
          * POST routes
          */
         logger.info("Ordered POST routes");
         for (final PragmatachRoute pragmatachRoute : pragmatachRoutes.getPOSTRoutes()) {
            logger.info("POST " + pragmatachRoute.getDescription());
         }
      } catch (final Exception e) {
         logger.fatal("Exceptioin in reportRoutes", e);
         e.printStackTrace();
      }
   }
}