package com.khubla.pragmatach.framework.api;

import java.util.Hashtable;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author tome
 */
public class Cookies {
   /**
    * for long term cookies
    */
   private static final int SECONDS_PER_YEAR = 60 * 60 * 24 * 365;
   /**
    * HttpServletRequest
    */
   private final HttpServletRequest httpServletRequest;
   /**
    * HttpServletResponse
    */
   private final HttpServletResponse httpServletResponse;

   /**
    * ctor
    */
   public Cookies(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
      this.httpServletRequest = httpServletRequest;
      this.httpServletResponse = httpServletResponse;
   }

   /**
    * clear all cookies
    */
   public void clearAll() {
      final Cookie[] cookies = httpServletRequest.getCookies();
      if ((null != cookies) && (cookies.length > 0)) {
         for (final Cookie cookie : cookies) {
            removeCookie(cookie.getName());
         }
      }
   }

   /**
    * get a cookie by name
    */
   public String getCookie(String name) {
      if ((null != name) && (name.length() > 0)) {
         final Hashtable<String, String> cookies = getCookies();
         if (null != cookies) {
            return cookies.get(name);
         }
      }
      return null;
   }

   /**
    * get all the cookies
    */
   public Hashtable<String, String> getCookies() {
      final Cookie[] cookies = httpServletRequest.getCookies();
      if ((null != cookies) && (cookies.length > 0)) {
         final Hashtable<String, String> ret = new Hashtable<String, String>();
         for (final Cookie cookie : cookies) {
            ret.put(cookie.getName(), cookie.getValue());
         }
         return ret;
      }
      return null;
   }

   /**
    * remove a cookie
    */
   public void removeCookie(String name) {
      final Cookie cookie = new Cookie(name, null);
      cookie.setMaxAge(0);
      httpServletResponse.addCookie(cookie);
   }

   /**
    * set a cookie
    */
   public void setCookie(String name, String value) {
      final Cookie cookie = new Cookie(name, value);
      cookie.setMaxAge(SECONDS_PER_YEAR);
      cookie.setPath("/");
      httpServletResponse.addCookie(cookie);
   }
}
