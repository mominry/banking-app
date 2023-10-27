<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">



<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Login</title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
</head>
<body class="bg-dark">
  <div class="container mt-5">
    <div class="row">
      <div class="col-md-6">
        <div class="card">
          <div class="card-body">
            <h2>User Information</h2>
            <table class="table">
              <tr>
                <th>First Name</th>
                <td>${user.firstName}</td>
              </tr>
              <tr>
                <th>Last Name</th>
                <td>${user.lastName}</td>
              </tr>
              <tr>
                <th>Username</th>
                <td>${user.username}</td>
              </tr>
              <tr>
                <th>Password</th>
                <td>${user.password}</td>
              </tr>
            </table>
          </div>
        </div>
      </div>
             <c:set var="acc_no" value="${acc_no}" />
            <c:url var="printPassbookLink" value="UserController">
                <c:param name="command" value="passbook"></c:param>
                <c:param name="acc_no" value="${userObj.acc_no}"></c:param>
            </c:url>

            <c:url var="userTransactionLink" value="UserController">
                <c:param name="command" value="accInfo"></c:param>
                <c:param name="acc_no" value="${isUser.acc_no}"></c:param>
                <c:param name="balance" value="${balance}"></c:param>
            </c:url>

            <c:url var="loadUserLink" value="UserController">
                <c:param name="command" value="load"></c:param>
                <c:param name="acc_no" value="${isUser.acc_no}"></c:param>
            </c:url>
      <div class="col-md-6">
        <div class="card">
          <div class="card-body">
            <h2>Actions</h2>
            
              <div class="row">
                <div class="col-md-4">
                <a href="${userTransactionLink}">
                  <button type="submit" class="btn btn-primary btn-sm">Transactions Passbook</button>
                </a>
                </div>
                <div class="col-md-4">
                 <a href="${printPassbookLink}">
                  <button type="submit" class="btn btn-primary btn-sm">Fund Transfer</button>
                </a>
                </div>
                <div class="col-md-4">
                <a href="${loadUserLink}">
                  <button type="submit" class="btn btn-primary btn-sm">Withdraw-deposit</button>
                </a>
                </div>
              </div>
            
          </div>
        </div>
      </div>
    </div>
  </div>
</body>
</html>
