<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
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
<form action="login" method="post">
	<div class="login">
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
		<br>
		
		<div>
		  <button type="submit">Login</button>
		</div>
	</div>
</form>
</html>