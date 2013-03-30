package com.khubla.pragmatach.framework.controller.interceptor;

import java.lang.reflect.Method;

import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

/**
 * @author tome
 */
public class ControllerMethodInterceptor implements MethodInterceptor {
   @Override
   public Object intercept(Object object, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
      return methodProxy.invokeSuper(object, args);
   }
}
