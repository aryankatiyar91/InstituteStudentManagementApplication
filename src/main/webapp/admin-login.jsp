<%@page import="org.hibernate.internal.build.AllowSysOut"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<style>
	<%@ include file="css/style.css"%>
</style>

<script type="text/javascript">
	<%@ include file="js/sample.js" %>
</script>
<head>
<meta charset="ISO-8859-1">
<title>Admin Login Page</title>

</head>
<body>
	<% String admin=(String) request.getAttribute("admin");%>
	<c:if test='<%=admin!=null && admin.equals("logFail")%>'>
			<h2 class="failed">Login failed...Invalid Credentials</h2>
			<h3 class="fail">Try Again with valid credentials!!</h3>
	</c:if>
	<c:if test='<%=admin!=null && admin.equals("aReg")%>'>
			<h2 class="pass">Admin <%=request.getParameter("tbUser") %> Registered Successfully!!</h2>
	</c:if>
	<c:if test='<%=admin!=null && admin.equals("aDelete")%>'>
			<h2 class="pass">Admin <%=request.getParameter("tbUser") %> Deleted Successfully!!</h2>
	</c:if>
	
	<form action="login" method="post">
		<h1>Admin Login</h1>
		<br>
		<div>
		  <label for="tbUser">Username: </label>
		  <input type="text" id="tbUser" placeholder="Enter username" name="tbUser" required="required">
		</div>
		<br>

		<div>
		  <label for="tbPass">Password: </label>
		  <input type="password" id="tbPass" placeholder="Enter password" name="tbPass" required="required">
		</div>
		<br><br>
		
		<div>
		  <button type="submit" class="btnLogin" >Login</button>
		  <br><br><br>
		  <h3>For New Registration?&nbsp;&nbsp;
		  	<a href="register" class="signUp">SignUp</a>
		  </h3>
		</div>
	</form>
</html>