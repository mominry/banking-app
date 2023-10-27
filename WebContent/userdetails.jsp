<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Banking Application</title>
    <style>
        body {
            background-color: black;
            font-family: Arial, sans-serif;
            color: white;
        }

        .transparent-box {
            background-color: rgba(0, 0, 0, 0.7);
            border-radius: 10px;
            padding: 20px;
            margin: 20px auto;
            max-width: 400px;
        }

        .form-label {
            color: white;
        }

        .form-control {
            background-color: rgba(255, 255, 255, 0.8);
            border: none;
            border-radius: 5px;
            color: black;
        }

        .form-control:focus {
            background-color: rgba(255, 255, 255, 0.9);
        }

        .btn-secondary {
            background-color: #444;
            border-color: #444;
            color: white;
        }

        .btn-secondary:hover {
            background-color: #333;
            border-color: #333;
        }
    </style>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css"
          rel="stylesheet"
          integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC"
          crossorigin="anonymous">
          
          <script>
            document.getElementById("showPassword").addEventListener("click", function () {
                var passwordField = document.getElementById("password");
                if (passwordField.type === "password") {
                    passwordField.type = "text";
                    //this.textContent = "Hide";
                } else {
                    passwordField.type = "password";
                    this.textContent = "Hide";
                }
            });
          </script>
</head>
<body>
<div class="container mt-5">
    <div class="transparent-box">
        <h1 class="text-center">User Details</h1>
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
                <label for="userId" class="form-label">User ID:</label>
                <input type="text" class="form-control" id="userId" name="userId" value="${userObj.userId}" readonly>
            </div>
            <div class="mb-3">
             <label for="Balance" class="form-label">UserBalance:</label>
              <input type="text" class="form-control" id="Balance" name="Balance" value="${account.balance}">
                
            </div>
            <div class="mb-3">
                <label for="password" class="form-label">Password:</label>
                <div class="input-group">
                   <input type="text" class="form-control" id="password" name="password" value="${userObj.password}">

                    
                </div>
            </div>
            <input type="hidden" name="action" value="update">
            <div class="text-center">
                <a href="user.jsp" class="btn btn-secondary">Back</a>
            </div>
        </form>
    </div>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"
        crossorigin="anonymous"></script>
</body>
</html>
