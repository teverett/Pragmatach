An example Gson Controller
------------------------

<pre><code>
package com.khubla.com.pragmatach.examples.gsonexample;

import java.util.Date;

import com.khubla.pragmatach.framework.annotation.Controller;
import com.khubla.pragmatach.framework.annotation.Route;
import com.khubla.pragmatach.framework.api.PragmatachException;
import com.khubla.pragmatach.framework.api.Response;
import com.khubla.pragmatach.plugin.gson.GSONController;

@Controller(name="index")
public class IndexController extends GSONController {
   /**
    * the message
    */
   private final String message = "hello world";
   /**
    * the time
    */
   private String time;

   public String getMessage() {
      return message;
   }

   public String getTime() {
      return time;
   }

   @Route(uri = "/")
   public Response render() throws PragmatachException {
      time = new Date().toString();
      return super.render();
   }

   public void setTime(String time) {
      this.time = time;
   }
}
</code></pre>

The output produced is

`{"message":"hello world","time":"Sun Feb 17 14:28:34 MST 2013"}`
