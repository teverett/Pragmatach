Template Variables
------------------------

The following variables are available in the templating engines (Velocity and Freemarker)

* `session` - The HttpSession.
* `controller` - The current Controller
* `i8n` - The i8n resolver
* The name of the current controller as specified in the @Controller annotation
* The names of all Session-scoped controllers as specified in their @Controller annotation

So, to access the session in FreeMarker:

`${session}`

or to access the current Controller in Velocity

`$controller`

To access the Session-scoped controller declared with `@Controller(name="userController", scope = Controller.Scope.session)` in FreeMarker:

`${userController}`

There is a controller API "url_for" that can be used to build URLs relative to the current URL. For example:

`
<a href="${controller.url_for('pragmatach/admin')}">Admin</a>
`
