<!DOCTYPE html>
<head>
		<title>Pragmatach</title>
</head>
<body>
<h1>JCR Example</h1>

<!-- the JCR route is "hello/world".  It is passed as URL encoded, but will be decoded by Pragmatach before being passed to the bound route method -->
<a href="${session.getServletContext().getContextPath()}/example/hello%2Fworld">Example Route Binding 1</a>

<a href="${session.getServletContext().getContextPath()}/pragmatach/admin">Admin</a>
</body>



