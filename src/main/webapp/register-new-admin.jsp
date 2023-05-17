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
<title>Admin Registration Page</title>
</head>
<body>
	<% String admin=(String) request.getAttribute("admin");%>
	<c:if test='<%=admin!=null && admin.equals("regFail")%>'>
			<h2 class="failed">Admin already exist or Password doean't match...Please Try Again!!</h2>
	</c:if>
	<c:if test='<%=admin!=null && admin.equals("delFail")%>'>
			<h2 class="failed">Admin doean't exist or Password doean't match...Please Try Again with correct user and pass!!</h2>
	</c:if>
	
	<form action="register" method="post" class="sForm">
			<h1>Register Admin</h1>
			<br>
			<div>
			  <label for="tbUser">Username: </label>
			  <input type="text" id="tbUser" placeholder="Enter username" name="tbUser" required="required">
			</div>
			<br>
	
			<div>
			  <label for="tbPass">Enter Password: </label>
			  <input type="password" id="tbPass" placeholder="Enter a password" name="tbPass" required="required">
			</div>
			<br>
			
			<div>
			  <label for="tbRePass">Re-enter Password: </label>
			  <input type="password" id="tbRePass" placeholder="Re-enter same password" name="tbRePass" required="required">
			</div>
			<br><br>
			
			<div>
			  <button type="submit" class="btnDel" name="del" onclick="deleteAdmin()">Delete</button>
			  &nbsp; &nbsp;
			  <button type="submit" class="btnReg">Register</button>
			</div>
			<br>
	</form>
			
</body>
</html>