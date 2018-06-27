
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>添加邮件</title>
</head>
<body>
<form method="post" action="${pageContext.request.contextPath}/email/save">
    <div>
        <label>user_id：</label>
        <input type="text" name="user_id"/>
    </div>
    <div>
        <label>title:</label>
        <input type="text" name="title"/>
    </div>
    <div>
        <label>send_by:</label>
        <input type="text" name="send_by"/>
    </div>
    <div>
        <label>read_flag:</label>
        <input type="radio" name="read_flag" value="0"/>n&nbsp;
        <input type="radio" name="read_flag" value="1"/>y&nbsp;
    </div>
    <div>
        <label>content:</label>
        <input type="text" name="content"/>
    </div>
    <div>
        <input type="submit" value="添加邮件"/>
    </div>
    <div>
        <a href="${pageContext.request.contextPath}/index.jsp">返回</a>
    </div>
</form>
</body>
</html>
