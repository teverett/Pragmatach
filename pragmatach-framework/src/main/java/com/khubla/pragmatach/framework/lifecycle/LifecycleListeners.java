package com.khubla.pragmatach.framework.lifecycle;

import java.util.HashSet;
import java.util.Set;

/**
 * @author tome
 */
public class LifecycleListeners {
   /**
    * listener
    */
   private final Set<LifecycleListener> lifecycleListeners = new HashSet<LifecycleListener>();

   /**
    * add
    */
   public void add(LifecycleListener lifecycleListener) {
      lifecycleListeners.add(lifecycleListener);
   }

   public Set<LifecycleListener> getLifecycleListeners() {
      return lifecycleListeners;
   }
}
