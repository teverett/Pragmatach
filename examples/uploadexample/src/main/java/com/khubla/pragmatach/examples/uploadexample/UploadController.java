package com.khubla.pragmatach.examples.uploadexample;

import com.khubla.pragmatach.framework.annotation.Controller;
import com.khubla.pragmatach.framework.annotation.Route;
import com.khubla.pragmatach.framework.annotation.Route.HttpMethod;
import com.khubla.pragmatach.framework.api.PragmatachException;
import com.khubla.pragmatach.framework.api.Response;
import com.khubla.pragmatach.framework.controller.AbstractController;

/**
 * @author tome
 */
@Controller()
public class UploadController extends AbstractController {
   @Route(uri = "/upload", method = HttpMethod.post)
   public Response render() throws PragmatachException {
      return ok();
   }
}