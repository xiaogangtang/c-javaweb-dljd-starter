<%@page contentType="text/html; charset=utf-8" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<title>欢迎使用OA系统</title>
	</head>
	<body>
	<%-- 前端发送请求路径的时候，如果请求路径是绝对路径，要以 / 开始，加项目名。--%>
	<%-- 以下这样写代码，oa项目名写死了。这种设计显然是不好的。--%>
	<%--<a href="/oa/list.jsp">查看部门列表</a>--%>

	<%-- 执行一个Servlet，查询数据库，收集数据。--%>
	<a href="<%=request.getContextPath() %>/dept/list">查看部门列表</a>

	<hr>
	<%--调用哪个对象的哪个方法，可以动态的获取一个应用的根路径。--%>
	<%--<%=request.getContextPath() %>--%>   <%--   out.print(request.getContextPath());   --%>
	</body>
</html>
