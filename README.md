Pragmatach - A pragmatic MVC framework for Web Developers
==========

Pragmatach is a simple web framework aimed at providing rapid development of web applications using technologies which are familiar to developers developing real-world sites.  Pragmatach was developed to enable rapid development of sites by Javascript and CSS3 developers who need a supporting MVC framework, but don't wish to have to learn a new programming language such as Groovy or Scala, and who wish to have powerful features such built-in JSON support, and annotation driven request routing.

Technologies in use in Pragmatach include:

* Minimum client-side requirement; browser programmers should be free of restrictions
* Modular design allowing features such as Freemarker, Velocity, XStream and Gson to be configured via maven dependencies
* Automatic JSON generation via Gson
* Automatic generation of project skeletons
* Projects built via maven, with Jetty and Tomcat profiles included
* Java as the core programming languge, rather than Groovy or Scala
* Grails-like routing via annotations
* FreeMarker and Velocity as the templating langauges, rather than GSP, JSP or Scala pages
* Pluggable template processors; Freemarker and Velocity currently, JSP later
* Simple configuration of controllers via annotations, rather than XML

Pragmatach does not include ORM support.  Hibernate, iBatis or Ebean can be used if required.

Creating a test project
------------------------

To create the skeleton of a test project use the pragmatach maven archetype

<pre>
<code>
mvn archetype:generate                           \
  -DarchetypeGroupId=com.khubla.pragmatach       \
  -DarchetypeArtifactId=pragmatach-archetype     \
  -DarchetypeVersion=1.0-SNAPSHOT                \
  -DgroupId=com.khubla.exampleproject            \
  -DartifactId=myexampleproject
</code>
</pre>

This will generate:

* A maven pom.xml which includes the Pragmatach framework
* A simple controller and freemarker template for an index page
* A web.xml that includes the Pragmatach servlet

To run the generated project, type:

<pre>
<code>
cd myexampleproject
mvn clean package jetty:run
</code>
</pre>

Then browse to:

`http:\\localhost:8080`

Administration Console
------------------------

Pragmatach has a built in web administration console, just browse to the context path

`/public/system/pragmatach.html`

in your application.

Request Routing
------------------------

Pragmatch uses REST-like route mapping. Request routes are declared via the @Route annotation, in conjunction with the signature of the annotated method

In this example, the method "render()" will be called for the context path "/".

<pre><code>
@Route(uri = "/")
public Response render() throws PragmatachException
</code></pre>

In this example the method "myMethod(int)" will be called with the value "2" for the context path "/mycontroller/2".

<pre><code>
@Route(uri = "/mycontroller")
public Response render(int myValue) throws PragmatachException
</code></pre>

Controller Scope
------------------------

By default, all Controllers are Request scoped.  However, Controllers can be declared as Session Scoped:

`@Controller(name="myController", scope = Controller.Scope.session)`

HTTP Session
------------------------

Templating engines bind the HttpSession to the name "session".  So, to access the session in FreeMarker:

`${session}`

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

Population of Controller fields via HTTP Form POST
------------------------

A typical HTML Form:

    <form action="/showfeed" method="post" enctype="multipart/form-data">
        <input name="feedURL" type="text">
        <button type="submit" class="btn">Submit</button>
    </form> 

When the form is posted to a controller route of type "HttpMethod.post" the Controller field "feedURL" will be automatically populated

<pre><code>
@Controller(name="index")
@View(view = "acceptPost.html")
public class AcceptPostController extends FreemarkerController {
   /**
    * the URL
    */
   private String feedURL;

   public String getFeedURL() {
      return feedURL;
   }

   public void setFeedURL(String feedURL) {
      this.feedURL = feedURL;
   }

   @Route(uri = "/acceptpost", method = HttpMethod.post)
   public Response acceptpost() throws PragmatachException {
      try {
         if (null != feedURL) {
     
         }
         /*
          * render
          */
         return super.render();
      } catch (final Exception e) {
         throw new PragmatachException("Exception in showFeed", e);
      }
   }
}
</code></pre>

Static Resources
------------------------

In order to differentiate static resources from Pragmatach pages, static resources are served from under the context path `/public`.



