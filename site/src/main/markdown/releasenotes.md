Release Notes
------------------------

### V19

* Added OpenJPA Support

### V18

* EBean and Hibernate support is complete

### V17

* @View is a valid property on both methods and classes; controllers can therefore now bind multiple views
* @Controller no longer requires the "name" property; it will use the Controller name by default
* Public resource are now on the file path /src/main/webapp/public.  This prevents black hats from viewing templates stored at /src/main/public/
* Added support for numerous additional containers, including jetty8, jetty7, tomcat7, tomcat6, tomee, glassfish, and jboss7
* Refactoring to allow for easier implementation of Bean bound controllers
* Added ok() and bad() APIs to AbstractController to ease in building simple controllers
* Added EBean support via pragmatach-ebean plugin
* Updated web.xml to specify that Servlet 2.4 is required

### V13

* Updated documentation
* Added a web-based slideshow to introduce Pragmatach
* Added a Pragmatach plugin for Facebook Authentication
* Added a Pragmatach plugin for Google Authentication
* Added optional support for HTTP Basic Auth
* Significant refactoring of the codebase to support more complex plugins
* Added pom.xml profiles for additional containers, including Jetty7,Jetty8, Tomcat6, Tomcat7, and JBoss-AS 7
* Added YAML support
* Configuration is now a static member of the Application object.

