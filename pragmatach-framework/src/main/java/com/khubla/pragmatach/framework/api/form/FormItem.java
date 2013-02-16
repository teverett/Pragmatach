package com.khubla.pragmatach.framework.api.form;

/**
 * @author tome
 */
public class FormItem {
   /**
    * name
    */
   private final String name;
   /**
    * value
    */
   private final String value;
   /**
    * content type
    */
   private final String contentType;

   /**
    * ctor
    */
   public FormItem(String name, String value, String contentType) {
      this.name = name;
      this.value = value;
      this.contentType = contentType;
   }

   public String getContentType() {
      return contentType;
   }

   public String getName() {
      return name;
   }

   public String getValue() {
      return value;
   }
}
