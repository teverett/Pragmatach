Controller Scope
------------------------

By default, all Controllers are Request scoped.  However, Controllers can be declared as Session Scoped:

`@Controller(name="myController", scope = Controller.Scope.session)`

Both the name and scope parameters are optional on @Controller.  The default value for `Name` is the class name of the annotated controller and the default value for `scope` is `Controller.Scope.request`

Controllers classes extends a template engine controller such as `FreemarkerController`, `VelocityController` or `ThymeleafController` can specify the view template to use with the`@View` annotatation.

For example a controller that renders the template `page.html` would use this `@View` annotation:

`@View(view="page.html")`

The file system path for view template is

`/src/main/webapp`

The @View annoation is valid on both classes and members.  It is therefore possible to have multiple methods that render different templates, from the same Controller class.
 