<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
</head>
<body class="bg-dark">
    <div class="container">
    <div class="row">
     <div class="col-sm-6">
     <form class="row g-3 align-items-center mt-2" action="LoginController" method="get">
     <input type="hidden" name="action" value="logout">
				<div class="col-2">
				<button type="submit" id="logout" class="btn btn-success btn-sm">logout</button>
				</div>
     </form>
     </div>
    </div>
        <h1>User Balance</h1>
        <div class="row">
            <div class="col-sm-6">
                <div class="card">
                    <div class="card-body">
                        <h5 class="card-title">Balance</h5>
                        <p class="card-text">${account.balance}</p>
                    </div>
                </div>
            </div>
            <div class="col-sm-6">
            
                <div class="card">
                    <div class="card-body">
                        <h5 class="card-title">Transaction</h5>
                        <form action="LoginController" method="get">
                            <div class="form-group">
                                <label for="type">Transaction Type</label>
                                <div class="form-check">
                                    <input type="radio" class="form-check-input" id="deposit" name="action" value="deposit"checked>
                                    <label class="form-check-label" for="deposit">Deposit</label>
                                </div>
                                <div class="form-check">
                                    <input type="radio" class="form-check-input" id="withdraw" name="action" value="withdraw">
                                    <label class="form-check-label" for="withdraw">Withdraw</label>
                                </div>
                            </div>
                            <div class="form-group mb-3">
                                <label for="amount">Amount</label>
                                <input type="number" class="form-control" id="amount" name="amount" required>
                            </div>
                            
                            <button type="submit" class="btn btn-primary" id="submitButton"  disabled>Submit</button>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>

<script> 
var userBalance = ${account.balance};


document.getElementById("submitButton").disabled = (userBalance < document.getElementById("amount").value);


document.getElementById("amount").addEventListener("input", function() {
    document.getElementById("submitButton").disabled = (userBalance < this.value);
});
</script>
</body>
</html>