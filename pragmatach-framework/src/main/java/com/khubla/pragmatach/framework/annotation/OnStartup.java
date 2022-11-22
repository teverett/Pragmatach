package com.khubla.pragmatach.framework.annotation;

import java.lang.annotation.*;

/**
 * @author tome
 */
@Documented
@Target({ ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface OnStartup {
}