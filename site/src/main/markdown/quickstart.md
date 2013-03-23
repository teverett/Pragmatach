
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

