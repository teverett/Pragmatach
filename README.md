Pragmatach - A pragmatic web framework
==========

Pragmatach is a simple web framework aimed at providing rapid development of web applications using technologies which are familiar to developers developing real-world sites.   Technologies in use in Pragmatach include:

* Automatic generation of project skeletons
* Projects built via maven
* Java as the core programming languge, rather than Groovy or Scala
* Grails-like routing
* FreeMarker as the templating langauge, rahter than GSP, JSP or Scala pages
* Simple configuration via annotations, rahter that XML
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


>package com.khubla.com.pragmatach.exampleproject;
>
>import com.khubla.pragmatach.framework.annotation.Controller;
>import com.khubla.pragmatach.framework.annotation.Route;
>import com.khubla.pragmatach.framework.api.PragmatachException;
>import com.khubla.pragmatach.framework.api.Request;
>import com.khubla.pragmatach.framework.api.Response;
>import com.khubla.pragmatach.plugin.freemarker.FreemarkerController;
>import com.khubla.pragmatach.plugin.freemarker.FreemarkerTemplate;
>
>@Controller
>@FreemarkerTemplate(template="index.ftl")
>public class IndexController extends FreemarkerController {   
>   @Route(uri = "/")
>   public Response render(Request request) throws PragmatachException {
>      return super.render(request);
>   }
>}


An example Template
------------------------

><h1>exampleproject</h1>
>Hello World

