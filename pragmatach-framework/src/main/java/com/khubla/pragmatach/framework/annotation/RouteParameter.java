package com.khubla.pragmatach.framework.annotation;

import java.lang.annotation.*;

/**
 * @author tome
 */
@Documented
@Target({ ElementType.PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface RouteParameter {
	String name() default "";

	String regex() default "";
}