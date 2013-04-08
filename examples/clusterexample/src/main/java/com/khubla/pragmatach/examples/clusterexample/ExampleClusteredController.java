package com.khubla.pragmatach.examples.clusterexample;

import com.khubla.pragmatach.framework.annotation.Controller;
import com.khubla.pragmatach.framework.controller.impl.AbstractController;
import com.khubla.pragmatach.plugin.cluster.annotation.Clustered;

/**
 * @author tome
 */
@Clustered
@Controller(name = "exampleClusteredController")
public class ExampleClusteredController extends AbstractController {
   private int count = 0;

   public int getCount() {
      return count;
   }

   public void setCount(int count) {
      this.count = count;
   }
}
