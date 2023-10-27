<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Update User</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <style>
        body {
            background-color: rgba(0, 0, 0, 0.7); /* Black background with transparency */
        }
        .container {
            background-color: #fff; /* White background for the container */
            border: 1px solid #ccc;
            border-radius: 5px;
            padding: 20px;
            margin: 20px auto;
            max-width: 400px;
        }
        .form-label {
            font-weight: bold;
        }
    </style>
</head>
<body>
<div class="container">
    <div class="row">
        <h1>Edit Your Data</h1>
    </div>
    <form action="LoginController" method="post">
        <div class="mb-3">
            <label for="firstName" class="form-label">First Name:</label>
            <input type="text" class="form-control" id="firstName" name="firstName" value="${userObj.firstName}">
        </div>
        <div class="mb-3">
            <label for="lastName" class="form-label">Last Name:</label>
            <input type="text" class="form-control" id="lastName" name="lastName" value="${userObj.lastName}">
        </div>
        <div class="mb-3">
            <label for="userName" class="form-label">User Name:</label>
            <input type="text" class="form-control" id="userName" name="userName" value="${userObj.userName}">
        </div>
        <div class="mb-3">
            <label for="password" class="form-label">Password:</label>
            <input type="text" class="form-control" id="password" name="password" value="${userObj.password}">
        </div>
        
        <button type="submit" class="btn btn-primary">Update</button>
        <input type="hidden" name="userId" value="${userObj.userId}">
        <input type="hidden" name="action" value="update">
    </form>
</div>
</body>
</html>
