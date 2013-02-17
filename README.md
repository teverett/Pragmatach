Pragmatach - A pragmatic web framework
==========

Pragmatach is a simple web framework aimed at providing rapid development of web applications using technologies which are familiar to developers developing real-world sites.   Technologies in use in Pragmatach include:

* Minimum client-side requirement; browser programmers should be free of restrictions
* Modular design allowing features such as Freemarker, Velocity and Jackson to be configured via maven dependencies
* Automatic JSON generation via Jackson
* Automatic generation of project skeletons
* Projects built via maven, with Jetty and Tomcat profiles included
* Java as the core programming languge, rather than Groovy or Scala
* Grails-like routing via annotations
* FreeMarker and Velocity as the templating langauges, rather than GSP, JSP or Scala pages
* Pluggable template processors; Freemarker and Velocity currently, JSP later
* Simple configuration of controllers via annotations, rather than XML

Creating a test project
------------------------

To create the skeleton of a test project run:

`java -jar pragmatach-console --operation=new --name=myeproject --namespace=com.myproject`

This will generate:

* A maven pom.xml which includes the Pragmatach framework
* A simple controller and freemarker template for an index page
* A web.xml that includes the Pragmatach servlet

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

@Controller
@View(view="index.ftl")
public class IndexController extends FreemarkerController {   
   @Route(uri = "/")
   public Response render(Request request) throws PragmatachException {
      return super.render(request);
   }
}
</code></pre>


An example Jackson Controller
------------------------

<pre><code>
package com.khubla.com.pragmatach.exampleproject;

import com.khubla.pragmatach.framework.annotation.Controller;
import com.khubla.pragmatach.framework.annotation.Route;
import com.khubla.pragmatach.framework.api.PragmatachException;
import com.khubla.pragmatach.framework.api.Response;
import com.khubla.pragmatach.plugin.jackson.JacksonController;

@Controller
public class IndexController extends JacksonController {   
   @Route(uri = "/")
   public Response render(Request request) throws PragmatachException {
      return super.render(request);
   }
}
</code></pre>

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

@Controller
@View(view="index.ftl")
public class IndexController extends VelocityController {   
   @Route(uri = "/")
   public Response render(Request request) throws PragmatachException {
      return super.render(request);
   }
}
</code></pre>

