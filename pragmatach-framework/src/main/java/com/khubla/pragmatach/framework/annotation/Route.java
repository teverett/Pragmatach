package com.khubla.pragmatach.framework.annotation;

import java.lang.annotation.*;

/**
 * @author tome
 *
 *         <pre>
 * Routes are of the syntax /a/b/c/d.
 * If a part is wrapped in {} it's a dynamic part.
 * The dynamic part consists of an optional regex followed by @ followed by a name.
 *         </pre>
 */
@Documented
@Target({ ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface Route {
	public enum HttpMethod {
		get, post;
	}

	HttpMethod method() default HttpMethod.get;

	String uri() default "";
}