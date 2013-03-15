package com.khubla.pragmatach.plugin.cluster;

import java.lang.reflect.Method;

import javassist.util.proxy.MethodHandler;

/**
 * @author tome
 */
public class ControllerMethodHandler implements MethodHandler {
   @Override
   public Object invoke(Object self, Method thisMethod, Method proceed, Object[] args) throws Throwable {
      return proceed.invoke(self, args);
   }
}
