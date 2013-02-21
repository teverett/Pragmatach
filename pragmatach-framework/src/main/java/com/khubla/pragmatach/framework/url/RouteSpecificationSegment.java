package com.khubla.pragmatach.framework.url;

/**
 * @author tome
 */
public class RouteSpecificationSegment {
   /**
    * the variable id
    */
   private final String variableId;
   /**
    * the static path part
    */
   private final String path;

   /**
    * ctor
    */
   public RouteSpecificationSegment(String path, String variableId) {
      this.variableId = variableId;
      this.path = path;
   }

   public String getPath() {
      return path;
   }

   public String getVariableId() {
      return variableId;
   }
}
