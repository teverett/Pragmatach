package com.khubla.pragmatach.framework.controller.impl;

import com.khubla.pragmatach.framework.annotation.Controller;
import com.khubla.pragmatach.framework.controller.impl.trivial.TrivialController;

/**
 * @author tome
 */
@Controller(name = "pragmatachNotFoundController")
public class NotFoundController extends TrivialController {
   public NotFoundController() {
      super(404);
   }
}
