<%@ page import="java.util.List" %>
<%@ page import="com.apexsoft.oa3.bean.Dept" %>
<%@page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>部门列表页面</title>
    <%--设置整个网页的基础路径是：http://localhost:8080/oa/ --%>
    <%--<base href="http://localhost:8080/oa/">--%>
</head>
<body>

<h1 align="center">部门列表</h1>
<hr>
<table border="1px" align="center" width="50%">
    <tr>
        <th>序号</th>
        <th>部门编号</th>
        <th>部门名称</th>
        <th>操作</th>
    </tr>

    <%
        String contextPath = request.getContextPath();
        out.print(contextPath);
    %>
<%--
    <%
        List<Dept> deptList = (List<Dept>) request.getAttribute("deptList");
        for (Dept dept : deptList) {
            out.write("<tr>");
            out.write("<td>" + dept.getDeptno() + "</td>");
            out.write("<td>" + dept.getDname() + "</td>");
            out.write("<td>" + dept.getLoc() + "</td>");
            out.write("<td>");
            out.write("<a href=" + contextPath + "/edit.jsp>修改 </a>");
            out.write("<a href=" + contextPath + "/add.jsp>删除 </a>");
            out.write("<a href=" + contextPath + "/add.jsp>详情</a>");
            out.write("</td>");
            out.write("</tr>");
        }
    %>
--%>


<%--
    <%
        List<Dept> deptList2 = (List<Dept>) request.getAttribute("deptList");
        for (Dept dept : deptList2) {
    %>
    <%="<tr>"%>
    <%="<td>" + dept.getDeptno() + "</td>"%>
    <%="<td>" + dept.getDname() + "</td>"%>
    <%="<td>" + dept.getLoc() + "</td>"%>
    <%="<td>"%>
    <%="<a href=" + contextPath + "/edit.jsp>修改 </a>"%>
    <%="<a href=" + contextPath + "/delete.jsp>删除 </a>"%>
    <%="<a href=" + contextPath + "/detail.jsp>详情</a>"%>
    <%="</td>"%>
    <%="</tr>"%>
    <%
        }
    %>
--%>


    <%
        List<Dept> deptList3 = (List<Dept>) request.getAttribute("deptList");
        for (Dept dept : deptList3) {
    %>
        <tr>
            <td><%=dept.getDeptno()%></td>
            <td><%=dept.getDname()%></td>
            <td><%=dept.getLoc()%></td>
            <td>
                <a href="<%=contextPath%>/edit.jsp">修改 </a>
                <a href="<%=contextPath%>/delete.jsp">删除 </a>
                <a href="<%=contextPath%>/detail.jsp">详情</a>
            </td>
        </tr>
    <%
        }
    %>
</table>

<hr>
<a href="add.jsp">新增部门</a>
</body>
</html>
