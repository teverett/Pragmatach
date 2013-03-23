Cache Control
------------------------

Cache control headers are specfied via the @CacheControl annotation on controllers.  For example:

<pre><code>
@Controller(name = "IndexController")
@View(view = "index.ftl")
@CacheControl(maxAge = 10)
public class IndexController extends FreemarkerController
</code></pre>