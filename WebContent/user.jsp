<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="true" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>User Transactions</title>
<style>
/* Styles for the container */
.container {
	display: flex;
	flex-direction: column;
	align-items: center;
	justify-content: center;
	height: 100vh;
	background-color: #fff; /* Black background */
	/* color: #fff;  White text */
}

/* Button styles */
.btn {
	display: inline-block;
	padding: 10px 20px;
	background-color: #3498db;
	color: #fff;
	text-decoration: none;
	border-radius: 5px;
	font-weight: bold;
	transition: background-color 0.3s;
	margin-bottom: 20px;
}

.btn:hover {
	background-color: #2980b9;
}

/* Table styles */
table {
	border-collapse: collapse;
	width: 80%;
	max-width: 600px;
	background: rgba(255, 255, 255, 0.1);
	/* Transparent table background */
	color: black; /* White text for table */
}

table, th, td {
	border: 1px solid #3498db;
	text-align: center;
}

th, td {
	padding: 10px;
}
</style>
</head>
<body>

	<div class="container">
		<a href="withdraw-deposit.jsp" class="btn">Withdraw Deposit Facility</a>

		<a href="fund-transfer.jsp" class="btn">fund transfer</a>
			
		<table border="1">
			<!-- Table header here -->
			<thead>
				<tr>
					<th>Transaction Id</th>
					<th>Account No</th>
					<th>Transaction Type</th>
					<th>Time</th>


				</tr>
			</thead>
			<tbody>
				<c:forEach var="transaction" items="${userTransactionList}">
					<tr>


						<td>${transaction.transactionId}</td>
						<td>${transaction.accountNo}</td>
						<td>${transaction.transactionType}</td>
						<td>${transaction.time}</td>


					</tr>

				</c:forEach>
			</tbody>
		</table>
	</div>

</body>
</html>
