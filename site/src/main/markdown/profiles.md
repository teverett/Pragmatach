Application Server Support
------------------------

The pom.xml for Pragmatach applications includes a number of maven profiles for different application servers.  These include:

* Jetty 8 (the default profile)
* Jetty 9
* Jetty 7
* Tomcat 6
* Tomcat 7
* JBoss-AS 7
* TomEE
* GlassFish

### Jetty 8

`mvn clean package jetty:run` and browse to `http://localhost:8080`

or

`mvn -Pjetty8 clean package jetty:run` and browse `to http://localhost:8080`

### Jetty 9

`mvn -Pjetty97clean package jetty:run` and browse to `http://localhost:8080`

Note that Jetty9 requires JDK7

### Jetty 7

`mvn -Pjetty7 clean package jetty:run` and browse to `http://localhost:8080`

### Tomcat 6

`mvn -Ptomcat6 clean package tomcat:run-war` and browse to `http://localhost:8080/<application name>`

### Tomcat 7

`mvn -Ptomcat7 clean package tomcat:run-war` and browse to `http://localhost:8080/<application name>`

### JBoss 7

`mvn -Pjbossas7 clean package jboss-as:run` and browse to `http://localhost:8080/<application name>`

### TomEE

`mvn -Ptomee clean package tomee:run` and browse to `http://localhost:8080/<application name>`

### GlassFish

`mvn -Pglassfish embedded-glassfish:run` and browse to `http://localhost:8080/<application name>`

