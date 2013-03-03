package com.khubla.pragmatach.framework.jmx.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.khubla.pragmatach.framework.controller.Controllers;
import com.khubla.pragmatach.framework.jmx.SystemStatusMXBean;
import com.khubla.pragmatach.framework.plugin.PluginDescriptor;
import com.khubla.pragmatach.framework.plugin.PluginDescriptors;
import com.khubla.pragmatach.framework.router.PragmatachRoute;
import com.khubla.pragmatach.framework.router.PragmatachRoutes;
import com.khubla.pragmatach.framework.servlet.PragmatachServlet;

/**
 * @author tome
 */
public class SystemStatus implements SystemStatusMXBean {
   /**
    * logger
    */
   private final Logger logger = Logger.getLogger(this.getClass());

   @Override
   public String[] getConfiguration() {
      try {
         final List<String> configurations = new ArrayList<String>();
         for (final String key : PragmatachServlet.getConfiguration().getAll().keySet()) {
            configurations.add(key + ":" + PragmatachServlet.getConfiguration().getAll().get(key));
         }
         final String[] ret = new String[configurations.size()];
         configurations.toArray(ret);
         return ret;
      } catch (final Exception e) {
         logger.error("Exception in getConfiguration", e);
         return null;
      }
   }

   @Override
   public String[] getControllers() {
      try {
         final List<String> controllerDescriptions = new ArrayList<String>();
         for (final Class<?> controllerClass : Controllers.getInstance().getControllers()) {
            controllerDescriptions.add(controllerClass.getName());
         }
         final String[] ret = new String[controllerDescriptions.size()];
         controllerDescriptions.toArray(ret);
         return ret;
      } catch (final Exception e) {
         logger.error("Exception in getControllers", e);
         return null;
      }
   }

   @Override
   public String[] getPlugins() {
      try {
         final List<String> pluginDescriptions = new ArrayList<String>();
         for (final PluginDescriptor plugin : PluginDescriptors.getPlugins().values()) {
            pluginDescriptions.add(plugin.getName());
         }
         final String[] ret = new String[pluginDescriptions.size()];
         pluginDescriptions.toArray(ret);
         return ret;
      } catch (final Exception e) {
         logger.error("Exception in getPlugins", e);
         return null;
      }
   }

   @Override
   public String[] getRouters() {
      try {
         final List<String> routeDescriptions = new ArrayList<String>();
         for (final PragmatachRoute pragmatachRoute : PragmatachRoutes.getInstance().getGETRoutes()) {
            routeDescriptions.add(pragmatachRoute.getDescription());
         }
         for (final PragmatachRoute pragmatachRoute : PragmatachRoutes.getInstance().getPOSTRoutes()) {
            routeDescriptions.add(pragmatachRoute.getDescription());
         }
         final String[] ret = new String[routeDescriptions.size()];
         routeDescriptions.toArray(ret);
         return ret;
      } catch (final Exception e) {
         logger.error("Exception in getRouters", e);
         return null;
      }
   }
}
