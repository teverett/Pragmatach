An example FreeMarker Controller
------------------------

<pre><code>
package com.khubla.com.pragmatach.exampleproject;

import com.khubla.pragmatach.framework.annotation.Controller;
import com.khubla.pragmatach.framework.annotation.View;
import com.khubla.pragmatach.framework.annotation.Route;
import com.khubla.pragmatach.framework.api.PragmatachException;
import com.khubla.pragmatach.framework.api.Response;
import com.khubla.pragmatach.plugin.freemarker.FreemarkerController;

@Controller(name="indexController")
@View(view = "index.html")
public class IndexController extends FreemarkerController {
   @Route(uri = "/")
   public Response render() throws PragmatachException {
      return super.render();
   }
}
</code></pre>

An example FreeMarker Template
------------------------

    <!DOCTYPE html>
    <head>
       <title>exampleproject</title>
    </head>
    <body>
    <h1>exampleproject</h1>
        <img src="/public/logo.png">
        ${indexController.message}
        <h2>Current Time</h2>
        ${indexController.time}
    </body>
