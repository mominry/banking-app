<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Fund Transfer</title>
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
    <div class="row justify-content-center align-items-center" style="height: 100vh;">
        <div class="col-md-6">
            <div class="card">
                <div class="card-header">
                    <h3>Fund Transfer</h3>
                </div>
                <div class="card-body">
                    <form action="TransactionController" method="get">
                        <div class="mb-3">
                            <label for="userBalance" class="form-label">User Balance</label>
                            <input type="number" class="form-control" id="userBalance" name="userBalance" value="${account.balance}" readonly>
                        </div>
                        <div class="mb-3">
                            <label for="senderUsername" class="form-label">Sender Username</label>
                            <input type="text" class="form-control" id="senderUsername" name="senderUsername" required>
                        </div>
                        <div class="mb-3">
                            <label for="transferAmount" class="form-label">Transfer Amount</label>
                            <input type="number" class="form-control" id="transferAmount" name="transferAmount" required>
                        </div>
                        <div class="mb-3">
                            <button type="submit" class="btn btn-primary" id="submitButton" disabled>Submit</button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>

<script> 
var userBalance = ${account.balance};

// Disable the submit button if the user balance is less than the transfer amount
document.getElementById("submitButton").disabled = (userBalance < document.getElementById("transferAmount").value);

// Update the submit button disabled state whenever the transfer amount changes
document.getElementById("transferAmount").addEventListener("input", function() {
    document.getElementById("submitButton").disabled = (userBalance < this.value);
});
</script>
</body>
</html>
