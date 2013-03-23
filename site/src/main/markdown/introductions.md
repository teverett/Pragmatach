
<link rel="stylesheet" href="../stylesheets/styles.css"></link>
<link rel="stylesheet" href="../stylesheets/pygment_trac.css"></link>

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
* Built-in support for i8n via plugins

Pragmatach does not include ORM support.  Hibernate, iBatis or Ebean can be used if required.

License
------------------------

Pragmatach is licensed under the GPL.












