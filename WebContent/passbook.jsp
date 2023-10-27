<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>User Passbook</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: rgba(0, 0, 0, 0.7);
            margin: 0;
            padding: 0;
            color: #fff;
        }

        h2 {
            text-align: center;
            background-color: #007bff;
            color: #fff;
            padding: 20px 0;
        }

        table {
            width: 80%;
            border-collapse: collapse;
            margin: 20px auto;
            background-color: rgba(255, 255, 255, 0.1);
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }

        th, td {
            padding: 10px;
            text-align: left;
        }

        th {
            background-color: #007bff;
            color: #fff;
        }

        tr:nth-child(even) {
            background-color: rgba(255, 255, 255, 0.2);
        }

        .back-button {
            text-align: center;
            margin-top: 20px;
        }

        .back-button a {
            background-color: #007bff;
            color: #fff;
            padding: 10px 20px;
            text-decoration: none;
            border-radius: 5px;
            font-weight: bold;
            transition: background-color 0.3s;
        }

        .back-button a:hover {
            background-color: #0056b3;
        }
    </style>
</head>
<body>
    <h2>User Passbook</h2>

    <table border="1">
        <tr>
           <th>User Id</th>
            
            <th>Transaction Id</th>
            <th>Account No</th>
            <th>Transaction Type</th>
            <th>Time</th>
            <th>Reciever Account No</th>
            <th>Amount</th>
        </tr>
        <c:forEach var="transaction" items="${userTransactionList}">
            <tr>
            <td>${userObj.userId}</td>
            
                <td>${transaction.transactionId}</td>
                <td>${transaction.accountNo}</td>
                <td>${transaction.transactionType}</td>
                <td>${transaction.time}</td>
                <td>${transaction.recievedAccountNo}</td>
				<td>${transaction.amount}</td>
            </tr>
        </c:forEach>
    </table>

    <div class="back-button">
        <a href="user.jsp">Back</a>
    </div>
</body>
</html>
