package com.khubla.pragmatach.framework.router;

import java.net.URLDecoder;
import java.util.LinkedHashMap;
import java.util.List;

import com.khubla.pragmatach.framework.annotation.Route;
import com.khubla.pragmatach.framework.annotation.RouteParameter;
import com.khubla.pragmatach.framework.api.PragmatachException;
import com.khubla.pragmatach.framework.api.Request;
import com.khubla.pragmatach.framework.url.RouteSpecificationSegment;
import com.khubla.pragmatach.framework.url.URICracker;

/**
 * @author tome
 */
public class RouteFinder {
   /**
    * check that the static parts of the route match
    */
   private static boolean staticPartsMatch(PragmatachRoute pragmatachRoute, String[] crackedURI) throws PragmatachException {
      try {
         /*
          * check that the static path parts match
          */
         int j = 0;
         for (final RouteSpecificationSegment routeSpecificationSegment : pragmatachRoute.getRouteSpecification().getSegments()) {
            final String staticPathPart = routeSpecificationSegment.getPath();
            if (null != staticPathPart) {
               if (false == (crackedURI[j].compareTo(staticPathPart) == 0)) {
                  return false;
               }
            }
            j++;
         }
         return true;
      } catch (final Exception e) {
         throw new PragmatachException("Exception in staticPartsMatch", e);
      }
   }

   /**
    * check that the static parts of wildcard route match
    */
   private static boolean staticWildcardPartsMatch(PragmatachRoute pragmatachRoute, String[] crackedURI) throws PragmatachException {
      try {
         /*
          * check that the static path parts match
          */
         final List<RouteSpecificationSegment> routeSpecificationSegments = pragmatachRoute.getRouteSpecification().getSegments();
         for (int i = 0; i < (routeSpecificationSegments.size() - 1); i++) {
            final RouteSpecificationSegment routeSpecificationSegment = routeSpecificationSegments.get(i);
            final String staticPathPart = routeSpecificationSegment.getPath();
            if (null != staticPathPart) {
               if (false == (crackedURI[i].compareTo(staticPathPart) == 0)) {
                  return false;
               }
            }
         }
         return true;
      } catch (final Exception e) {
         throw new PragmatachException("Exception in staticPartsMatch", e);
      }
   }

   /**
    * the method parameters. key is the id specified in the routespecification, value is the value passed in the uri
    */
   private LinkedHashMap<String, String> parameterMap;
   /**
    * the route
    */
   private PragmatachRoute pragmatachRoute;

   /**
    * get the parameter map that the URI actually means
    */
   private LinkedHashMap<String, String> buildParameterMap(PragmatachRoute pragmatachRoute, String[] crackedURI) throws PragmatachException {
      try {
         /*
          * the ret
          */
         final LinkedHashMap<String, String> ret = new LinkedHashMap<String, String>();
         /*
          * check if wildcard
          */
         if (false == pragmatachRoute.isWildcardRoute()) {
            /*
             * walk the route specification
             */
            int i = 0;
            for (final RouteSpecificationSegment routeSpecificationSegment : pragmatachRoute.getRouteSpecification().getSegments()) {
               final String id = routeSpecificationSegment.getVariableId();
               if ((null != id) && (id.length() > 0)) {
                  /*
                   * URL decode the parameter
                   */
                  final String decodedParameter = URLDecoder.decode(crackedURI[i], "UTF-8");
                  /*
                   * set the parameter
                   */
                  ret.put(id, decodedParameter);
               }
               i++;
            }
         } else {
            /*
             * hacky, but it works
             */
            for (int i = pragmatachRoute.getSegmentCount() - 1; i < crackedURI.length; i++) {
               ret.put(crackedURI[i], crackedURI[i]);
            }
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

   public LinkedHashMap<String, String> getParameterMap() {
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
         final String[] crackedURI = URICracker.crackURI(request.getResourcePath());
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
                  /*
                   * remember the matched controller
                   */
                  this.pragmatachRoute = pragmatachRoute;
                  /*
                   * return true to indicate a match
                   */
                  return true;
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
         if (false == pragmatachRoute.isWildcardRoute()) {
            /*
             * there is a uri?
             */
            if (null != crackedURI) {
               /*
                * # of segments passed matches the route specification number of segments?
                */
               if (crackedURI.length == pragmatachRoute.getSegmentCount()) {
                  /*
                   * walk the route annotations
                   */
                  int i = 0;
                  final List<RouteParameter> routeParameters = pragmatachRoute.getBoundRouteParameters();
                  if ((null != routeParameters) && (routeParameters.size() > 0)) {
                     for (final RouteParameter routeParameter : routeParameters) {
                        /*
                         * check regex
                         */
                        final String regex = routeParameter.regex();
                        if ((null != regex) && (regex.length() > 0)) {
                           if (false == crackedURI[i].matches(regex)) {
                              return false;
                           }
                        }
                        i++;
                     }
                  }
                  /*
                   * check that the static path parts match
                   */
                  if (false == staticPartsMatch(pragmatachRoute, crackedURI)) {
                     return false;
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
            } else {
               if ((0 == pragmatachRoute.getParameterCount()) && (0 == pragmatachRoute.getSegmentCount())) {
                  /*
                   * no parameters; its a match if the static parts match
                   */
                  return staticPartsMatch(pragmatachRoute, crackedURI);
               } else {
                  /*
                   * the route requires parameters, and none were passed
                   */
                  return false;
               }
            }
         } else {
            /*
             * check that the static path parts match
             */
            if (false == staticWildcardPartsMatch(pragmatachRoute, crackedURI)) {
               return false;
            }
            /*
             * matches
             */
            return true;
         }
      } catch (final Exception e) {
         throw new PragmatachException("Exception in matchesRouteSpecification", e);
      }
   }
}
