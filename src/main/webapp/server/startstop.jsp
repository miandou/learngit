<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8" %>
<link href="images/skin.css" rel="stylesheet" type="text/css" />
<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
<style type="text/css">
<!--
body {
	margin-left: 0px;
	margin-top: 0px;
	margin-right: 0px;
	margin-bottom: 0px;
	background-color: #EEF2FB;
}
-->
.STYLE1 {
	color: #e1e2e3;
	font-size: 12px;
}
.STYLE6 {color: #000000; font-size: 12; }
.STYLE10 {color: #000000; font-size: 12px; }
.STYLE19 {
	color: #344b50;
	font-size: 12px;
}
.STYLE21 {
	font-size: 12px;
	color: #3b6375;
}
.STYLE22 {
	font-size: 12px;
	color: #295568;
}
</style>
<body>
<table width="60%" border="0" cellpadding="0" cellspacing="1" bgcolor="#a8c7ce" style="position:relative;left:20%;top:100px">
      <tr>
       
        <td width="20%" height="50px" bgcolor="d3eaef" class="STYLE6"><div align="center"><span class="STYLE10">类型</span></div></td>
        <td width="20%" height="50px" bgcolor="d3eaef" class="STYLE6"><div align="center"><span class="STYLE10">状态</span></div></td>
        <td width="20%" height="50px" bgcolor="d3eaef" class="STYLE6"><div align="center"><span class="STYLE10">基本操作</span></div></td>
      </tr>
      
       <tr>
         <td height="30px" bgcolor="#FFFFFF" class="STYLE6"><div align="center"><span class="STYLE19">服务器</span></div></td>
         <td height="30px" bgcolor="#FFFFFF" class="STYLE6"><div align="center"><span class="STYLE19">服务器</span></div></td>
         <td height="30px" bgcolor="#FFFFFF" class="STYLE6"><div align="center"><span class="STYLE19">服务器</span></div></td>
	   </tr>
	   <%
	   String smtpstate="";
	   if(request.getAttribute("smtpopen")!=null){ 
	   		smtpstate="open";
	   }
	   if(request.getAttribute("smtpclose")!=null){ 
	   		smtpstate="close";
	   }
	   %>
	   <tr>
         <td height="30px" bgcolor="#FFFFFF" class="STYLE6"><div align="center"><span class="STYLE19">SMTP</span></div></td>
         <td height="30px" bgcolor="#FFFFFF" class="STYLE6"><div align="center"><span class="STYLE19"><%=smtpstate %></span></div></td>
         <%if(smtpstate.equals("open")){ %>
         <td height="30px" bgcolor="#FFFFFF" class="STYLE6"><div align="center"><span class="STYLE19"><a href="${pageContext.request.contextPath}/server/SMTPClose">close</a></span></div></td>
	   <%} %>
	   <%if(smtpstate.equals("close")){ %>
         <td height="30px" bgcolor="#FFFFFF" class="STYLE6"><div align="center"><span class="STYLE19"><a href="${pageContext.request.contextPath}/server/SMTPOpen">open</a></span></div></td>
	   <%} %>
	   </tr>
	   <%
	   String pop3state="";
	   if(request.getAttribute("pop3open")!=null){ 
	   		pop3state="open";
	   }
	   if(request.getAttribute("pop3close")!=null){ 
	   		pop3state="close";
	   }
	   %>
	   <tr>
         <td height="30px" bgcolor="#FFFFFF" class="STYLE6"><div align="center"><span class="STYLE19">POP3</span></div></td>
         <td height="30px" bgcolor="#FFFFFF" class="STYLE6"><div align="center"><span class="STYLE19"><%=pop3state %></span></div></td>
        <%if(pop3state.equals("open")){ %>
         <td height="30px" bgcolor="#FFFFFF" class="STYLE6"><div align="center"><span class="STYLE19"><a href="${pageContext.request.contextPath}/server/POP3Close">close</a></span></div></td>
	   <%} %>
	   <%if(pop3state.equals("close")){ %>
         <td height="30px" bgcolor="#FFFFFF" class="STYLE6"><div align="center"><span class="STYLE19"><a href="${pageContext.request.contextPath}/server/POP3Open">open</a></span></div></td>
	   <%} %>
	   </tr>
</table>
</body>