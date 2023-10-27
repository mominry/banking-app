<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Transactions Table</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC"
	crossorigin="anonymous">
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
	integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"
	crossorigin="anonymous"></script>
</head>
<body>
	<div class="display-5 m-3">List of Transactions</div>
	<hr>
	
	<!-- 
	<a href="add-student.jsp" class="btn btn-primary m-3">Add New User</a>
		<div class="input-group m-3">
	<form class="row align-items-center" action="" method="get">
    
     <div class="input-group mb-3">
      <input type="text" placeholder="Search..." class="form-control" name="search">
      <select class="form-select form-select-sm"
       aria-label=".form-select-sm example" name="field">
       <option selected>Select Field</option>
       <option value="id">id</option>
       <option value="firstName" name="firstName">Firstname</option>
       <option value="lastName">Lastname</option>
       <option value="email">Account number</option>
      </select>
 
    <input type="hidden" name="action" value="search">
    <div class="">
    <button type="submit" class="btn btn-success ">Search</button>
    </div>
   </form>
   </div>
    -->
	
	
	<table class="table table-striped table-bordered m-3">
		<thead>
			<tr>
				<th>Transaction ID</th>
				<th>Account Number</th>
				<th>Transaction Type</th>
				<th>Time of transaction</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="temp" items="${transactions}">
			
				<tr>
					<td>${temp.transactionId}</td>
					<td>${temp.accountNo}</td>
					<td>${temp.transactionType}</td>
					<td>${temp.time}</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</body>
</html>