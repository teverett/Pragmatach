Plugins
------------------------

The Pragmatch framework does not include useful functionality by itself; all functionality is provided via plugins. The current plugins are:

* pragmatach-framework; this is the core Pragmatach code
* pragmatach-freemarker; support for the [Freemarker](http://freemarker.sourceforge.net/) template engine
* pragmatach-thymeleaf; support for the [Thymeleaf](http://www.thymeleaf.org/) template engine
* pragmatach-velocity; support for the [Velocity](http://velocity.apache.org/) template engine
* pragmatach-adminapp; the Pragmatach admin console
* pragmatach-json; support for [JSON](http://en.wikipedia.org/wiki/JSON), including GET-POST to controllers
* pragmatach-xtream; XML support for GET-POST via [XStream](http://xstream.codehaus.org/)
* pragmatach-jcr; support for the [JCR](http://en.wikipedia.org/wiki/Content_repository_API_for_Java)
* pragmatach-responsive; support for responsive design
* pragmatach-i8n; simple file-based i8n provider
* pragmatach-facebook; a controller which implements Facebook login
* pragmatach-google; a controller which implements Google login
* pragmatach-yaml; a controller which implements [YAML](http://en.wikipedia.org/wiki/YAML) GET-POST
* pragmatach-hibernate; support for JPA 1.0 via Hibernate
* pragmatach-ebean; support for JPA 1.0 via [EBean](http://www.avaje.org/)
* pragmatach-openjpa; support for JPA 1.0 via [OpenJPA](http://openjpa.apache.org/)
* pragmatach-statsd; support for [statsd](https://github.com/etsy/statsd/)

To use a plugin in your project, simply include the plugin module in your project as a maven depenendency. For example, to include support for GETing and POSTing JSON:

<pre>
<code>
&lt;dependency&gt;
	&lt;groupId&gt;com.khubla.pragmatach&lt;/groupId&gt;
	&lt;artifactId&gt;pragmatach-json&lt;/artifactId&gt;
	&lt;version&gt;1.20-RELEASE&lt;/version&gt;
	&lt;type&gt;jar&lt;/type&gt;
	&lt;scope&gt;compile&lt;/scope&gt;
&lt;/dependency&gt;
</code>
</pre>

The plugin will automatically be detected at runtime and will be availalbe to your applications
