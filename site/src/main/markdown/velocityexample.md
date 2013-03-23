An example Velocity Controller
------------------------

<pre><code>
package com.khubla.com.pragmatach.exampleproject;

import com.khubla.pragmatach.framework.annotation.Controller;
import com.khubla.pragmatach.framework.annotation.View;
import com.khubla.pragmatach.framework.annotation.Route;
import com.khubla.pragmatach.framework.api.PragmatachException;
import com.khubla.pragmatach.framework.api.Response;
import com.khubla.pragmatach.plugin.velocity.VelocityController;

@Controller(name="index")
@View(view="index.ftl")
public class IndexController extends VelocityController {   
   @Route(uri = "/")
   public Response render(Request request) throws PragmatachException {
      return super.render(request);
   }
}
</code></pre>