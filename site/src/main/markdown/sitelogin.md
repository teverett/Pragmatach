Site Login
------------------------

Pragmatach includes the ability to require HTTP Basic Auth login to sites.  This can be useful for sites that are deployed on internet facing servers, but are not yet in production.

To enable this feature simply put this into pragmatach.properties

<pre><code>
pragmatach.applicationuser=user
pragmatach.applicationpassword=password
pragmatach.applicationrealm=MySite
</code></pre>