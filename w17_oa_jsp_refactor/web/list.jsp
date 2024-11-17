<%@page  contentType="text/html; UTF-8"%>
<html>
	<head>
		<meta charset="utf-8">
		<title>部门列表页面</title>
		<%--设置整个网页的基础路径是：http://localhost:8080/oa/ --%>
		<%--<base href="http://localhost:8080/oa/">--%>
	</head>
	<body>

<script type="text/javascript">
	function del(dno){
		var ok = window.confirm("亲，删了不可恢复哦！");
		if(ok){
			/*注意html的base标签可能对JS代码不起作用。所以JS代码最好前面写上"/oa" */
			document.location.href = "${pageContext.request.contextPath}/dept/delete?deptno=" + dno;
		}
	}
</script>

		<h1 align="center">部门列表</h1>
		<hr >
		<table border="1px" align="center" width="50%">
			<tr>
				<th>序号</th>
				<th>部门编号</th>
				<th>部门名称</th>
				<th>操作</th>
			</tr>

			<c:forEach items="${deptList}" varStatus="deptStatus" var="dept">
				<tr>
					<td>${deptStatus.count}</td>
					<td>${dept.deptno}</td>
					<td>${dept.dname}</td>
					<td>
						<a href="javascript:void(0)" onclick="del(${dept.deptno})">删除</a>
						<a href="dept/detail?f=edit&dno=${dept.deptno}">修改</a>
						<a href="dept/detail?f=detail&dno=${dept.deptno}">详情</a>
					</td>
				</tr>
			</c:forEach>

		</table>
		
		<hr >
		<a href="add.jsp">新增部门</a>
		
	</body>
</html>
