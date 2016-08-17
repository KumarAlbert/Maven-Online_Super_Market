<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="css/bootstrap.min.css">
<link rel="stylesheet" href="css/style.css">
<script src="js/jquery.min.js"></script>
<script src="js/bootstrap.min.js"></script>
<title>Payment</title>
</head>
<body>
<div class="alert">
  <span class="closebtn" onclick="this.parentElement.style.display='none';">&times;</span> 
  <strong>Info!</strong>Please select how you would like to pay.
</div>
<div class="row">
<div class="col-sm-12">
<div class="panel panel-primary">
  <div class="panel-heading"  style="background-color: #0059b3;">Payment Gateway</div>
 	<div class="panel-body">
	<form:form method="POST" modelAttribute="payment" action="payment.html">
	<div class="row">
	<div class="col-sm-3">
  		<div class="form-group">
    		<label >Payment Method</label>
                <select name="choice" style="margin-left: 22px; padding-left: 22px;">
                    <option value="null">--Select Payment Type--</option>
                    <option value="creditCard">Credit Card</option>
                    <option value="netBanking">Net Banking</option>
                    <option value="debitCard">Debit Card</option>
                </select>
  		</div>
  		<div class="form-group">
    		<label >Card Number</label>
    		<input name="cardNumber" type="text" class="form-control" required="required"  placeholder="Card Number" id="cardNumber"/>
  		</div>
  		<div class="form-group">
    		<label >Name On Card</label>
    		<input name="nameOnCard" type="text" class="form-control" required="required"  placeholder="Name On Card" id="nameOnCard"/>
  		</div>
  		<div class="form-group">
    		<label>Expiry Date</label>
    		      <div class="col-xs-2">
                      <label>month</label>
                      <input style="margin-left: 119px; margin-top: -20px;" class="form-control" id="month" type="number">
                      </div>
    		      <div class="col-xs-2">
                      <label>year</label>
                      <input style="margin-left: 133px; margin-top: 1px;" class="form-control" id="year" type="number">
                      </div>
  		</div>
  		<div class="form-group">
    		<label >CVV</label>
    		<input name="cvv"type="integer" class="form-control" required="required" placeholder="Security code behind your card" id="cvv"/>
  		</div>
                    <input type="image" value="submit" src="images/pay.png" alt="submit Button" onMouseOver="this.src='pay.png'">
  		</div>
	</form:form>
	</div></div></div>
