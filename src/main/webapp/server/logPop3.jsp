<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8" import="email.bean.*,java.util.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>无标题文档</title>
<style type="text/css">
<!--
body {
	margin-left: 3px;
	margin-top: 0px;
	margin-right: 3px;
	margin-bottom: 0px;
}
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
-->
</style>
<script>
var  highlightcolor='#d5f4fe';
//此处clickcolor只能用win系统颜色代码才能成功,如果用#xxxxxx的代码就不行,还没搞清楚为什么:(
var  clickcolor='#51b2f6';
function  changeto(){
source=event.srcElement;
if  (source.tagName=="TR"||source.tagName=="TABLE")
return;
while(source.tagName!="TD")
source=source.parentElement;
source=source.parentElement;
cs  =  source.children;
//alert(cs.length);
if  (cs[1].style.backgroundColor!=highlightcolor&&source.id!="nc"&&cs[1].style.backgroundColor!=clickcolor)
for(i=0;i<cs.length;i++){
	cs[i].style.backgroundColor=highlightcolor;
}
}

function  changeback(){
if  (event.fromElement.contains(event.toElement)||source.contains(event.toElement)||source.id=="nc")
return
if  (event.toElement!=source&&cs[1].style.backgroundColor!=clickcolor)
//source.style.backgroundColor=originalcolor
for(i=0;i<cs.length;i++){
	cs[i].style.backgroundColor="";
}
}

function  clickto(){
source=event.srcElement;
if  (source.tagName=="TR"||source.tagName=="TABLE")
return;
while(source.tagName!="TD")
source=source.parentElement;
source=source.parentElement;
cs  =  source.children;
//alert(cs.length);
if  (cs[1].style.backgroundColor!=clickcolor&&source.id!="nc")
for(i=0;i<cs.length;i++){
	cs[i].style.backgroundColor=clickcolor;
}
else
for(i=0;i<cs.length;i++){
	cs[i].style.backgroundColor="";
}
}
</script>
<script type="text/javascript" language="javascript"> 
function confirmAct() 
{ 
  if(confirm('确定要执行此操作吗?')) 
  { 
    return true; 
  } 
  return false; 
} 
</script> 


</head>
<body>
<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
  <tr>
    <td height="30"><table width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td height="24" bgcolor="#353c44"><table width="100%" border="0" cellspacing="0" cellpadding="0">
          <tr>
            <td><table width="100%" border="0" cellspacing="0" cellpadding="0">
              <tr>
                <td width="6%" height="19" valign="bottom"><div align="center"><img src="${pageContext.request.contextPath}/user/images/tb.gif" width="14" height="14" /></div></td>
                <td width="94%" valign="bottom"><span class="STYLE1"> 管理日志信息列表</span></td>
              </tr>
            </table></td>
            <td><div align="right"><span class="STYLE1">
              
			  <a href="${pageContext.request.contextPath}/Log/deleteAllPop3" onclick="return confirmAct();" target="rightFrame"><img src="${pageContext.request.contextPath}/user/images/add.gif" width="10" height="10" /><span style="color:white"> 删除全部</span>   &nbsp;</a> </span><span class="STYLE1"> &nbsp;</span></div></td>
          </tr>
        </table></td>
      </tr>
    </table></td>
  </tr>
  <tr>
    <td><table width="100%" border="0" cellpadding="0" cellspacing="1" bgcolor="#a8c7ce" onmouseover="changeto()"  onmouseout="changeback()">
      <tr>
       
        <td width="17%" height="20" bgcolor="d3eaef" class="STYLE6"><div align="center"><span class="STYLE10">用户名</span></div></td>
        <td width="18%" height="20" bgcolor="d3eaef" class="STYLE6"><div align="center"><span class="STYLE10">IP</span></div></td>
        <td width="17%" height="20" bgcolor="d3eaef" class="STYLE6"><div align="center"><span class="STYLE10">内容</span></div></td>
        <td width="19%" height="20" bgcolor="d3eaef" class="STYLE6"><div align="center"><span class="STYLE10">操作时间</span></div></td>
        <td width="29%" height="20" bgcolor="d3eaef" class="STYLE6"><div align="center"><span class="STYLE10">基本操作</span></div></td>
      </tr>
      <% 
      String since_id=(String)request.getAttribute("since_id");
      Integer sum=(Integer)request.getAttribute("sum");
      Integer pagesum=(Integer)request.getAttribute("pagesum");
      int since_idd=Integer.parseInt(since_id);
      if(request.getAttribute("logs")!=null){
        	List<Log> logs=(List<Log>)request.getAttribute("logs");
        
        for(Log l:logs){
        %>
      
      <tr>
        
        <td height="20" bgcolor="#FFFFFF" class="STYLE6"><div align="center"><span class="STYLE19"><%=l.getUsername() %></span></div></td>
        <td height="20" bgcolor="#FFFFFF" class="STYLE19"><div align="center"><%=l.getIp() %></div></td>
        <td height="20" bgcolor="#FFFFFF" class="STYLE19"><div align="center"><%=l.getContent() %></div></td>

        <td height="20" bgcolor="#FFFFFF" class="STYLE19"><div align="center"><%=l.getCreate_date() %></div></td>
        <td height="20" bgcolor="#FFFFFF" class="STYLE19"><div align="center"><a href="${pageContext.request.contextPath}/Log/deletePop3?delid=<%=l.getId() %>&currentpage=<%=since_id%>" onclick="return confirmAct();">删除</a>
    </div></td>
          </tr>
      <%}} %>
     </table></td>
  </tr>
  <tr>
    <td height="30"><table width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td width="33%"><div align="left"><span class="STYLE22">&nbsp;&nbsp;&nbsp;&nbsp;共有<strong> <%=sum %></strong> 条记录，当前第<strong> <%=since_id %></strong> 页，共 <strong><%=pagesum %></strong> 页</span></div></td>
        <td width="67%"><table width="312" border="0" align="right" cellpadding="0" cellspacing="0">
          <tr>
            <td width="49"><div align="center"><a href="${pageContext.request.contextPath}/Log/firstPoppage?since_id=<%=since_id%>"><img src="${pageContext.request.contextPath}/user/images/main_54.gif" width="40" height="15" /></a></div></td>
            <%if(since_idd>1){ %>
            <td width="49"><div align="center"><a href="${pageContext.request.contextPath}/Log/previousPoppage?since_id=<%=since_id%>"><img src="${pageContext.request.contextPath}/user/images/main_56.gif" width="45" height="15" /></a></div></td>
            <%} %>
            <%if(since_idd<pagesum){ %>
            <td width="49"><div align="center"><a href="${pageContext.request.contextPath}/Log/nextPoppage?since_id=<%=since_id%>"><img src="${pageContext.request.contextPath}/user/images/main_58.gif" width="45" height="15" /></a></div></td>
            <%} %>
            <td width="49"><div align="center"><a href="${pageContext.request.contextPath}/Log/lastPoppage?since_id=<%=since_id%>"><img src="${pageContext.request.contextPath}/user/images/main_60.gif" width="40" height="15" /></a></div></td>
            
          </tr>
        </table></td>
      </tr>
    </table></td>
  </tr>
</table>
</body>
</html>
