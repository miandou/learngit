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
                <td width="6%" height="19" valign="bottom"><div align="center"><img src="images/tb.gif" width="14" height="14" /></div></td>
                <td width="94%" valign="bottom"><span class="STYLE1"> 管理人员基本信息列表</span></td>
              </tr>
            </table></td>
            <td><div align="right"><span class="STYLE1">
              
			  <a href="addUser.jsp" target="rightFrame"><img src="images/add.gif" width="10" height="10" /><span style="color:white"> 添加</span>   &nbsp;</a> </span><span class="STYLE1"> &nbsp;</span></div></td>
          </tr>
        </table></td>
      </tr>
    </table></td>
  </tr>
  <tr>
    <td><table width="100%" border="0" cellpadding="0" cellspacing="1" bgcolor="#a8c7ce" onmouseover="changeto()"  onmouseout="changeback()">
      <tr>
       
        <td width="12%" height="20" bgcolor="d3eaef" class="STYLE6"><div align="center"><span class="STYLE10">ID</span></div></td>
        <td width="13%" height="20" bgcolor="d3eaef" class="STYLE6"><div align="center"><span class="STYLE10">用户昵称</span></div></td>
        <td width="12%" height="20" bgcolor="d3eaef" class="STYLE6"><div align="center"><span class="STYLE10">用户姓名</span></div></td>
        <td width="14%" height="20" bgcolor="d3eaef" class="STYLE6"><div align="center"><span class="STYLE10">权限</span></div></td>
        <td width="15%" height="20" bgcolor="d3eaef" class="STYLE6"><div align="center"><span class="STYLE10">状态</span></div></td>
        <td width="10%" height="20" bgcolor="d3eaef" class="STYLE6"><div align="center"><span class="STYLE10">过滤</span></div></td>
        <td width="24%" height="20" bgcolor="d3eaef" class="STYLE6"><div align="center"><span class="STYLE10">基本操作</span></div></td>
      </tr>
      
      
      <% 
      String since_id=(String)request.getAttribute("since_id");
      //session.setAttribute("since_id", since_id);
      Integer sum=(Integer)request.getAttribute("sum");
      Integer pagesum=(Integer)request.getAttribute("pagesum");
      int since_idd=Integer.parseInt(since_id);
      if(request.getAttribute("users")!=null){
        	List<User> users=(List<User>)request.getAttribute("users");
        
        for(User u:users){
        %>
      <tr>
        
        <td height="20" bgcolor="#FFFFFF" class="STYLE6"><div align="center"><span class="STYLE19"><%=u.getId() %></span></div></td>
        <td height="20" bgcolor="#FFFFFF" class="STYLE19"><div align="center"><%=u.getNickname() %></div></td>
        <td height="20" bgcolor="#FFFFFF" class="STYLE19"><div align="center"><%=u.getUsername() %></div></td>
        <%String s="",s1="",b="",b1="",b2="",y="",y1="",y2="";
        if(u.getPower()==0) {
        	s="普通用户";
        	s1="提升权限";
        }
        else{
        	s="管理员";
        }
        if(u.isDisable_flag()==true){
        	b="禁用中";
        	b1="解禁";
        	b2="0";
        	s1="";
        }
        else{
        	b="未禁用";
        	b1="禁用";
        	b2="1";
        }
        
        if(u.isIsfilter()){
        	y="已过滤";
        	y1="解除过滤";
        	y2="0";
        }
        else{
        	y="未过滤";
        	y1="过滤";
        	y2="1";
        }
        %>
        <td height="20" bgcolor="#FFFFFF" class="STYLE19"><div align="center"><%=s %></div></td>
        <td height="20" bgcolor="#FFFFFF" class="STYLE19"><div align="center"><%=b %></div></td>
         <td height="20" bgcolor="#FFFFFF" class="STYLE19"><div align="center"><%=y %></div></td>
        <td height="20" bgcolor="#FFFFFF"><div align="center" class="STYLE21">
        <%if(!s.equals("管理员")){ %>
        <a href="${pageContext.request.contextPath}/user/delete?delid=<%=u.getId()%>&currentpage=<%=since_id%>" onclick="return confirmAct();">删除</a> | <a href="${pageContext.request.contextPath}/user/disable?disable=<%=b2%>&disableid=<%=u.getId()%>&currentpage=<%=since_id%>"><%=b1 %></a> | <a href="${pageContext.request.contextPath}/user/filter?filter=<%=y2 %>&filterid=<%=u.getId()%>&currentpage=<%=since_id%>"><%=y1 %></a> | <a href="${pageContext.request.contextPath}/user/power?powerid=<%=u.getId()%>&currentpage=<%=since_id%>"><%=s1 %></a></div>
        <%} %>
        </td>
      </tr>
      <%}} 
      
      %>
    </table></td>
  </tr>
  <tr>
    <td height="30"><table width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td width="33%"><div align="left"><span class="STYLE22">&nbsp;&nbsp;&nbsp;&nbsp;共有<strong> <%=sum %></strong> 条记录，当前第<strong> <%=since_id %></strong> 页，共 <strong><%=pagesum %></strong> 页</span></div></td>
        <td width="67%"><table width="312" border="0" align="right" cellpadding="0" cellspacing="0">
          <tr>
            <td width="49"><div align="center"><a href="${pageContext.request.contextPath}/user/firstpage?since_id=<%=since_id%>"><img src="images/main_54.gif" width="40" height="15" /></a></div></td>
            <%if(since_idd>1){ %>
            <td width="49"><div align="center"><a href="${pageContext.request.contextPath}/user/previouspage?since_id=<%=since_id%>"><img src="images/main_56.gif" width="45" height="15" /></a></div></td>
            <%} %>
            <%if(since_idd<pagesum){ %>
            <td width="49"><div align="center"><a href="${pageContext.request.contextPath}/user/nextpage?since_id=<%=since_id%>"><img src="images/main_58.gif" width="45" height="15" /></a></div></td>
            <%} %>
            <td width="49"><div align="center"><a href="${pageContext.request.contextPath}/user/lastpage?since_id=<%=since_id%>"><img src="images/main_60.gif" width="40" height="15" /></a></div></td>
            
          </tr>
        </table></td>
      </tr>
    </table></td>
  </tr>
</table>
</body>
</html>
