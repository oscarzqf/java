<%--
  Created by IntelliJ IDEA.
  User: 22837
  Date: 2021/12/4
  Time: 10:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%--
<c:set />
        作用：set 标签可以往域中保存数据
        域对象.setAttribute(key,value);
        scope 属性设置保存到哪个域
            page 表示 PageContext 域（默认值）
            request 表示 Request 域
            session 表示 Session 域
            application 表示 ServletContext 域
        var 属性设置 key 是多少
        value 属性设置值
--%>
    保存之前：${ sessionScope.abc } <br>
    <c:set scope="session" var="abc" value="abcValue"/>
    保存之后：${ sessionScope.abc } <br>
</body>
</html>
