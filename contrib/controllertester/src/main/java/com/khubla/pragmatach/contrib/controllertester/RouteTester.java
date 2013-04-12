package com.khubla.pragmatach.contrib.controllertester;

import java.util.List;

import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

/**
 * @author tome
 */
public class RouteTester {
   /**
    * client
    */
   private static final DefaultHttpClient client = new DefaultHttpClient();

   public static String generateRandomUrl(String host, RouteUrl routeUrl) {
      String ret = host;
      final List<String> parts = routeUrl.getParts();
      for (final String part : parts) {
         ret += "/" + part;
      }
      return ret;
   }

   /**
    * call route, report if we get something other than 200
    */
   public static void testRoute(String host, RouteUrl routeUrl, int trials) throws Exception {
      try {
         if (routeUrl.getMethod().compareTo("get") == 0) {
            /*
             * url
             */
            final String url = generateRandomUrl(host, routeUrl);
            /*
             * show the url
             */
            System.out.println(url);
            /*
             * get
             */
            final HttpGet httpGet = new HttpGet(url);
            /*
             * get the return code
             */
            final int ret = client.execute(httpGet).getStatusLine().getStatusCode();
            /*
             * done
             */
            httpGet.releaseConnection();
            /*
             * check
             */
            if (200 != ret) {
               System.out.println(ret + " " + routeUrl.getContext());
            }
         }
      } catch (final Exception e) {
         throw new Exception("Exception in testRoute: '" + routeUrl.getContext() + "'", e);
      }
   }
}
