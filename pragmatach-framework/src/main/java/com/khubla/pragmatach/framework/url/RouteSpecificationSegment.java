package com.khubla.pragmatach.framework.url;

/**
 * @author tome
 */
public class RouteSpecificationSegment {
   /**
    * the regex
    */
   private final String regex;
   /**
    * the variable name
    */
   private final String variableName;
   /**
    * the static path part
    */
   private final String path;

   /**
    * ctor
    */
   public RouteSpecificationSegment(String path, String regex, String variableName) {
      this.regex = regex;
      this.variableName = variableName;
      this.path = path;
   }

   public String getPath() {
      return path;
   }

   public String getRegex() {
      return regex;
   }

   public String getVariableName() {
      return variableName;
   }
}
