<%--
  Created by IntelliJ IDEA.
  User: 22837
  Date: 2021/12/1
  Time: 12:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>四大域对象</title>
</head>
<body>
<h1>scope.jsp页面</h1>
<%
    // 往四个域中都分别保存了数据,pageContext.setAttribute("key", "pageContext");
    //报错，原因为没有加入jar包
    request.setAttribute("key", "request");
    session.setAttribute("key", "session");
    application.setAttribute("key", "application");
%>
request 域是否有值：<%=request.getAttribute("key")%> <br>
session 域是否有值：<%=session.getAttribute("key")%> <br>
application 域是否有值：<%=application.getAttribute("key")%> <br>
</body>
</html>
