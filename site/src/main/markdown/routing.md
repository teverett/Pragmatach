Request Routing
------------------------

Pragmatch uses REST-like route mapping. Request routes are declared via the @Route annotation, in conjunction with the signature of the annotated method

###Examples

In this example, the method "render()" will be called for the context path "/".

<pre><code>
@Route(uri = "/")
public Response render() throws PragmatachException
</code></pre>

In this example the method "myMethod(int)" will be called with the value "2" for the context path "/mycontroller/2".

<pre><code>
@Route(uri = "/mycontroller")
public Response render(int myValue) throws PragmatachException
</code></pre>

In order to bind parameters, both the @Route annotation and the @RouteParameter must be used.  The @Route annotation is used to specify parameters passed in the URI, and the @RouteParametere is used to map those parameters to Java method parameters.  For example:

<pre><code>
@Route(uri = "/user/@id")
public Response render(@RouteParameter(name="id") String userId) throws PragmatachException
</code></pre>

Regular Expressions can be specified for any parameter using @RouteParameter, for example:

<pre><code>
@Route(uri = "/user/@id")
public Response render(@RouteParameter(name="id", regex="\b\d+\b") int userId) throws PragmatachException
</code></pre>

Wildcard routes

<pre><code>
@Route(uri = "/user/*")
public Response render(String[] parameters) throws PragmatachException
</code></pre>

### Route Cache

The process of mapping arbitrary incoming request URLs to routes is computationally expensive.  Therefore, Pragmatach maintains an internal LRU cache of route mapping results.  The route cache statics are in the Admin Console and additionally the route cache can be cleared via the Admin Console.  The default maximum size of the cache is 100 routes, however that maximum can be specified in the pragmatach configuration using this flag

`pragmatach.routecache.size`


