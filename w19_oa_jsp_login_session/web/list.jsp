<%@ page import="java.util.List" %>
<%@ page import="com.apexsoft.oa5.bean.Dept" %>
<%@page contentType="text/html; charset=UTF-8" %>
<%--访问jsp时候不生成session对象,jsp也页面也就不能使用内置的session对象了，建议不要配置false--%>
<%--<%@page session="false" %>--%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>部门列表页面</title>
    <%--设置整个网页的基础路径是：http://localhost:8080/oa/ --%>
    <%--<base href="http://localhost:8080/oa/">--%>
</head>
<body>
    <h1><%=session.getAttribute("username")%>，欢迎你！！！</h1>
    <br/>
    <a href="<%=request.getContextPath()%>/user/exit">退出登录</a>
    <script type="text/javascript">
        function del(dno){
            var ok = window.confirm("亲，删了不可恢复哦！");
            if(ok){
                /*注意html的base标签可能对JS代码不起作用。所以JS代码最好前面写上"/oa" */
                document.location.href = "<%=request.getContextPath()%>/dept/delete?deptno=" + dno;
            }
        }
    </script>

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
        List<Dept> deptList3 = (List<Dept>) request.getAttribute("deptList");
        for (Dept dept : deptList3) {
    %>
        <tr>
            <td><%=dept.getDeptno()%></td>
            <td><%=dept.getDname()%></td>
            <td><%=dept.getLoc()%></td>
            <td>
                <a href="javascript:void(0)" onclick="del(<%=dept.getDeptno()%>)">删除 </a>
                <%--修改和详情都是先要从servlet获取数据，再转发到不同的页面，所以可以用同一个，获取数据的逻辑--%>
                <a href="<%=request.getContextPath()%>/dept/detail?flag=edit&dno=<%=dept.getDeptno()%>">修改</a>
                <a href="<%=request.getContextPath()%>/dept/detail?flag=detail&dno=<%=dept.getDeptno()%>">详情</a>
            </td>
        </tr>
    <%
        }
    %>
</table>

<hr>
<a href="<%=request.getContextPath()%>/add.jsp">新增部门</a>
</body>
</html>
