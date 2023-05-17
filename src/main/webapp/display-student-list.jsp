<%@page import="org.hibernate.internal.build.AllowSysOut"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    
    <%@page import="java.util.List"%>
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
<title>Display Student Page</title>
</head>
<body>

	<% String student=(String) request.getAttribute("student");%>
	<c:if test='<%=student!=null && student.equals("sAdded")%>'>
			<h2 class="pass">One Student record added Successfully!!</h2>
	</c:if>
	<c:if test='<%=student!=null && student.equals("sUpdated")%>'>
			<h2 class="pass">One Student record updated Successfully!!</h2>
	</c:if>
	<c:if test='<%=student!=null && student.equals("sDeleted")%>'>
			<h2 class="failed">One Student record Deleted Successfully!!</h2>
	</c:if>
	
	<div class="list">
		<h1><a href="student">Student</a> List:</h1><br>
		<table class="center" border="1" cellspacing="5" cellpadding="10">
			<thead>
				<tr>
					<th>Sno: </th>
					<th>Name: </th>
					<th>Email: </th>
					<th>Password: </th>
					<th>Mobile: </th>
					<th>Actions: </th>
				</tr>
			</thead>
			
			<tbody>
			<% List<Student> stud=(List<Student>) request.getAttribute("allStudent"); 
				for(Student s:stud)
				{
			%>
					<tr>
						<td><c:out value="<%=s.getSno()%>"></c:out></td>
						<td><c:out value="<%=s.getName()%>"></c:out></td>
						<td><c:out value="<%=s.getEmail()%>"></c:out></td>
						<td class="hide"><c:out value="<%=s.getPassword()%>"></c:out></td>
						<td><c:out value="<%=s.getMobile()%>"></c:out></td>
						<td>
							<input type="submit" value="Edit" class="edit" onclick="editStud(<%=s.getSno()%>);" /> 
					  		&nbsp; &nbsp;  
					  		<input type="submit" value="Delete" class="delete" onclick="deleteStud(<%=s.getSno()%>);" />
						</td>
					</tr>
			<%
				}
			%>
			</tbody>
		</table>
	</div>
</body>
</html>

<!--<a href="delete?id=<c:out value="<%//=s.getSno()%>"></c:out>" onclick="deleteStud()">Delete</a>  -->