Hibernate
------------------------

To use Hibernate, include the "pragmatach-hibernate" module in your maven dependencies.

Configuration for Hibernate is provided by these properties in `pragmatach.properties`

* hibernate.driver
* hibernate.dialect
* hibernate.connection.url
* hibernate.connection.username
* hibernate.connection.password
* hibernate.hbm2ddl.auto

An example configuration for Hibernate, in this case using HSQL would look like

<pre>
<code>
hibernate.driver=org.hsqldb.jdbcDriver
hibernate.dialect=org.hibernate.dialect.HSQLDialect
hibernate.connection.url=jdbc:hsqldb:mem:testdb
hibernate.connection.username=sa
hibernate.connection.password=
hibernate.hbm2ddl.auto=create
</code>
</pre>

To create a DAO for an object which has been annotated with JPA 1.0 Annotations, simply declare an Hibernate DAO.  For example:

<pre>
<code>
HibernateDAO&lt;ExamplePOJO, Long&gt; dao = new HibernateDAO&lt;ExamplePOJO, Long&gt;(ExamplePOJO.class, Long.class);
</code>
</pre>