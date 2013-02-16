package com.khubla.pragmatach.framework.api;

import java.io.InputStream;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.khubla.pragmatach.framework.api.form.Form;
import com.khubla.pragmatach.framework.api.form.FormItem;

/**
 * @author tome
 */
public class Request {
   /**
    * HttpServletRequest
    */
   private final HttpServletRequest httpServletRequest;

   /**
    * ctor
    */
   public Request(HttpServletRequest httpServletRequest) {
      this.httpServletRequest = httpServletRequest;
   }

   /**
    * get form POST data
    */
   public Form getFormData() throws PragmatachException {
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
    * headers
    */
   @SuppressWarnings("unchecked")
   public Map<String, String> getHeaders() {
      final Map<String, String> ret = new HashMap<String, String>();
      final Enumeration<String> enumer = httpServletRequest.getHeaderNames();
      while (enumer.hasMoreElements()) {
         final String key = enumer.nextElement();
         ret.put(key, httpServletRequest.getHeader(key));
      }
      return ret;
   }

   public InputStream getInputStream() throws PragmatachException {
      try {
         return httpServletRequest.getInputStream();
      } catch (final Exception e) {
         throw new PragmatachException("Exception in getInputStream", e);
      }
   }

   /**
    * servlet context
    */
   public ServletContext getServletContext() {
      return httpServletRequest.getSession().getServletContext();
   }

   /**
    * session
    */
   public HttpSession getSession() {
      return httpServletRequest.getSession();
   }

   /**
    * URI
    */
   public String getURI() {
      return httpServletRequest.getRequestURI();
   }
}
