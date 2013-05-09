<!DOCTYPE html SYSTEM "http://www.thymeleaf.org/dtd/xhtml1-strict-thymeleaf-3.dtd">
<html xmlns="http://www.w3.org/1999/xhtml"
	xmlns:th="http://www.thymeleaf.org">
<head>
<title>exampleproject</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
</head>
<body>
	<h1>exampleproject</h1>
	<img
		th:src="<%=session.getServletContext().getContextPath() + "/public/logo.png"%>" />
	<p th:text="<%=controller.message%>" />

	<h2>Current Time</h2>
	<p th:text="<%=controller.time%>" />

	<a
		th:href="<%=session.getServletContext().getContextPath() + "/pragmatach/admin"%>">Admin</a>
</body>
</html>



