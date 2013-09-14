MongoDB
------------------------

To use EBean, include the "pragmatach-mongodb" module in your maven dependencies.

Configuration for MongoDB is provided by these properties in `pragmatach.properties`

* mongodb.Hostname
* mongodb.Database
* mongodb.ConnectionUserName
* mongodb.ConnectionPassword

An example configuration for EBean using manual configuration, in this case using HSQL would look like

<pre>
<code>
mongodb.Hostname=mongoserver.khubla.com
mongodb.Database=mydb
mongodb.ConnectionUserName=
mongodb.ConnectionPassword=
</code>
</pre>

To create a DAO for an object which has been annotated with JPA 1.0 Annotations, simply declare an MongoDB DAO.  For example:

<pre>
<code>
MongoDBDAO&lt;ExamplePOJO&gt; dao = new MongoDBDAO&lt;ExamplePOJO&gt;(ExamplePOJO.class);
</code>
</pre>

The Pragmatach MongoDB DAO strategy for storing objects is to define a MongoDB collection for each entity type.  Lazy loading and cascade saving is supported.


