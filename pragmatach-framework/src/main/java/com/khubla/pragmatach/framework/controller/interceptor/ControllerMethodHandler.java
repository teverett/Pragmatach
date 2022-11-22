package com.khubla.pragmatach.framework.controller.interceptor;

import java.lang.reflect.*;

import javassist.util.proxy.*;

/**
 * @author tome
 */
public class ControllerMethodHandler implements MethodHandler {
	@Override
	public Object invoke(Object object, Method thisMethod, Method proceed, Object[] args) throws Throwable {
		return proceed.invoke(object, args);
	}
}
