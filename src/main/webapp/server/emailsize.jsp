<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<title>设置邮箱大小</title>
	<meta name="keywords" content="" />
	<meta name="description" content="" />
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	
	<link href="../user/css/font-awesome.min.css" rel="stylesheet" type="text/css">
	<link href="../user/css/bootstrap.min.css" rel="stylesheet" type="text/css">
	<link href="../user/css/bootstrap-theme.min.css" rel="stylesheet" type="text/css">
	<link href="../user/css/templatemo_style.css" rel="stylesheet" type="text/css">	
</head>
<script type="text/javascript">
 
      function checkRegister(){
          var name=document.getElementById("size");
          if(name.value=="")
          {
              alert("请输入邮箱大小！");
              name.focus();
              return  false;
          }
          var r = /^[0-9]+.?[0-9]*/;
          if(!r.test(name.value)){
        	  alert("！");
              name.focus();
              return  false;
          }
 
                  return  true;
      }
      
</script>
<body class="templatemo-bg-gray">
	<div class="container">
		<div class="col-md-12">	
			<h1 class="text-center margin-bottom-15">     </h1>		
			<form class="form-horizontal templatemo-contact-form-2 templatemo-container" role="form" method="post" style="margin-top:80px" onSubmit="return checkRegister()" action="${pageContext.request.contextPath}/server/updateemailsize" method="post">
				<div class="row">
					<div class="col-md-6">
						<div class="form-group">				          		          	
				           	<div class="col-sm-12">
				            	<div class="templatemo-input-icon-container">
				            		
				            		当前邮箱大小：
				            	</div>		            		            		            
				          	</div>              
				        </div>
				        <div class="form-group">
				            <div class="col-sm-12">
				            	<div class="templatemo-input-icon-container">
				            		<%=request.getAttribute("emailsize") %>
				            	</div>
				          	</div>
				        </div>
				        <div class="form-group">
				            <div class="col-sm-12">
				            	<div class="templatemo-input-icon-container">
				            		<i class="fa fa-info-circle"></i>
				            		<input type="text" class="form-control" id="size" name="size" placeholder="输入修改大小">
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