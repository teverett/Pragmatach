<a id="releasenotes"></a>
Release Notes
------------------------

### V24

* Fixed a fairly serious bug in pragmatach-openjpa and pragmatach-ebean which was causing the DAO's to reload the configuration over and over
* Fixed the admin app to always show controller names
* Added the namespace com.khubla.pragmatach.framework.test with the class AbstractDAOTest to support simple CRUD testing of DAOs.

### V23

* Fixed route sorting bug

### V21

* Added streaming controller to support building controllers from simple streams

### V20

* Combined pragmatach-json and pragmatach-gson into a single plugin; pragmatach-json
* Added support for collections to pragmatach-json

### V19

* Added OpenJPA Support
* Application build date appears in the console
* Minor updates to the Archetype
* Route cache size can be configured via `pragmatach.routecache.size`
* Switched from log4j to LogBack for logging
* Expanded testing for OpenJPA, Hibernate and EBean to include embedded MySQL, H2, HSQL, and Derby
* Added JNDI data source support to OpenJPA, EBean and Hibernate
* Added Paging to the DAO's
* Added support for [statsd](https://github.com/etsy/statsd/)

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

