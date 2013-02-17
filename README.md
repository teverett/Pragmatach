Pragmatach - A pragmatic web framework
==========

Pragmatach is a simple web framework aimed at providing rapid development of web applications using technologies which are familiar to developers developing real-world sites.   Technologies in use in Pragmatach include:

* Minimum client-side requirement; browser programmers should be free of restrictions
* Automatic generation of project skeletons
* Projects built via maven
* Java as the core programming languge, rather than Groovy or Scala
* Grails-like routing via annotations
* FreeMarker as the templating langauge, rather than GSP, JSP or Scala pages
* Pluggable template processors; Velocity or JSP should be supportable
* Simple configuration of controllers via annotations, rather that XML
* JSON rather than XML

Creating a test project
------------------------

To create the skeleton of a test project run:

`java -jar pragmatach-console --operation=new --name=myeproject --namespace=com.myproject`

This will generate:

* A maven pom.xml which includes the Pragmatach framework
* A simple controller and freemarker template for an index page
* A web.xml that includes the Pragmatach servlet

An example Controller
------------------------

<pre><code>
package com.khubla.com.pragmatach.exampleproject;

import com.khubla.pragmatach.framework.annotation.Controller;
import com.khubla.pragmatach.framework.annotation.Route;
import com.khubla.pragmatach.framework.api.PragmatachException;
import com.khubla.pragmatach.framework.api.Request;
import com.khubla.pragmatach.framework.api.Response;
import com.khubla.pragmatach.plugin.freemarker.FreemarkerController;
import com.khubla.pragmatach.plugin.freemarker.FreemarkerTemplate;

@Controller
@FreemarkerTemplate(template="index.ftl")
public class IndexController extends FreemarkerController {   
   @Route(uri = "/")
   public Response render(Request request) throws PragmatachException {
      return super.render(request);
   }
}
</code></pre>


