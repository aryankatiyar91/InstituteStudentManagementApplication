<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    
    <%@page import="com.myweb.model.Student" %>
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
<title>Register Page</title>
</head>
<body>
<div class="sForm">
	<% Student s=(Student) request.getAttribute("oneStud");%>

	<c:if test="<%=s==null%>">
		<form action="insert" method="post">
		<h2>Student Registration</h2>
		<br>
		<label for="tbName">Name: </label>
		<input type="text" id="tbName" placeholder="Enter name" name="tbName" required/>
		<br><br>
		<label for="tbEmail">Email: </label>
		<input type="email" id="tbEmail" placeholder="Enter email" name="tbEmail" required/>
		<br><br>
		<label for="tbPass">Password: </label>
		<input type="password" id="tbPass" placeholder="Enter password" name="tbPass" required/>
		<br><br>
		<label for="tbMob">Mobile: </label>
		<input type="tel" id="tbMob" placeholder="Enter mobile" name="tbMob" required/>
		<br><br><br>
		
		<input type="submit" value="Save" class="save" onclick="add();">
		</form>
	</c:if>
	
	<c:if test="<%=s!=null%>">
		<form action="update" method="post">
		<h2>Edit Student Data</h2>
		<br>
		<input type="hidden" id="tbId" value="<c:out value="<%=s.getSno()%>"></c:out>" name="tbId">
		<br><br>
		<label for="tbName">Name: </label>
		<input type="text" id="tbName" placeholder="Enter name" value="<c:out value="<%=s.getName()%>"></c:out>" name="tbName" required>
		<br><br>
		<label for="tbEmail">Email: </label>
		<input type="email" id="tbEmail" placeholder="Enter email" value="<c:out value="<%=s.getEmail()%>"></c:out>" name="tbEmail" required>
		<br><br>
		<label for="tbPass">Password: </label>
		<input type="password" id="tbPass" placeholder="Enter password" value="<c:out value="<%=s.getPassword()%>"></c:out>" name="tbPass" required>
		<br><br>
		<label for="tbMob">Mobile: </label>
		<input type="tel" id="tbMob" placeholder="Enter mobile" value="<c:out value="<%=s.getMobile()%>"></c:out>"  name="tbMob" required>
		<br><br><br>
		
		<input type="submit" value="Update" class="save" onclick="update();">
		</form>
	</c:if>
</div>		
</body>
</html>