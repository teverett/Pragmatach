<html xmlns="http://www.w3.org/1999/xhtml" xmlns:th="http://www.thymeleaf.org">
<head>
<title>Pragmatach JSP Example</title>
</head>
<body>
	<h1>JSP Example</h1>
	<img src="<%=session.getServletContext().getContextPath() + "/public/logo.png"%>" />
	${controller.message}
	<h2>Current Time</h2>
	${controller.time}
	<a href="<%=session.getServletContext().getContextPath() + "/pragmatach/admin"%>">Admin</a>
</body>
</html>



