<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>

<link href="images/skin.css" rel="stylesheet" type="text/css" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" /><style type="text/css">
<!--
body {
	margin-left: 0px;
	margin-top: 0px;
	margin-right: 0px;
	margin-bottom: 0px;
	background-color: #EEF2FB;
}
-->
</style>

<script type="text/javascript">
 
      function checkRegister(){
          var name=document.getElementById("title");
          if(name.value=="")
          {
              alert("请输入标题！");
              name.focus();
              return  false;
          }
 
          var password=document.getElementById("content");
          if(password.value=="")
          {
          
              alert("请输入内容！");
              password.focus();
              return  false;
          }
                  return  true;
      }
      
</script>

<body>
<table width="100%" border="0" cellpadding="0" cellspacing="0">
  <tr>
    <td width="17" valign="top" background="images/mail_leftbg.gif"><img src="images/left-top-right.gif" width="17" height="29" /></td>
    <td valign="top" background="images/content-bg.gif"><table width="100%" height="31" border="0" cellpadding="0" cellspacing="0" class="left_topbg" id="table2">
      <tr>
        <td height="31"><div class="titlebt">群发邮件</div></td>
      </tr>
    </table></td>
    <td width="16" valign="top" background="images/mail_rightbg.gif"><img src="images/nav-right-bg.gif" width="16" height="29" /></td>
  </tr>
  
</table>
<table>
	<tr>
		<form action="${pageContext.request.contextPath}/server/sendemail" onSubmit="return checkRegister()" method="post">
		<td style="position:absolute;top:60px;left:15%">
		<b>标题:</b> <input type="text" name="title" id="title" style="width:500px"><br>
		</td>
		<td style="position:absolute;top:95px;left:15%">
		<b>内容:</b><br/> </td>
		<td style="position:absolute;top:95px;left:19%"><textarea name="content" id="content" style="width:700px;height:300px" rows="100" cols="20"></textarea><br></td>
		<td style="position:absolute;top:410px;left:50%"><input type="submit" value="发送"></td>
		</form>
	</tr>
</table>
<%
if(request.getAttribute("sendsuccessfully")!=null){
%>
<script type="text/javascript">
alert("发送成功!");
</script>
<%	
}
%>

</body>
