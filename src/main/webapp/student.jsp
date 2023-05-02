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
<body>
	<h1>Welcome Admin</h1>
	<br><br>
	<form action="student" class="form">
		<button name="reg">Register Student</button>
		<br><br><br>
		
		<button name="show">Show All Students</button>
		
	</form>
	<div class="out">
		<a href="logout" class="log">Logout</a>
	</div>
</body>
</html>
