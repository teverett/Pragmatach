package com.khubla.pragmatach.plugin.cluster.controller;

import com.khubla.pragmatach.framework.annotation.Controller;
import com.khubla.pragmatach.framework.controller.impl.AbstractController;
import com.khubla.pragmatach.plugin.cluster.annotation.Clustered;

/**
 * @author tome
 */
@Clustered
@Controller(name = "serverInstance")
public class ServerInstanceController extends AbstractController {
   private String exampleString;

   public String getExampleString() {
      return exampleString;
   }

   public void setExampleString(String exampleString) {
      this.exampleString = exampleString;
   }
}
