package com.khubla.pragmatach.framework.annotation;

import java.lang.annotation.*;

/**
 * @author tome
 */
@Documented
@Target({ ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface Controller {
	public enum Scope {
		session, request
	};

	String name() default "";

	Scope scope() default Scope.request;
}