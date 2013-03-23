Welcome to your new Pragmatach project

*** Running your new project ***

Your generated maven pom supports numerous web containers.  They are:

* jetty 8
* jetty 7
* jboss 7
* tomcat 6
* tomcat 7

** jetty 8 **

This is the default profile.  Type "mvn clean package jetty:run" and browse to http://localhost:8080

** jetty 7 **

Type "mvn -Pjetty7 clean package jetty:run" and browse to http://localhost:8080

** jboss 7 **

Type "mvn -Pjboss7 clean package jboss-as:run" and browse to http://localhost:8080/<application name>

** tomcat 6 **

Type "mvn -Ptomcat6 clean package tomcat:run-war" and browse to http://localhost:8080/<application name>

** tomcat 7 **

Type "mvn -Ptomcat7 clean package tomcat:run-war" and browse to http://localhost:8080/<application name>