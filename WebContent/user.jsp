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
            background-color: #000; 
            color: #fff;
        }

       
        .card {
            background: url('your-background-image.jpg'); 
            background-size: cover;
            border: 1px solid #3498db;
            border-radius: 10px;
            width: 300px;
            height: 150px;
            padding: 20px;
            margin: 10px;
            text-align: center;
            box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
            position: relative;
        }

        .overlay {
            position: absolute;
            top: 0;
            left: 0;
            width: 100%;
            height: 100%;
            background: rgba(0, 0, 0, 0.5); 
            border-radius: 10px;
            display: flex;
            align-items: center;
            justify-content: center;
        }

       
        .btn {
            display: inline-block;
            padding: 10px 20px;
            background-color: #3498db;
            color: #fff;
            text-decoration: none;
            border-radius: 5px;
            font-weight: bold;
            transition: color 0.3s;
        }

        .card .btn {
            color: #fff;
        }
    </style>
</head>
<body>
<h1>Welcome ${userObj.firstName} !!!</h1>

    
<div class="container">
 <div class="card">
        <div class="overlay">
            <a href="userdetails.jsp" class="btn btn-warning"><i class="fas fa-pencil-alt"></i> Details</a>
        </div>
    </div>
    <div class="card">
        <div class="overlay">
            <a href="updateUser.jsp" class="btn btn-warning"><i class="fas fa-pencil-alt"></i> Update</a>
        </div>
    </div>
    <div class="card">
        <div class="overlay">
            <a href="withdraw-deposit.jsp" class="btn">Withdraw Deposit Facility</a>
        </div>
    </div>

    <div class="card">
        <div class="overlay">
            <a href="fund-transfer.jsp" class="btn">Fund Transfer</a>
        </div>
    </div>

    <div class="card">
        <div class="overlay">
            <a href="passbook.jsp" class="btn">PassBook</a>
        </div>
    </div>

    
   
</div>

</body>
</html>
