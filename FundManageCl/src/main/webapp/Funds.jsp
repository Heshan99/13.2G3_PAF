<%@ page import="model.Funds" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%
    
    session.setAttribute("statusMsg","");
    System.out.println("Trying to process.......");
 
	//Save---------------------------------
    if (request.getParameter("itemCode") != null)
    {
     Funds FundObj = new Funds();
     String stsMsg = "";
    //Insert--------------------------
    if (request.getParameter("hidItemIDSave") == "")
     {
     stsMsg = FundObj.insertItem(request.getParameter("itemCode"),
     request.getParameter("itemPrice"),
     request.getParameter("itemDesc"));
     }
    else
    //Update----------------------
     {
     stsMsg = FundObj.updateItem(request.getParameter("hidItemIDSave"),
     request.getParameter("itemCode"),
     request.getParameter("itemPrice"),
     request.getParameter("itemDesc"));
     }
     session.setAttribute("statusMsg", stsMsg);
    }
    //Delete-----------------------------
    if (request.getParameter("hidItemIDDelete") != null)
    {
     Funds FundObj = new Funds();
     String stsMsg =
     FundObj.deleteItem(request.getParameter("hidItemIDDelete"));
     session.setAttribute("statusMsg", stsMsg);
    }
    
    %>
    
<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<title>Fund Management</title>
	
		<link rel="stylesheet" href="Views/bootstrap.css">
		<script src="Components/jquery-3.6.0.min.js"></script>
		<script src="Components/Funds.js"></script>

	</head>

	<body>
	<div class="container">
	<div class="row">
		<div class="col-6">

		<h2><b>Fund Management</b></h2>
	
		<form id="formItem" name="formItem" method="post" action="Funds.jsp">
 		Fund Project Name:
		
		<input id="itemCode" name="itemCode" type="text"
 		class="form-control form-control-sm">
		<br> Amount:

		<input id="itemPrice" name="itemPrice" type="text"
 		class="form-control form-control-sm">
		<br> Fund Estimated By:

		<input id="itemDesc" name="itemDesc" type="text"
 		class="form-control form-control-sm">
		<br>

		<input id="btnSave" name="btnSave" type="button" value="Save"
 		class="btn btn-primary">
		<input type="hidden" id="hidItemIDSave" name="hidItemIDSave" value="">

		</form>
		

	<div id="alertSuccess" class="alert alert-success">
		<%
			out.print(session.getAttribute("statusMsg"));
		%>
	</div>

	<div id="alertError" class="alert alert-danger"></div>
	
		<%
			Funds fundObj = new Funds();
	  		out.print(fundObj.readItems());
		%>
	</div>
	</div>
	</div>
	 </body>
</html>