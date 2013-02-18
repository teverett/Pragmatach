package com.khubla.pragmatach.framework.controller.impl;

import com.khubla.pragmatach.framework.annotation.Controller;

/**
 * @author tome
 */
@Controller
public class NotFoundController extends TrivialController {
   public NotFoundController() {
      super(404);
   }
}
