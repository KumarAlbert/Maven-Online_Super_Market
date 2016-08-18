<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

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
<div class="alert alert-success">
  <span class="closebtn" onclick="this.parentElement.style.display='none';">&times;</span> 
  <strong>Info!</strong>Please select how you would like to pay.
</div>
<div class="panel ">
 	<div class="panel-body">
	<form:form method="POST"  modelAttribute="payment" action="/payment" >
  		<div class="form-inline">
    		<label >Payment Method</label>
               <select class="btn btn-info"  name="paymentType"style="    margin-left: 30px;width: 204px;" >
 	 	<option  id="paymentType" value="Credit Card">Credit Card</option>
 		 <option  id="paymentType" value="Debit Card">Debit Card</option>
 		 <option  id="paymentType" value="Net Bank">Net Bank</option>
		</select>
  		</div><br>
  		<div class="form-inline">
    		<label >Card Number</label>
    		<input style=" margin-left: 56px;" name="cardNumber" type="text" class="form-control" required="required"  placeholder="Card Number" id="cardNumber"/>
  		</div><br>
  		<div class="form-inline">
    		<label >Name Of Card Holder</label>
    		<input name="nameOnCard" type="text" class="form-control" required="required"  placeholder="Name On Card" id="nameOnCard"/>
  		</div><br>
  		<div class="form-inline">
            <label>Expiry Date</label>
                  <input type="text" placeholder="Month" style="width: 60px;margin-left: 68px;padding-left: 5px;padding-right: 5px;"  class="form-control" style="width: 60px;" id="month" >                  

                  <input  type="text" placeholder="Year" class="form-control"style="width: 60px;" id="year" type="integer">
  		</div><br>
  		<div class="form-inline">
        <label>CVV</label>
        <input type="text" style="margin-left: 116px;width: 232px;" name="cvv" class="form-control" required="required" placeholder="Security code behind your card" id="cvv" >  		
        </div><br>
        <input type="image" src="images/pay.png"  style="margin-left: 145px;" value="submit">     
	</form:form>
	</div></div>
	<div class="row">
   <div class="col-sm-12">
   <div class="panel">
 	    <div class="col-sm-3"> 
<img src="images/payment.jpg" class="img-rounded" style="width:100%;height: 186px;" alt="Cinque Terre" >
            </div>
       <div class="col-sm-3"> 
<img src="images/paypal.png" class="img-rounded" style="width:100%;height: 186px;" alt="Cinque Terre" >
            </div>
       </div></div></div>
