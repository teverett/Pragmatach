Controller Scope
------------------------

By default, all Controllers are Request scoped.  However, Controllers can be declared as Session Scoped:

`@Controller(name="myController", scope = Controller.Scope.session)`
