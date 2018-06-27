<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>用户列表</title>
</head>
<body>
    <table align="center" border="1">
        <c:forEach items="${list}" var="user">
            <tr>
                <td>
                    ${user.username}@test.com
                </td>
                <td>
                    ${user.password}
                </td>
                <td>
                    ${user.nickname}
                </td>
                <td>
                    ${user.power}
                </td>
                <td>
                    ${user.disable_flag}
                </td>
                <td>
                    ${user.isfilter}
                </td>
                <td>
                    <a href="/user/form?id=${user.id}">修改</a>
                    <a href="">删除</a>
                </td>
            </tr>
        </c:forEach>
    </table>
</body>
</html>
