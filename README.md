Pragmatach - A pragmatic MVC framework for Web Developers
==========

Pragmatach is a simple web framework aimed at providing rapid development of web applications using technologies which are familiar to developers developing real-world sites.  Pragmatach was developed to enable rapid development of sites by Javascript and CSS3 developers who need a supporting MVC framework, but don't wish to have to learn a new programming language such as Groovy or Scala, and who wish to have powerful features such built-in JSON support, Cache Control, and annotation driven request routing.

Features in Pragmatach include:

* Minimum client-side requirement; browser programmers should be free of restrictions
* Modular design allowing features such as Freemarker, Velocity, XStream and Gson to be configured via maven dependencie
* Automatic JSON generation via Gson
* Cache control headers specified via Annotations and generated automatically
* Automatic population of controller fields from Form, JSON or XML POST
* Support for Session and Request scoped controllers, via Annotations
* Automatic generation of project skeletons
* Projects built via maven, with Jetty and Tomcat6 and Tomcat7 profiles included
* Java as the core programming languge, rather than Groovy or Scala
* Grails-like routing via annotations
* FreeMarker and Velocity as the templating langauges, rather than GSP, JSP or Scala pages
* Pluggable template processors; Freemarker and Velocity currently, JSP later

Pragmatach does not include ORM support.  Hibernate, iBatis or Ebean can be used if required.

License
------------------------

Pragmatach is licensed under the GPL.

Plugins
------------------------

The Pragmatch framework does not include useful functionality by itself; all functionality is provided via plugins. The current plugins are:

* pragmatach-framework; this is the core Pragmatach code
* pragmatach-freemarker; support for the Freemarker template engine
* pragmatach-velocity; support for the Velocity template engine
* pragmatach-adminapp; the Pragmatach admin console
* pragmatach-gson; support for Google Gson
* pragmatach-json; support for JSON, including posting JSON to controllers
* pragmatach-xtream; XML support via XStream
* pragmatach-jcr; support for the JCR

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

`http://localhost:8080`

Administration Console
------------------------

Pragmatach has a built in web administration console, just browse to the context path

`/pragmatach/admin`

in your application.

On localhost, the link is:

`http://localhost:8080/pragmatach/admin`

In order for your project to use the Admin plugin, it must include the "pragmatach-adminapp" dependency.

Configuration
------------------------

Configuration for the Pragmatach framework is contained in the file

`src\main\resources\pragmatach.configuration`

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

In order to bind parameters, both the @Route annotation and the @RouteParameter must be used.  The @Route annotation is used to specify parameters passed in the URI, and the @RouteParametere is used to map those parameters to Java method parameters.  For example:

<pre><code>
@Route(uri = "/user/@id")
public Response render(@RouteParameter(name="id") String userId) throws PragmatachException
</code></pre>

Regular Expressions can be specified for any parameter using @RouteParameter, for example:

<pre><code>
@Route(uri = "/user/@id")
public Response render(@RouteParameter(name="id", regex="\b\d+\b") int userId) throws PragmatachException
</code></pre>

Controller Scope
------------------------

By default, all Controllers are Request scoped.  However, Controllers can be declared as Session Scoped:

`@Controller(name="myController", scope = Controller.Scope.session)`

Template Variables
------------------------

The following variables are available in the templating engines (Velocity and Freemarker)

* `session` - The HttpSession.
* `controller` - The current Controller
* The name of the current controller as specified in the @Controller annotation
* The names of all Session-scoped controllers as specified in their @Controller annotation

So, to access the session in FreeMarker:

`${session}`

or to access the current Controller in Velocity

`$controller`

To access the Session-scoped controller declared with `@Controller(name="userController", scope = Controller.Scope.session)` in FreeMarker:

`${userController}`

Static Resources
------------------------

In order to differentiate static resources from Pragmatach pages, static resources are served from under the context path `/public`.

Cache Control
------------------------

Cache control headers are specfied via the @CacheControl annotation on controllers.  For example:

<pre><code>
@Controller(name = "IndexController")
@View(view = "index.ftl")
@CacheControl(maxAge = 10)
public class IndexController extends FreemarkerController
</code></pre>

Pragmatach Modules
------------------------

Pragmatach features such as Velocity support, the Admin console and Gson are added to the project by including maven modules. Modules can include both Controllers and resources.

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

Population of Controller fields via HTML Form POST
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

Population of Controller fields via JSON Form POST
------------------------

The JSONController is capable of accepting JSON POSTs and populating atomic controllers fields automatically.  For example posting this:

`{"message":"hello world"}`

To this controller:

<pre><code>
@Controller(name="index")
@View(view = "acceptPost.html")
public class AcceptPostController extends JSONController {
   /**
    * message
    */
   private String message;

   public String getMessage() {
      return message;
   }

   public void setMessage(String message) {
      this.message = message;
   }

   @Route(uri = "/acceptpost", method = HttpMethod.post)
   public Response acceptJSONpost() throws PragmatachException {
         return super.render();
      } catch (final Exception e) {
         throw new PragmatachException("Exception in showFeed", e);
      }
   }
}
</code></pre>

Will result in the method `setMessage()` being called with the value `"hello world"`.

JMX Bindings
------------------------

In order to support runtime inspection of applications, Pragmatach supports JMX bindings using MXBeans.  

JCR Support
------------------------

Support for the JCR is provided via the "pragmatach-jcr" plugin.  This plugin will accept a HTTP GET with an encoded JCR Node path and will return JSON containing the names-value pairs for every JCR Property in that Node.

The below code binds the route /example/@propertyName and returns JSON for the properties of the node path passed as "propertyName".

<pre><code>
@Controller(name = "ExampleJCRController")
public class ExampleJCRController extends JCRController {
   @Route(uri = "example/@propertyName")
   public Response render(@RouteParameter(name = "propertyName") String propertyName) throws PragmatachException {
      return super.render(propertyName);
   }
}
</code></pre>

An example link to this route is.

`<a href="${session.getServletContext().getContextPath()}/example/hello%2Fworld">Example Route Binding 1</a>`

This example link is querying the JCR for the properties of the Node "hello/world"

