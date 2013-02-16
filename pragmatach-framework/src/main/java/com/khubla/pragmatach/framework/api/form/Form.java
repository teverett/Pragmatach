package com.khubla.pragmatach.framework.api.form;

import java.util.Hashtable;

/**
 * @author tome
 */
public class Form {
   /**
    * items
    */
   private final Hashtable<String, FormItem> items;

   /**
    * ctor
    */
   public Form(Hashtable<String, FormItem> items) {
      this.items = items;
   }

   public FormItem getFormItem(String name) {
      if (null != items) {
         return items.get(name);
      }
      return null;
   }

   public String getFormItemValue(String name) {
      if (null != items) {
         final FormItem formItem = items.get(name);
         if (null != formItem) {
            return formItem.getValue();
         }
      }
      return null;
   }

   public int size() {
      if (null != items) {
         return items.size();
      }
      return 0;
   }
}
