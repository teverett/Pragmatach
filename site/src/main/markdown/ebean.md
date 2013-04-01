EBean
------------------------

To use EBean, include the "pragmatach-EBean" module in your maven dependencies.

Configuration for EBean is provided by these properties in `pragmatach.properties`

* ebean.driver
* ebean.username
* ebean.password
* ebean.url
* ebean.autocreate
* ebean.datasource

An example configuration for EBean using manual configuration, in this case using HSQL would look like

<pre>
<code>
ebean.driver=org.hsqldb.jdbcDriver
ebean.username=sa
ebean.password=
ebean.url=jdbc:hsqldb:mem:testdb
ebean.autocreate=true
</code>
</pre>

A similar configuration, this time using JNDI could be

<pre>
<code>
ebean.datasource=java:/comp/env/jdbc/MyDB
ebean.autocreate=true
</code>
</pre>

To create a DAO for an object which has been annotated with JPA 1.0 Annotations, simply declare an EBean DAO.  For example:

<pre>
<code>
EBeanDAO&lt;ExamplePOJO, Long&gt; dao = new EBeanDAO&lt;ExamplePOJO, Long&gt;(ExamplePOJO.class, Long.class);
</code>
</pre>