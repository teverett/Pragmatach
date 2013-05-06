#Pragmatach Proposal


##Abstract

Pragmatch is a web development framework focussed on simplicity and modularity

##Proposal

Pragmatach can be used as a frameowork quickly developing web-based applications.  The framework is designed to be used to implement simple back-end support for world facing sites that are primarily developed using client-side CSS and JS code.

###Pragmatach provides:

* Minimum client-side requirements; browser programmers should be free of restrictions
* Automatic generation of project skeletons via Maven Archetype
* Automatic population of controller fields from Form, JSON, XML or YAML POST
* Grails-like routing via Java annotations
* Built-in support for monitoring and runtime debugging via a secure web-based console, and JMX
* Pluggable template processors; Freemarker, Thymeleaf and Velocity
* Support for Session and Request scoped controllers, via Annotations
* Projects built via Maven, with Jetty8, Jetty7, JBoss-AS 7, GlassFish, TomEE, Tomcat6 and Tomcat7 profiles included
* Built-in support for i8n
* Modular design allowing features such as Freemarker, Velocity, XStream and Gson to be configured via maven dependencies
* Cache control headers specified via Annotations and generated automatically
* Support for JPA 1.0 relational persistence via EBean, OpenJPA or Hibernate

##Background

Web frameworks such as Struts2, JSF, Grails and Play have been very successful.  Pragmatach, however is not intended to be a general purpose web framework.  Pragmatach is intended to provide back-end support for consumer facing sites.  These sites have a number of characteristics which make them unique including:

* The need for simple back-end support that can be outsourced for maintainence or maintained by junior staff
* The need to operate and monitor the site in real time
* The need to support a JS-CSS UI using technologies which are familiar to front-end programmers such as RESTful URLs and JSON
* The need to support a broad variety of development and deployment technologies, in light of the fact that sites are often deployed into existing environments with existing technology standards

Therefore, these design criteria were set:

* The framework must make it possible to set up a simple site in a matter of minutes, via an archetype
* The framework must use standard technologies which are accessible by a large community of programmers, such as MVC architecture, and Maven
* The framework must not provide, or depend upon any specific front-end technologies
* The framework must be able to produce a war file which can run in any arbitrary J2EE container
* The framework must be able to support a variety of templating engines and ORM frameworks
* The framework must support operation on real-world sites via a built-in operations console and built-in support for monitoring.
* The framework must support simple GET and POST operations, using REST-like URLs and with automatic population of controllers via JSON and XML

##Initial Goals

The initial goals for Pragmatach are:

* Donate the initial code base
* Enter the Apache process, via the Incubator
* Set up the incubator for Pragmatach, including automated builds, svn, and a web site. 
* Set up continuous integration
* Focus on a version 1 release and bulding a community.

##Current Status

The current status is that Pragmatach is quite new, and has not yet formed a community.  The source code 

##Known Risks

There is a clear risk that Pragmatach will be unable to build a community. However, Pragmatach may be able to build a community based on serving a niche need.  There is a clear need for simple, maintainable server-side support for complex client-side site implementations.  While existing frameworks, such as Struts2, can be used to provide that support, Pragmatach provides a simpler, easier to implement solution.  Pragmatach is certainly less capable than broader frameworks, but has the advantage of being simple to set up and capable enough to support its audience.

##Documentation

The current documentation is at:

http://pragmatach.com/pragmatach-documentation/pragmatach-documentation.xhtml

There is also an introductory presentation at:

http://pragmatach.com/slideshow/index.html

##Initial Source

The source tree is currently at https://github.com/teverett/Pragmatach

##Source and Intellectual Property Submission Plan

The Pragmatach code base is owned by Tom Everett.  There is a legal excemption in place with Tom's employer which allows him to contribute to the code base and to open-source the code base.  The Pragmatach code can therefore be donated to the Apache Foundation.

##External Dependencies

Pragmatach is divided into a framework, and optional plugins.  The plugins are generally focussed on support for specific technologies such as ORM framworks or templating engines. The dependencies for the core framework are:

###Pragmatach-Framework

* commons-lang
* commons-beanutils
* commons-io
* commons-fileupload
* commons-codec
* scannotation (http://scannotation.sourceforge.net/). Apache 2.0 License.
* antlr (http://www.antlr.org/). BSD License.
* cglib (http://cglib.sourceforge.net/) Apache License.
* logback (http://logback.qos.ch/). EPL 1.0, LGPL 2.1.

##Required Resources

Currently unknown

##Initial Committers

Tom Everett

##Sponsors

No Sponsors have yet been identified

##Champion:

No Champion has yet been identified