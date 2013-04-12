package com.khubla.pragmatach.contrib.controllertester;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import au.com.bytecode.opencsv.CSVReader;

/**
 * @author tome
 */
public class RouteUrl {
   /**
    * read
    */
   public static List<RouteUrl> readRoutes(InputStream inputStream) throws Exception {
      try {
         final List<RouteUrl> ret = new ArrayList<RouteUrl>();
         final CSVReader reader = new CSVReader(new InputStreamReader(inputStream));
         String[] nextLine;
         while ((nextLine = reader.readNext()) != null) {
            if (nextLine[0].charAt(0) != '#') {
               final String method = nextLine[0];
               final String context = nextLine[1];
               final List<String> parts = new ArrayList<String>();
               for (int i = 2; i < nextLine.length; i++) {
                  parts.add(nextLine[i]);
               }
               ret.add(new RouteUrl(parts, method, context));
            }
         }
         reader.close();
         return ret;
      } catch (final Exception e) {
         throw new Exception("Exception in readRoutes", e);
      }
   }

   /**
    * url parts
    */
   private final List<String> parts;
   /**
    * method
    */
   private final String method;
   /**
    * context
    */
   private final String context;

   /**
    * ctor
    */
   public RouteUrl(List<String> parts, String method, String context) {
      this.parts = parts;
      this.method = method;
      this.context = context;
   }

   public String getContext() {
      return context;
   }

   public String getMethod() {
      return method;
   }

   public List<String> getParts() {
      return parts;
   }
}
