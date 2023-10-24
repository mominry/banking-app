<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

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
            background-color: #000; /* Black background */
            color: #fff; /* White text */
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
            background: rgba(255, 255, 255, 0.1); /* Transparent table background */
            color: #fff; /* White text for table */
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
    <a href="TransactionOptions.jsp" class="btn">Doing more transactions click here</a>

    <table border="1">
        <!-- Table header here -->
        <tr>
            <th>Transaction Id</th>
            <th>Account No</th>
            <th>Transaction Type</th>
            <th>Time</th>
            <th>Balance</th>
            <th>Account Name</th> <!-- Assuming you have an account name property -->
            <th>Account Number</th> <!-- Assuming you have an account number property -->
        </tr>

       <c:forEach items="${transaction}" var="transactionList">
    <tr>
        <td>${transaction.transactionId}</td>
        <td>${transaction.account.accountNumber}</td>
        <td>${transaction.transactionType}</td>
        <td>${transaction.time}</td>
        <td>${transaction.balance}</td>
        <td>${transaction.account.accountName}</td>
        <td>${transaction.account.accountNumber}</td>
    </tr>
</c:forEach>
    </table>
</div>

</body>
</html>
