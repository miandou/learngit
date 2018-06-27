<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java"
import="email.bean.*,java.util.*"%>
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
.divForm1{
position: absolute;/*绝对定位*/

text-align: center;/*(让div中的内容居中)*/
top: 20%;
left: 33%;

}
</style>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<script type="text/javascript">  
	function chkIP(eleText){
		var value = eleText.value;  
		var len = value.length;  
		for(var i = 0;i < len;i++) {  
			if((value.charAt(i) <= "9" && value.charAt(i) >= "0") || (value.charAt(i) == ".")) 
			{
				continue;
			}
			else{
				alert("含有非法字符");
				break;
			}
		}   
	}
	function checkIP()      
	{  var ip = document.getElementById('ip').value;  
	   var re=/^(\d+)\.(\d+)\.(\d+)\.(\d+)$/;//正则表达式     
	   if(re.test(ip))     
	   {     
	       	if( RegExp.$1<256 && RegExp.$2<256 && RegExp.$3<256 && RegExp.$4<256)   
	       		return true;     
	   }     
	   alert("IP有误！");     
	   return false;      
	} 
	
	function confirmAct() 
	{ 
	  if(confirm('确定要执行此操作吗?')) 
	  { 
	    return true; 
	  } 
	  return false; 
	} 
	</script>  
</script>
<title>过滤IP列表</title>
</head>

<c:if test="${flag_add!=null}">
	<c:if test="${flag_add==0}">
		<script>
		setTimeout(function(){alert("该IP已存在过滤列表中")},100);
		</script>
	</c:if>
	<c:if test="${flag_add==1}">
		<script>
		setTimeout(function(){alert("添加过滤IP成功")},100);
		</script>
	</c:if>
</c:if>

<body>


    <form method="post" action="${pageContext.request.contextPath}/setting/addIP" onsubmit="return checkIP()">
            <label>过滤IP地址：</label>
            <input type="text" name="ip" id="ip">
            <input type="submit" value="添加"/>
    </form>
 
    <table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
  <tr>
    <td height="30"><table width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td height="24" bgcolor="#353c44"><table width="100%" border="0" cellspacing="0" cellpadding="0">
          <tr>
            <td><table width="100%" border="0" cellspacing="0" cellpadding="0">
              <tr>
                <td width="6%" height="19" valign="bottom"><div align="center"><img src="images/tb.gif" width="14" height="14" /></div></td>
                <td width="94%" valign="bottom"><span class="STYLE1"> 过滤IP地址</span></td>
              </tr>
            </table></td>
          </tr>
        </table></td>
      </tr>
    </table></td>
  </tr>
  <tr>
    <td><table width="50%" border="0" cellpadding="0" cellspacing="1" bgcolor="#a8c7ce" align="center">
      <tr>
       
        <td width="50%" height="20" bgcolor="d3eaef" class="STYLE6"><div align="center"><span class="STYLE10">IP地址</span></div></td>
        <td width="50%" height="20" bgcolor="d3eaef" class="STYLE6"><div align="center"><span class="STYLE10">基本操作</span></div></td>
      </tr>
      
      
      <% 
      String since_id=(String)request.getAttribute("since_id");
      //session.setAttribute("since_id", since_id);
      Integer sum=(Integer)request.getAttribute("sum");
      Integer pagesum=(Integer)request.getAttribute("pagesum");
      int since_idd=Integer.parseInt(since_id);
      if(request.getAttribute("ips")!=null){
        	List<Filter_Ip> ips=(List<Filter_Ip>)request.getAttribute("ips");
        
        for(Filter_Ip f:ips){
        %>
      <tr>
        
        <td width="50%" height="20" bgcolor="#FFFFFF" class="STYLE6"><div align="center"><span class="STYLE19"><%=f.getIp() %></span></div></td>
        <td width="50%" height="20" bgcolor="#FFFFFF" class="STYLE6"><div align="center"><span class="STYLE19"><a href="${pageContext.request.contextPath}/setting/delete?delip=<%=f.getIp()%>&currentpage=<%=since_id%>" onclick="return confirmAct();">解除过滤</a><td>
        
      </tr>
      <%}} 
      
      %>
    </table>
  <tr>
    <td height="30"><table width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td width="40%"><div align="left"><span class="STYLE22">&nbsp;&nbsp;&nbsp;&nbsp;共有<strong> <%=sum %></strong> 条记录，当前第<strong> <%=since_id %></strong> 页，共 <strong><%=pagesum %></strong> 页</span></div></td>
        <td width="60%"><table width="320" border="0" align="left" cellpadding="0" cellspacing="0">
          <tr>
            <td width="49"><div align="center"><a href="${pageContext.request.contextPath}/setting/firstpage?since_id=<%=since_id%>"><img src="images/main_54.gif" width="40" height="15" /></a></div></td>
            <%if(since_idd>1){ %>
            <td width="49"><div align="center"><a href="${pageContext.request.contextPath}/setting/previouspage?since_id=<%=since_id%>"><img src="images/main_56.gif" width="45" height="15" /></a></div></td>
            <%} %>
            <%if(since_idd<pagesum){ %>
            <td width="49"><div align="center"><a href="${pageContext.request.contextPath}/setting/nextpage?since_id=<%=since_id%>"><img src="images/main_58.gif" width="45" height="15" /></a></div></td>
            <%} %>
            <td width="49"><div align="center"><a href="${pageContext.request.contextPath}/setting/lastpage?since_id=<%=since_id%>"><img src="images/main_60.gif" width="40" height="15" /></a></div></td>
            
          </tr>
        </table></td>
      </tr>
    </table></td>
  </tr>
</table>
    
</body>
</html>