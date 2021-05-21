<%@page import="model.Users"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" href="Views/bootstrap.min.css">
<script src="Components/jquery-3.6.0.min.js"></script>
<script src="Components/users.js"></script>
<script>

</script>
</head>

<body>
<form id="formItem" name="formItem" method="post" action="users.jsp">
  User Name: 
<input id="itemCode" name="itemCode" type="text" 
 class="form-control form-control-sm">
<br> User Email: 
<input id="itemName" name="itemName" type="text" 
 class="form-control form-control-sm">
<br> User Password: 
<input id="itemPrice" name="itemPrice" type="text" 
 class="form-control form-control-sm">
<br> User Phone Number: 
<input id="itemDesc" name="itemDesc" type="text" 
 class="form-control form-control-sm">
<br>
<input id="btnSave" name="btnSave" type="button" value="Save" 
 class="btn btn-primary">
<input type="hidden" id="hidItemIDSave" name="hidItemIDSave" value="">
</form>
<br>
<div id="alertSuccess" class="alert alert-success">
			<% out.print(session.getAttribute("statusMsg")); %>
</div>
<br>
<div>
			<%
			Users projectObj = new Users();
			out.print(projectObj.readItems());
			%>
</div>

		
		
		<br>


</body>
</html>
