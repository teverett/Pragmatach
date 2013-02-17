package com.khubla.pragmatach.framework.form;

import java.util.Hashtable;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.khubla.pragmatach.framework.api.PragmatachException;

/**
 * @author tome
 */
public class Form {
   /**
    * get form POST data
    */
   public static Form parse(HttpServletRequest httpServletRequest) throws PragmatachException {
      try {
         /*
          * POST is actually a form?
          */
         if (ServletFileUpload.isMultipartContent(httpServletRequest)) {
            /*
             * item factory
             */
            final FileItemFactory fileItemFactory = new DiskFileItemFactory();
            /*
             * the upload
             */
            final ServletFileUpload servletFileUpload = new ServletFileUpload(fileItemFactory);
            /*
             * the items
             */
            @SuppressWarnings("unchecked")
            final List<FileItem> fileItems = servletFileUpload.parseRequest(httpServletRequest);
            /*
             * the hashtable
             */
            final Hashtable<String, FormItem> formItems = new Hashtable<String, FormItem>();
            /*
             * walk the items
             */
            for (final FileItem fileItem : fileItems) {
               final FormItem formItem = new FormItem(fileItem.getFieldName(), fileItem.getString(), fileItem.getContentType());
               formItems.put(formItem.getName(), formItem);
            }
            /*
             * return the form
             */
            return new Form(formItems);
         } else {
            /*
             * invalid form encoding
             */
            return null;
         }
      } catch (final Exception e) {
         throw new PragmatachException("Exception in process", e);
      }
   }

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

   public Hashtable<String, FormItem> getItems() {
      return items;
   }

   public int size() {
      if (null != items) {
         return items.size();
      }
      return 0;
   }
}
