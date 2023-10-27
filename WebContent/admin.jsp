<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN"
	crossorigin="anonymous">
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"
	integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL"
	crossorigin="anonymous"></script>
<head>
<meta charset="ISO-8859-1">
<title>Admin Homepage</title>
</head>
<body>
	<h1 class="display-3 text-center">Welcome Administrator</h1>
	<div class="container">
		<a href="login.jsp" class="btn btn-danger btn-sm float-end">Logout</a>
		<br>
		<hr>
		<div class="row row-cols-2">
			<div class="col">
				<div class="card" style="width: 30rem;">
					<div class="card-header lead">Manage Users</div>


					<ul class="list-group list-group-flush">
						<form action="AdminController" method="get">
							<input type="hidden" name="action" value="search">
							<button type="submit" class=" btn btn-success mt-3">users</button>
						</form>
						<li><a href="add-user.jsp" class="btn btn-primary mt-3">Add
								User</a></li>
						<li><a href="AddAccount.jsp" class="btn btn-primary mt-3">Add
								Account</a></li>
					</ul>
				</div>
			</div>
			<div class="col">
				<div class="card" style="width: 30rem;">
					<div class="card-header lead">Manage Transactions</div>
					<ul class="list-group list-group-flush">
						<li><a href="list-transactions.jsp"
							class="btn btn-primary mt-3">View all transactions</a></li>
					</ul>
				</div>
			</div>

			<!--
			<div class="col lead">Manage Users:</div>
			<div class="col lead">Manage Transactions:</div>
			<div class= "btn btn-outline-success mt-3">View all users</div>
			<div class= "btn btn-success mt-3">View all transactions</div>
			<div class= "btn btn-outline-info mt-3">Add new user</div>
		-->
		</div>
	</div>
</body>
</html>