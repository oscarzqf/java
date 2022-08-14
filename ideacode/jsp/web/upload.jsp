<%--
  Created by IntelliJ IDEA.
  User: 22837
  Date: 2021/12/4
  Time: 11:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>文件上传</title>
</head>
<body>
  <form action="uploadServlet" method="post" enctype="multipart/form-data">
    头像：<input type="file" name="photo"><br>
    <input type="submit" value="上传">
  </form>
</body>
</html>
