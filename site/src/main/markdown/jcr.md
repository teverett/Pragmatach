JCR Support
------------------------

Support for the JCR is provided via the "pragmatach-jcr" plugin.  This plugin will accept a HTTP GET with an encoded JCR Node path and will return JSON containing the names-value pairs for every JCR Property in that Node.

The below code binds the route /example/@propertyName and returns JSON for the properties of the node path passed as "propertyName".

<pre><code>
@Controller(name = "ExampleJCRController")
public class ExampleJCRController extends JCRController {
   @Route(uri = "example/@propertyName")
   public Response render(@RouteParameter(name = "propertyName") String propertyName) throws PragmatachException {
      return super.render(propertyName);
   }
}
</code></pre>

An example link to this route is.

`<a href="${session.getServletContext().getContextPath()}/example/hello%2Fworld">Example Route Binding 1</a>`

This example link is querying the JCR for the properties of the Node "hello/world"

