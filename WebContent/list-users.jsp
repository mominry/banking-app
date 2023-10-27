<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Users Table</title>
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
	<div class="display-5 m-3">List of Users</div>
	<hr>


	<form class="row align-items-center" action="AdminController" method="get">

		<div class="input-group m-3">
			<input type="text" placeholder="Search..." class="form-control"
				name="search"> <select class="form-select form-select-sm"
				aria-label=".form-select-sm example" name="field">
				<option selected>Select Field</option>
				<option value="id">id</option>
				<option value="firstName" name="firstName">Firstname</option>
				<option value="lastName">Lastname</option>
				<option value="username">Username</option>
			<!--	<option value="accountNo">Account number</option>-->
			</select> <input type="hidden" name="action"  value="search">
			<div class="">
				<button type="submit" class="btn btn-success ">Search</button>
			</div>
		</div>
	</form>



	<table class="table table-striped table-bordered m-3">
		<thead>
			<tr>
				<th>ID</th>
				<th>First Name</th>
				<th>Last Name</th>
				<th>Username</th>
				
				
				<th>Balance</th>
				<th>Update</th>
				<th>Delete</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="temp" items="${usersList}">
				<c:url var="updateLink" value="AdminController">
					<c:param name="action" value="load-update" />
					<c:param name="id" value="${temp.userId}" />
				</c:url>
				<c:url var="deleteLink" value="AdminController">
					<c:param name="action" value="delete" />
					<c:param name="id" value="${temp.userId}" />
				</c:url>
				<tr>
					<td>${temp.userId}</td>
					<td>${temp.firstName}</td>
					<td>${temp.lastName}</td>
					<td>${temp.userName}</td>
					<!--  <td>${temp.accountNo}</td>-->
					<!--<td>${temp.accountType}</td>-->
					<td>${temp.balance}</td>
					<td><a href="${updateLink}" class="btn btn-warning">Update</a></td>
					<td><a href="${deleteLink}" class="btn btn-danger">Delete</a></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</body>
</html>