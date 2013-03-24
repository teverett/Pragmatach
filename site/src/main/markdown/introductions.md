
<link rel="stylesheet" href="../stylesheets/styles.css"></link>
<link rel="stylesheet" href="../stylesheets/pygment_trac.css"></link>

Pragmatach - A pragmatic MVC framework for Web Developers
==========

Pragmatach is a simple web framework aimed at providing server-site support for B2C web developers using technologies which are familiar to developers developing real-world sites.

Features in Pragmatach include:

* Minimum client-side requirement; browser programmers should be free of restrictions
* Modular design allowing features such as Freemarker, Velocity, XStream and Gson to be configured via maven dependencies
* Automatic JSON generation via Gson
* Cache control headers specified via Annotations and generated automatically
* Automatic population of controller fields from Form, JSON, XML or YAML POST
* Support for Session and Request scoped controllers, via Annotations
* Automatic generation of project skeletons via Maven Archetype
* Projects built via Maven, with Jetty8, Jetty7, JBoss-AS 7, GlassFish, TomEE, Tomcat6 and Tomcat7 profiles included
* Grails-like routing via annotations
* Pluggable template processors; Freemarker, Tymeleaf and Velocity
* Built-in support for i8n via plugins
* Built-in support for monitoring and runtime debugging via JMX

Pragmatach does not include ORM support.  Hibernate, iBatis or Ebean can be used if required.

License
------------------------

Pragmatach is licensed under the GPL.
