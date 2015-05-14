<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>验证</title>
<script type="text/javascript">
	function same(){
		loginForm.action = "./leaflet/login.action";
		loginForm.submit();
	}
	function different(){
		alert("请输入正确的验证码");
		location.href = "login.jsp";
	}
</script>
</head>
<body>
	<%
	String userName = request.getParameter("user.userName");
	String password = request.getParameter("user.password");
	%>
	<form id="loginForm" name="loginForm" method="post">
		<input type="hidden" id="userName" value=<%=userName %>  name="user.userName">
		<input type="hidden" id="password" value=<%=password %> name="user.password">
	</form>
	<%
		String rand = session.getAttribute("verifycode").toString();
		String input = request.getParameter("verifycode");
		if(rand.equals(input)){
			out.println("<script language='javascript'>same();</script>");
		}else{
			out.print("<script language='javascript'>different();</script>");
		}
	%>
	
</body>
</html>