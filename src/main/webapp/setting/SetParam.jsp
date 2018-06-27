<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<style type="text/css">
body {
	margin-left: 0px;
	margin-top: 0px;
	margin-right: 0px;
	margin-bottom: 0px;
	background-color: #EEF2FB;
}
.divForm1{
position: absolute;/*绝对定位*/
text-align: center;/*(让div中的内容居中)*/
top: 20%;
left: 33%;

}
.divForm2{
position: absolute;/*绝对定位*/
text-align: center;/*(让div中的内容居中)*/
top: 30%;
left: 33%;

}
.divForm3{
position: absolute;/*绝对定位*/
text-align: center;/*(让div中的内容居中)*/
top: 40%;
left: 33%;

}
</style>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<script type="text/javascript">  
	function chkNumber(eleText) {   
		var value = eleText.value;  
		var len = value.length;  
		for(var i = 0;i < len;i++) {  
			if(value.charAt(i) > "9" || value.charAt(i) < "0") {  
				alert("含有非数字字符");
				break;   
			}  
		}  
	}
	function chkChinese(eleText){
		var value = eleText.value;  
		var len = value.length;  
		for(var i = 0;i < len;i++) {  
			if((value.charAt(i) <= "9" && value.charAt(i) >= "0") 
					|| (value.charAt(i) <= "Z" && value.charAt(i) >= "A") 
					|| (value.charAt(i) <= "z" && value.charAt(i) >= "a")
					|| (value.charAt(i) == ".")
			) 
			{
				continue;
			}
			else{
				alert("含有非法字符");
				break;
			}
		}   
	}
	
</script>
<title>设置系统参数</title>
</head>
<c:if test="${flag_smtp!=null}">
	<c:if test="${flag_smtp==0}">
		<script>
		setTimeout(function(){alert("请先关闭SMTP服务！")},100);
		</script>
	</c:if>
	<c:if test="${flag_smtp==1}">
		<script>
		setTimeout(function(){alert("修改SMTP端口号成功!")},100);
		</script>
	</c:if>
</c:if>

<c:if test="${flag_pop3!=null}">
	<c:if test="${flag_pop3==0}">
		<script>
		setTimeout(function(){alert("请先关闭POP3服务!")},100);
		</script>
	</c:if>
	<c:if test="${flag_pop3==1}">
		<script>
		setTimeout(function(){alert("修改POP3端口号成功!")},100);
		</script>
	</c:if>
</c:if>

<c:if test="${flag!=null}">
	<c:if test="${flag==0}">
		<script>
			setTimeout(function(){alert("请先关闭SMTP和POP3服务!")},100);
		</script>
	</c:if>
	<c:if test="${flag==1}">
		<script>
		setTimeout(function(){alert("修改邮件域名成功!")},100);
		</script>
	</c:if>
</c:if>


<body>

    <div class="divForm1">
    <form method="post" action="${pageContext.request.contextPath}/setting/SMTPport">
            <label>SMTP端口号：</label>
            <input type="text" name="port_smtp" value="${smtpPort}" onblur="chkNumber(this)"/>
            <input type="submit" value="设置"/>
    </form>
    </div>
    <div class="divForm2">
    <form method="post" action="${pageContext.request.contextPath}/setting/POP3port">
            <label>POP3端口号：</label>
            <input type="text" name="port_pop3" value="${pop3Port}" onblur="chkNumber(this)"/>
            <input type="submit" value="设置"/>
    </form>
    </div>
    <div class="divForm3">
    <form method="post" action="${pageContext.request.contextPath}/setting/DomainName">
            <label>Email域名：</label>
            <input type="text" name="name_domain" value="${domainName}" onblur="chkChinese(this)"/>
            <input type="submit" value="设置"/>
    </form>
    </div>
</body>
</html>