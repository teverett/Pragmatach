package com.khubla.pragmatach.framework.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author tome
 */
@Documented
@Target({ ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
public @interface Route {
   public enum HttpMethod {
      get, post;
   }

   HttpMethod method() default HttpMethod.get;

   String path() default "";
}