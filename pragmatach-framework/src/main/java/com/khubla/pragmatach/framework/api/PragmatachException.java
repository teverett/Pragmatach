package com.khubla.pragmatach.framework.api;

/**
 * @author tome
 */
public class PragmatachException extends Exception {
   private static final long serialVersionUID = 1L;

   public PragmatachException(Exception e) {
      super(e);
   }

   public PragmatachException(String e) {
      super(e);
   }

   public PragmatachException(String s, Exception e) {
      super(s, e);
   }
}