package com.khubla.pragmatach.framework.api;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;

/**
 * @author tome
 */
public class PragmatachException extends Exception {
   public static String getExceptionTrace(Throwable throwable) {
      final Writer result = new StringWriter();
      final PrintWriter printWriter = new PrintWriter(result);
      throwable.printStackTrace(printWriter);
      return result.toString();
   }

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