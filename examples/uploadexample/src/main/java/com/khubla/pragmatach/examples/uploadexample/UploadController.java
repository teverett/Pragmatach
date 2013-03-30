package com.khubla.pragmatach.examples.uploadexample;

import com.khubla.pragmatach.framework.annotation.Controller;
import com.khubla.pragmatach.framework.annotation.Route;
import com.khubla.pragmatach.framework.annotation.Route.HttpMethod;
import com.khubla.pragmatach.framework.api.PragmatachException;
import com.khubla.pragmatach.framework.api.Response;
import com.khubla.pragmatach.framework.controller.BasicBeanBoundController;

/**
 * @author tome
 */
@Controller()
public class UploadController extends BasicBeanBoundController {
   /**
    * filedata. This is the name of the input element in the HTML
    */
   private byte[] filedata;

   @Route(uri = "/upload", method = HttpMethod.post)
   public Response render() throws PragmatachException {
      return ok();
   }

   public byte[] getFiledata() {
      return filedata;
   }

   public void setFiledata(byte[] filedata) {
      this.filedata = filedata;
   }
}