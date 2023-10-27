<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Update User</title>
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
	<div class="container">
		<div class="row mt-3 align-center">
			<h3 class="display-5">Update User Details</h3>
			<hr>
		</div>
		<form action="AdminController" method="get">
			<input type="text" class="form-control mb-3" name="first-name"
				placeholder="Enter First Name" value ="${theUser.firstName}"> 
				<input type="text"
				class="form-control  mb-3" name="last-name"
				placeholder="Enter Last Name " value ="${theUser.lastName}"> 
				<input type="text"
				class="form-control  mb-3" name="username"
				placeholder="Enter username " value ="${theUser.userName}"> 
				<input type="text"
				class="form-control  mb-3" name="password"
				placeholder="Enter password " value ="${theUser.password}">
				<input type="hidden" name="user_id" value="${theUser.userId}">
			<div class="form-check form-check-inline">
				<input class="form-check-input" type="radio"
					name="account-type" id="inlineRadio1" value="savings">
				<label class="form-check-label" for="inlineRadio1">Savings</label>
			</div>
			<div class="form-check form-check-inline">
				<input class="form-check-input" type="radio"
					name="account-type" id="inlineRadio2" value="current">
				<label class="form-check-label" for="inlineRadio2">Current</label>
			</div>
			<input type="hidden" name="balance" value="${theUser.balance}">
			<div class="text-center">
				<input type="hidden" name="action" value="update">
				<button type="submit" class="btn btn-success ">Create User</button>
			</div>
		</form>
	</div>
</body>
</html>