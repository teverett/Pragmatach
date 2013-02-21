package com.khubla.pragmatach.framework.router;

import java.util.Hashtable;
import java.util.List;

import com.khubla.pragmatach.framework.annotation.Route;
import com.khubla.pragmatach.framework.api.PragmatachException;
import com.khubla.pragmatach.framework.api.Request;
import com.khubla.pragmatach.framework.url.RouteSpecificationSegment;

/**
 * @author tome
 */
public class RouteFinder {
   /**
    * parse the URL parameters. If no parameters, returns null;
    */
   private static String[] crackURI(String uri) throws PragmatachException {
      try {
         String p = uri;
         if (p.startsWith("/")) {
            p = p.substring(1);
         }
         if ((p.length() > 0) && (p.contains("/"))) {
            return p.split("/");
         } else {
            if (p.length() > 0) {
               return new String[] { p };
            } else {
               return null;
            }
         }
      } catch (final Exception e) {
         throw new PragmatachException("Exception in parseParameters", e);
      }
   }

   /**
    * the method parameters. key is the id specified in the routespecification, value is the value passed in the uri
    */
   private Hashtable<String, String> parameterMap;
   /**
    * the route
    */
   private PragmatachRoute pragmatachRoute;

   /**
    * get the paramter map that the URI actually means
    */
   private Hashtable<String, String> buildParameterMap(PragmatachRoute pragmatachRoute, String[] crackedURI) throws PragmatachException {
      try {
         final Hashtable<String, String> ret = new Hashtable<String, String>();
         /*
          * walk the route specification
          */
         int i = 0;
         for (final RouteSpecificationSegment routeSpecificationSegment : pragmatachRoute.getRouteSpecification().getSegments()) {
            final String id = routeSpecificationSegment.getVariableName();
            if ((null != id) && (id.length() > 0)) {
               ret.put(id, crackedURI[i]);
            }
            i++;
         }
         return ret;
      } catch (final Exception e) {
         throw new PragmatachException("Exception in getParameters", e);
      }
   }

   /**
    * get the appropriate routes to search. These routes are already ordered.
    */
   private List<PragmatachRoute> getMethodRoutes(Route.HttpMethod httpMethod) throws PragmatachException {
      if (httpMethod == Route.HttpMethod.get) {
         return PragmatachRoutes.getInstance().getGETRoutes();
      } else {
         return PragmatachRoutes.getInstance().getPOSTRoutes();
      }
   }

   public Hashtable<String, String> getParameterMap() {
      return parameterMap;
   }

   public PragmatachRoute getPragmatachRoute() {
      return pragmatachRoute;
   }

   /**
    * Match route. Returns true if a matching route was found.
    * <p>
    * 
    * <pre>
    * There are multiple criteria for matching.  
    * 1 - Every part of the URI must match the name or regex
    * 2 - Values must be provided for each method signature variable
    * </pre>
    * <p>
    */
   public boolean match(Request request) throws PragmatachException {
      try {
         /*
          * crack the URI
          */
         final String[] crackedURI = crackURI(request.getResourcePath());
         /*
          * get the routes
          */
         final List<PragmatachRoute> pragmatachRoutes = getMethodRoutes(request.getMethod());
         if (null != pragmatachRoutes) {
            /*
             * check for matching regexs
             */
            for (final PragmatachRoute pragmatachRoute : pragmatachRoutes) {
               /*
                * route is a regex match at each segment
                */
               if (matchesRouteSpecification(pragmatachRoute, crackedURI)) {
                  /*
                   * build parameter map
                   */
                  parameterMap = buildParameterMap(pragmatachRoute, crackedURI);
               }
            }
         }
         /*
          * no match
          */
         return false;
      } catch (final Exception e) {
         throw new PragmatachException("Exception in match", e);
      }
   }

   /**
    * check that the number of segments matches and that each segment matches the regex, if there is one
    */
   private boolean matchesRouteSpecification(PragmatachRoute pragmatachRoute, String[] crackedURI) throws PragmatachException {
      try {
         /*
          * # of segments passed matches the route specification number of segments?
          */
         if (crackedURI.length == pragmatachRoute.getParameterCount()) {
            /*
             * walk the route segments
             */
            int i = 0;
            for (final RouteSpecificationSegment routeSpecificationSegment : pragmatachRoute.getRouteSpecification().getSegments()) {
               final String regex = routeSpecificationSegment.getRegex();
               if ((null != regex) && (regex.length() > 0)) {
                  if (false == crackedURI[i].matches(regex)) {
                     return false;
                  }
               }
               i++;
            }
            /*
             * everything matches
             */
            return true;
         } else {
            /*
             * its not a match
             */
            return false;
         }
      } catch (final Exception e) {
         throw new PragmatachException("Exception in matchesRouteSpecification", e);
      }
   }
}
