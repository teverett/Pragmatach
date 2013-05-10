<html xmlns="http://www.w3.org/1999/xhtml" xmlns:th="http://www.thymeleaf.org">
<head>
<title>exampleproject</title>
</head>
<body>
	<h1>exampleproject</h1>
	<img src="<%=session.getServletContext().getContextPath() + "/public/logo.png"%>" />
	<h2>Current Time</h2>
	<a href="<%=session.getServletContext().getContextPath() + "/pragmatach/admin"%>">Admin</a>
</body>
</html>



