<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8" %>
<!DOCTYPE html>
<head>
	<title>Create Account</title>
	<meta name="keywords" content="" />
	<meta name="description" content="" />
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	
	<link href="css/bootstrap.min.css" rel="stylesheet" type="text/css">
	<link href="css/bootstrap-theme.min.css" rel="stylesheet" type="text/css">
	<link href="css/templatemo_style.css" rel="stylesheet" type="text/css">	
</head>
<script type="text/javascript">
 
      function checkRegister(){
          var name=document.getElementById("username");
          if(name.value=="")
          {
              alert("请输入邮箱！");
              name.focus();
              return  false;
          }
 
          var nickname=document.getElementById("nickname");
          if(nickname.value=="")
          {
          
              alert("请输入昵称！");
              nickname.focus();
              return  false;
          }
          
          var password=document.getElementById("password");
          if(password.value=="")
          {
          
              alert("请输入密码！");
              password.focus();
              return  false;
          }
          
          
                  return  true;
      }
</script>
<body class="templatemo-bg-gray">

	<div class="container">
		<div class="col-md-12">			
			<form class="form-horizontal templatemo-create-account templatemo-container" onSubmit="return checkRegister()" role="form" action="${pageContext.request.contextPath}/user/save" method="post">
				<div class="form-inner">
			        <div class="form-group">
			          <div class="col-md-12">		          	
			            <label for="username" class="control-label">用户名</label>
			            <input type="text" class="form-control" name="username" maxlength="20" id="username" placeholder="点击输入用户名">
			          </div>              
			        </div>			
			        <div class="form-group">
			          <div class="col-md-6">		          	
			            <label for="username" class="control-label">昵称</label>
			            <input type="text" class="form-control" name="nickname" maxlength="20" id="nickname" placeholder="点击输入昵称">	
				            		            		            
			          </div>
			                      
			        </div>
			        <div class="form-group">
			          <div class="col-md-6">
			            <label for="password" class="control-label">Password</label>
			            <input type="password" class="form-control" name="password" maxlength="20" id="password" placeholder="点击输入密码">
			          </div>
					 </div>
					 
					 <div class="form-group">
			          <div class="col-md-6">
			            <label for="username" class="control-label">用户类型</label><br/>
            			<input type="radio" name="power" value="0" checked="checked"/>普通用户&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            			<input type="radio" name="power" value="1"/>管理员
			          </div>
					 </div>
					 
			        <div class="form-group">
			          <div class="col-md-12">
			            <input type="submit" value="添加" class="btn btn-info">
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<a href="${pageContext.request.contextPath}/user/list" target="rightFrame"><input type="button" value="返回" class="btn btn-info"></a>
			          </div>
			        </div>	
						
				</div>				    	
		      </form>		      
		</div>
	</div>

	<script type="text/javascript" src="js/jquery-1.11.1.min.js"></script>
	<script type="text/javascript" src="js/bootstrap.min.js"></script>
</body>
</html>