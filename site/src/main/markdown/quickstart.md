
Quickstart
------------------------

To create the skeleton of a test project use the pragmatach maven archetype

<pre>
<code>
mvn archetype:generate                                  \
  -DarchetypeGroupId=com.khubla.pragmatach       				\
  -DarchetypeArtifactId=pragmatach-archetype     				\
  -DarchetypeVersion=1.14                        				\
  -DgroupId=com.khubla.exampleproject            				\
  -DartifactId=myexampleproject				  				 				\
  -DinteractiveMode=false                        				\
  -DarchetypeRepository=http://www.pragmatach.com/repo/
  
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

If you are using the Tomcat6, Tomcat7, GlassFish, JBoss-AS7 or TomEE maven profiles, your application will be at:

`http://localhost:8080/myexampleproject-1.0-SNAPSHOT/`


