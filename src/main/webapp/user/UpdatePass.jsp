<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8" %>
<!DOCTYPE html>
<head>
	<title>修改密码</title>
	<meta name="keywords" content="" />
	<meta name="description" content="" />
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	
	<link href="css/font-awesome.min.css" rel="stylesheet" type="text/css">
	<link href="css/bootstrap.min.css" rel="stylesheet" type="text/css">
	<link href="css/bootstrap-theme.min.css" rel="stylesheet" type="text/css">
	<link href="css/templatemo_style.css" rel="stylesheet" type="text/css">	
</head>

<script type="text/javascript">
function checkRegister(){
          var password=document.getElementById("newpsd");
          if(password.value=="")
          {
          
              alert("请输入密码！");
              password.focus();
              return  false;
          }
          var password2=document.getElementById("renewpsd");
 
          if(password.value!=password2.value)
          {
              alert("输入的密码不一致！");
              password2.focus();
              return  false;
          }
          
                  return  true;
      }
</script>
<body class="templatemo-bg-gray">
	<div class="container">
		<div class="col-md-12">	
			<h1 class="text-center margin-bottom-15">修改密码</h1>		
			<form class="form-horizontal templatemo-contact-form-2 templatemo-container" onSubmit="return checkRegister()" role="form" action="${pageContext.request.contextPath}/user/updatepsd" method="post">
				<div class="row">
					<div class="col-md-6">
				        <div class="form-group">
				            <div class="col-sm-12">
				            	<div class="templatemo-input-icon-container">
				            		<i class="fa fa-info-circle"></i>
				            		<input type="password" class="form-control" id="newpsd" maxlength="20" name="newpsd" placeholder="输入新密码">
				            	</div>
				          	</div>
				        </div>
				        <div class="form-group">
				            <div class="col-sm-12">
				            	<div class="templatemo-input-icon-container">
				            		<i class="fa fa-info-circle"></i>
				            		<input type="password" class="form-control" id="renewpsd" maxlength="20" name="renewpsd" placeholder="重复新密码">
				            	</div>
				          	</div>
				        </div>
					</div>
									
				</div>	        
		        <div class="form-group">
		          
		            <input type="submit" value="确认修改" class="btn btn-warning pull-right" style="position:absolute;left:45%;top:370px">		            
		          </div>
		        	    	
		      </form>	
    	</div>	
	</div>
</body>
</html>