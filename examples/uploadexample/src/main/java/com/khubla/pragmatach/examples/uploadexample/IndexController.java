package com.khubla.pragmatach.examples.uploadexample;

import com.khubla.pragmatach.framework.annotation.Controller;
import com.khubla.pragmatach.framework.annotation.Route;
import com.khubla.pragmatach.framework.api.PragmatachException;
import com.khubla.pragmatach.framework.api.Response;
import com.khubla.pragmatach.framework.controller.AbstractController;

@Controller()
public class IndexController extends AbstractController {
   @Route(uri = "/")
   public Response render() throws PragmatachException {
      return ok();
   }
}