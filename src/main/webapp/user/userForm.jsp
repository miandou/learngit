<%@ page contentType="text/html;charset=utf-8" pageEncoding="utf-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>编辑用户</title>
</head>
<body>
<form method="post" action="${pageContext.request.contextPath}/user/update">
    <input type="hidden" name="id" value="${user.id}">
    <div>
        <label>邮箱名：</label>
        <input type="text" name="username" value="${user.username}"/>
        <label>@test.com</label>
    </div>
    <div>
        <label>密码:</label>
        <input type="password" name="password" value="${user.password}"/>
    </div>
    <div>
        <label>昵称:</label>
        <input type="text" name="nickname" value="${user.nickname}"/>
    </div>
    <div>
        <label>用户类型:</label>
        <input type="radio" name="power" value="0"/>普通用户&nbsp;
        <input type="radio" name="power" value="1"/>管理员&nbsp;
    </div>
    <div>
        <label>是否禁用:</label>
        <input type="radio" name="disable_flag" value="0"/>否&nbsp;
        <input type="radio" name="disable_flag" value="1"/>是&nbsp;
    </div>
    <div>
        <label>是否过滤:</label>
        <input type="radio" name="isfilter" value="0"/>否&nbsp;
        <input type="radio" name="isfilter" value="1"/>是&nbsp;
    </div>
    <div>
        <input type="submit" value="修改用户"/>
    </div>
    <div>
        <a href="${pageContext.request.contextPath}/index.jsp">返回</a>
    </div>
</form>
</body>
</html>