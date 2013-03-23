Request Routing
------------------------

Pragmatch uses REST-like route mapping. Request routes are declared via the @Route annotation, in conjunction with the signature of the annotated method

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
