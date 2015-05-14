<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>后台管理</title>
    <link href="static/css/login.css" rel="stylesheet" type="text/css" />
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<script language="javascript" src="./static/js/jquery-1.8.0.min.js"></script>
  </head>

  <body>
    	<div id="OutBox">
    	<div class="top"></div>
		<div class="Mid">
			<div class="Right">
			<form action="check.jsp"  method="post" id="subForm">
				<table border="0" cellpadding="0" cellspacing="0">
				  <tr>
					<td>用户名：</td>
					<td>
					  <input name="user.userName" id="userName" type="text" />
					</td>
				  </tr>
				  <tr>
					<td>密　码：</td>
					<td>
					  <input  name="user.password" id="password" type="password"/>
					</td>
				  </tr>
				  <tr>
					<td>验证码：</td>
					<td>
					  <input  name="verifycode" id="verifycode" type="text" maxlength="4"/>
					  <img id="image" border="0"  onclick="refresh()" src="image.jsp" title="点击更换图片" >
					</td>
				  </tr>
				  <tr>
					<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
					<td>
					  <input type="button" class="btnSubmit" name="button" value="" onclick="login()"/>
					</td>
				  </tr>
			  </table>
			  </form>
			</div>
		</div>
		<div class="Bot">技术支持：南储仓储管理集团有限公司  信息科技部</div>
	</div>
<script type="text/javascript">
  function login(){
	var name = $('#userName').val();
	var pass = $('#password').val();
	var verifycode = $('#verifycode').val();
	if(name == '' || name == null){
		alert("用户名不能为空！");
		$('#userName').focus();
		return;
	}
	if(pass == '' || pass == null){
		alert("密码不能为空！");
		$('#password').focus();
		return;
	}
	if(verifycode == '' || verifycode == null){
		alert("验证码不能为空！");
		$('#verifycode').focus();
		return;
	}
	$('#subForm').submit();
  }
  function refresh() {  
        //IE存在缓存,需要new Date()实现更换路径的作用  
        document.getElementById("image").src="image.jsp?"+new Date();  
    }  
  </script>
  </body>
</html>
