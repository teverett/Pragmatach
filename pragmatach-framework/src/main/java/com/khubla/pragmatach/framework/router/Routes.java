package com.khubla.pragmatach.framework.router;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import com.khubla.pragmatach.framework.api.PragmatachException;

/**
 * all routes
 * 
 * @author tome
 */
public class Routes {
   /**
    * routes file
    */
   private static final String ROUTES = "/routes";
   /**
    * singleton
    */
   private static Routes instance = null;

   /**
    * getter
    */
   public static Routes getInstance() throws PragmatachException {
      try {
         if (null == instance) {
            instance = new Routes();
         }
         return instance;
      } catch (final Exception e) {
         throw new PragmatachException(e);
      }
   }

   /**
    * GET routes
    */
   private final List<Route> GETRoutes = new ArrayList<Route>();
   /**
    * POST routes
    */
   private final List<Route> POSTRoutes = new ArrayList<Route>();

   /**
    * ctor
    */
   private Routes() throws PragmatachException {
      readRoutes();
   }

   public List<Route> getGETRoutes() {
      return GETRoutes;
   }

   public List<Route> getPOSTRoutes() {
      return POSTRoutes;
   }

   /**
    * read a route
    */
   private Route parseRoute(String line) throws PragmatachException {
      try {
         final String[] parts = line.replaceAll("\t", " ").split(" ");
         if ((null != parts) && (parts.length == 3)) {
            final String method = parts[0];
            final String parameters = parts[1];
            final String controller = parts[2];
            if (method.trim().toLowerCase().compareTo("get") == 0) {
               return new Route(Route.Method.get, parameters, controller);
            } else {
               return new Route(Route.Method.post, parameters, controller);
            }
         }
         return null;
      } catch (final Exception e) {
         throw new PragmatachException(e);
      }
   }

   /**
    * read the routes file
    */
   private void readRoutes() throws PragmatachException {
      try {
         final InputStream inputStream = Routes.class.getResourceAsStream(ROUTES);
         if (null != inputStream) {
            final BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            String line = null;
            while ((line = bufferedReader.readLine()) != null) {
               if (false == line.startsWith("#")) {
                  if (line.trim().length() > 0) {
                     final Route route = parseRoute(line.trim());
                     if (null != route) {
                        if (route.getMethod() == Route.Method.get) {
                           GETRoutes.add(route);
                        } else {
                           POSTRoutes.add(route);
                        }
                     } else {
                        throw new Exception("Failed to read route '" + line + "'");
                     }
                  }
               }
            }
         } else {
            throw new Exception("Could not read routes file '" + ROUTES + "'");
         }
      } catch (final Exception e) {
         throw new PragmatachException(e);
      }
   }
}
