<%@page import="model.Product"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
      
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" href="Views/bootstrap.min.css">    <!-- Link Bootstrap, jQuery, and main.js -->
<script src="Components/jquery-3.6.0.min.js"></script>
<script src="Components/products.js"></script> 
</head>
<body>

<form id="formItem" name="formItem" method="post" action="products.jsp">
 Product code:
<input id="itemCode" name="itemCode" type="text"
 class="form-control form-control-sm">
<br> Product name:
<input id="itemName" name="itemName" type="text"
 class="form-control form-control-sm">
<br> Product price:
<input id="itemPrice" name="itemPrice" type="text"
 class="form-control form-control-sm">
<br> Product description:
<input id="itemDesc" name="itemDesc" type="text"
 class="form-control form-control-sm">
<br>
<input id="btnSave" name="btnSave" type="button" value="Save"
 class="btn btn-primary">
<input type="hidden" id="hidItemIDSave" name="hidItemIDSave" value="">
</form>

<br>
<div id="alertSuccess" class="alert alert-success"></div>
<div id="alertError" class="alert alert-danger"></div>

<br>
<div id = "divItemsGrid">
<%
Product prodObj = new Product();
out.print(prodObj.readItems());
%>
</div>




</body>
</html>