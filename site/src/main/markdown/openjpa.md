OpenJPA
------------------------

To use OpenJPA, include the "pragmatach-openjpa" module in your maven dependencies.

Configuration for OpenJPA is provided by these properties in `pragmatach.properties`

* openjpa.ConnectionDriverName
* openjpa.ConnectionURL
* openjpa.ConnectionUserName
* openjpa.ConnectionPassword
* openjpa.jdbc.SynchronizeMapping
* openjpa.ConnectionFactoryName

An example manual configuration for OpenJPA, in this case using HSQL would look like

<pre>
<code>
openjpa.ConnectionDriverName=org.hsqldb.jdbcDriver
openjpa.ConnectionURL=jdbc:hsqldb:mem:testdb
openjpa.ConnectionUserName="sa"
openjpa.ConnectionPassword=""
openjpa.jdbc.SynchronizeMapping=buildSchema(ForeignKeys=true)
</code>
</pre>

An example of configuring a DB connection via JNDI is:

<pre>
<code>
openjpa.ConnectionFactoryName=java:comp/env/jdbc/myDB
openjpa.jdbc.SynchronizeMapping=buildSchema(ForeignKeys=true)
</code>
</pre>


To create a DAO for an object which has been annotated with JPA 1.0 Annotations, simply declare an OpenJPA DAO.  For example:

<pre>
<code>
OpenJPADAO&lt;ExamplePOJO, Long&gt; dao = new OpenJPADAO&lt;ExamplePOJO, Long&gt;(ExamplePOJO.class, Long.class);
</code>
</pre>