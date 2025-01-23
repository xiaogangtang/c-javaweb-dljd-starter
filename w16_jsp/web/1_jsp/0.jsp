<%@ page contentType="text/html;charset=UTF-8" %>
hello this is my test<br>
<%
    out.write("测试out.write");
%>
<br>
<!%
    private String name;
%>
<br>
<%=
    "测试%="
%>
<br>



<%
  int a = 1;
  int b = 2;
  int c = a + b;
%>
<%="c的值为：" + c%>
<br>
<%
    out.write("c的值为：" + c);
%>