
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<html>
<head>
    <title>登录成功</title>
</head>
<body>
<shiro:authenticated>
    用户[<shiro:principal/>]已身份验证通过
</shiro:authenticated>&nbsp;
</body>
</html>
