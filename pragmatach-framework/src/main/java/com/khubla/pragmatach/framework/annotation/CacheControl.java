package com.khubla.pragmatach.framework.annotation;

import java.lang.annotation.*;

/**
 * @author tome
 */
@Documented
@Target({ ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface CacheControl {
	int maxAge() default -1;

	String policy() default "public";

	int sMaxAge() default -1;
}